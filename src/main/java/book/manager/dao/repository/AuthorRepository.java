package book.manager.dao.repository;

import book.manager.dto.response.AuthorRateResponseDto;
import book.manager.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT a.author_name AS authorName, author_rate AS authorRate "
            + "FROM authors a "
            + "JOIN (SELECT a.author_name, a.id, "
            + "(sum(CAST(b.sold_amount AS DEC) / b.published_amount) / count(book_name)) "
            + "AS author_rate "
            + "FROM books b "
            + "JOIN authors a ON a.id = b.author_id "
            + "GROUP BY a.id "
            + "ORDER BY author_rate DESC "
            + "LIMIT 1) AS f "
            + "ON f.id = a.id", nativeQuery = true)
    AuthorRateResponseDto findMostSuccessfulAuthorRate();
}
