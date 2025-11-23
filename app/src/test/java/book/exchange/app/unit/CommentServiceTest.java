package book.exchange.app.unit;

import book.exchange.app.dto.commentDTOs.CommentRequestDTO;
import book.exchange.app.dto.commentDTOs.CommentResponseDTO;
import book.exchange.app.mapper.CommentMapper;
import book.exchange.app.model.Comment;
import book.exchange.app.repository.CommentRepository;
import book.exchange.app.service.CommentService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    // -----------------------------------------------------------
    // 1. createComment()
    // -----------------------------------------------------------
    @Test
    void testCreateComment() {

        CommentRequestDTO requestDTO = CommentRequestDTO.builder()
                .content("Hello!")
                .userId(UUID.randomUUID())
                .commentId(null)
                .noticeId(UUID.randomUUID())
                .build();

        Comment fakeComment = new Comment();
        fakeComment.setId(UUID.randomUUID());
        fakeComment.setContent("Hello!");

        try (MockedStatic<CommentMapper> mocked = mockStatic(CommentMapper.class)) {

            mocked.when(() -> CommentMapper.fromDto(requestDTO))
                    .thenReturn(fakeComment);

            mocked.when(() -> CommentMapper.toDto(fakeComment))
                    .thenReturn(
                            CommentResponseDTO.builder()
                                    .id(fakeComment.getId())
                                    .content("Hello!")
                                    .timeSent(LocalDateTime.now())
                                    .replies(null)
                                    .build()
                    );

            CommentResponseDTO result = commentService.createComment(requestDTO);

            verify(commentRepository).createComment(fakeComment);
            assertEquals(fakeComment.getId(), result.getId());
            assertEquals("Hello!", result.getContent());
        }
    }

    // -----------------------------------------------------------
    // 2. getCommentsForNotice() — recursive replies
    // -----------------------------------------------------------
    @Test
    void testGetCommentsForNoticeWithReplies() {

        UUID noticeId = UUID.randomUUID();

        Comment parent = new Comment();
        parent.setId(UUID.randomUUID());

        Comment child = new Comment();
        child.setId(UUID.randomUUID());

        Comment grandChild = new Comment();
        grandChild.setId(UUID.randomUUID());

        when(commentRepository.getCommentForNotice(noticeId))
                .thenReturn(List.of(parent));

        when(commentRepository.getRepliesForComment(parent.getId()))
                .thenReturn(List.of(child));

        when(commentRepository.getRepliesForComment(child.getId()))
                .thenReturn(List.of(grandChild));

        when(commentRepository.getRepliesForComment(grandChild.getId()))
                .thenReturn(Collections.emptyList());

        try (MockedStatic<CommentMapper> mocked = mockStatic(CommentMapper.class)) {

            mocked.when(() -> CommentMapper.toDto(any()))
                    .thenAnswer(inv -> {
                        Comment c = inv.getArgument(0);

                        return CommentResponseDTO.builder()
                                .id(c.getId())
                                .content(c.getContent())
                                .timeSent(LocalDateTime.now())
                                .replies(null)
                                .build();
                    });

            List<CommentResponseDTO> result = commentService.getCommentsForNotice(noticeId);

            assertEquals(1, result.size());
            assertEquals(parent.getId(), result.get(0).getId());

            assertEquals(1, parent.getReplies().size());
            assertEquals(1, child.getReplies().size());
            assertEquals(0, grandChild.getReplies().size());
        }
    }

    // -----------------------------------------------------------
    // 3. updateComment() — success
    // -----------------------------------------------------------
    @Test
    void testUpdateComment() {

        UUID id = UUID.randomUUID();
        CommentRequestDTO requestDTO = CommentRequestDTO.builder()
                .content("Updated")
                .userId(UUID.randomUUID())
                .noticeId(UUID.randomUUID())
                .commentId(id)
                .build();

        Comment existing = new Comment();
        existing.setId(id);
        existing.setContent("Old content");

        when(commentRepository.findCommentById(id))
                .thenReturn(Optional.of(existing));

        try (MockedStatic<CommentMapper> mocked = mockStatic(CommentMapper.class)) {

            mocked.when(() -> CommentMapper.toDto(existing))
                    .thenReturn(
                            CommentResponseDTO.builder()
                                    .id(id)
                                    .content("Updated")
                                    .timeSent(LocalDateTime.now())
                                    .replies(null)
                                    .build()
                    );

            CommentResponseDTO response = commentService.updateComment(id, requestDTO);

            verify(commentRepository).updateComment(existing);
            assertEquals("Updated", response.getContent());
            assertNotNull(existing.getTimeSent());
        }
    }

    // -----------------------------------------------------------
    // 3b. updateComment() — NOT FOUND
    // -----------------------------------------------------------
    @Test
    void testUpdateCommentNotFound() {

        UUID id = UUID.randomUUID();

        CommentRequestDTO requestDTO = CommentRequestDTO.builder()
                .content("Doesn't matter")
                .userId(UUID.randomUUID())
                .noticeId(UUID.randomUUID())
                .commentId(id)
                .build();

        when(commentRepository.findCommentById(id))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> commentService.updateComment(id, requestDTO));
    }

    // -----------------------------------------------------------
    // 4. deleteComment()
    // -----------------------------------------------------------
    @Test
    void testDeleteComment() {

        UUID id = UUID.randomUUID();

        commentService.deleteComment(id);

        verify(commentRepository).deleteComment(id);
    }
}
