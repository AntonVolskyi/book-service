package book.manager.service;

import java.util.List;
import book.manager.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findByAuthor(String authorName);

    Book findMostSellingByAuthorFullName(String authorName);

    Book findMostPublishedByAuthorFullName(String authorName);

    List<Book> findMostSellingByAuthorPartName(String authorPartName);

    List<Book> findMostPublishedByAuthorPartName(String authorPartName);

    List<Book> findMostSuccessfulByAuthorPartName(String authorPartName);

    Book findById(Long id);

    void delete(Long id);
}
