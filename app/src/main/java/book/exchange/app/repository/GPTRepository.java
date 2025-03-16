package book.exchange.app.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface GPTRepository {
    @Select({"<script>",
            "SELECT id FROM (",
            "   SELECT id FROM app.books WHERE (title, author) IN",
            "   <foreach item='titleAuthor' collection='titlesAndAuthors' open='(' separator=',' close=')'>",
            "       (#{titleAuthor[0]}, #{titleAuthor[1]})",
            "   </foreach>",

            "   UNION ALL",

            "   SELECT id FROM app.periodicals WHERE (title, author) IN",
            "   <foreach item='titleAuthor' collection='titlesAndAuthors' open='(' separator=',' close=')'>",
            "       (#{titleAuthor[0]}, #{titleAuthor[1]})",
            "   </foreach>",

            "   UNION ALL",

            "   SELECT id FROM app.comics WHERE (title, author) IN",
            "   <foreach item='titleAuthor' collection='titlesAndAuthors' open='(' separator=',' close=')'>",
            "       (#{titleAuthor[0]}, #{titleAuthor[1]})",
            "   </foreach>",
            ") AS publications",
            "</script>"})
    List<UUID> findPublicationIdsByTitleAndAuthor(@Param("titlesAndAuthors") List<String[]> titlesAndAuthors);
}
