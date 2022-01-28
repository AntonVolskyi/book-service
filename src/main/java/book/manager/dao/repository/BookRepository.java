package book.manager.dao.repository;

import java.util.List;
import book.manager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_AuthorName(String authorName);

    Book findFirstByAuthor_AuthorNameOrderBySoldAmountDesc(String authorName);

    Book findFirstByAuthor_AuthorNameOrderByPublishedAmountDesc(String authorName);

    @Query(value = "SELECT b.id, b.book_name, b.published_amount, b.sold_amount, b.author_id "
            + "FROM books b "
            + "JOIN (SELECT a.id, MAX(b.sold_amount) AS max "
            + "FROM books b "
            + "JOIN authors a ON a.id = b.author_id "
            + "WHERE LOWER(a.author_name) LIKE %?1% "
            + "GROUP BY a.id) AS f "
            + "ON f.id = b.author_id AND f.max = b.sold_amount", nativeQuery = true)
    List<Book> findMostSellingByAuthorPartName(String authorPartName);

    @Query(value = "SELECT b.id, b.book_name, b.published_amount, b.sold_amount, b.author_id "
            + "FROM books b "
            + "JOIN (SELECT a.id, MAX(b.published_amount) AS max "
            + "FROM books b "
            + "JOIN authors a ON a.id = b.author_id "
            + "WHERE LOWER(a.author_name) LIKE %?1% "
            + "GROUP BY a.id) AS f "
            + "ON f.id = b.author_id AND f.max = b.published_amount", nativeQuery = true)
    List<Book> findMostPublishedByAuthorPartName(String authorPartName);

    @Query(value = "SELECT b.id, b.book_name, b.published_amount, b.sold_amount, b.author_id "
            + "FROM books b "
            + "JOIN (SELECT a.id, MAX((CAST(b.sold_amount AS DEC) / b.published_amount)) max "
            + "FROM books b "
            + "JOIN authors a ON a.id = b.author_id "
            + "WHERE a.author_name LIKE %?1% "
            + "GROUP BY a.id) AS f "
            + "ON f.id = b.author_id "
            + "AND f.max = (CAST(b.sold_amount AS DEC) / b.published_amount)",
            nativeQuery = true)
    List<Book> findMostSuccessfulByAuthorPartName(String authorPartName);
}
