package book.manager.service.impl;

import java.util.List;
import book.manager.dao.repository.BookRepository;
import book.manager.model.Book;
import book.manager.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        return bookRepository.findAllByAuthor_AuthorName(authorName);
    }

    @Override
    public Book findMostSellingByAuthorFullName(String authorName) {
        return bookRepository.findFirstByAuthor_AuthorNameOrderBySoldAmountDesc(authorName);
    }

    @Override
    public Book findMostPublishedByAuthorFullName(String authorName) {
        return bookRepository
                .findFirstByAuthor_AuthorNameOrderByPublishedAmountDesc(authorName);
    }

    @Override
    public List<Book> findMostSellingByAuthorPartName(String authorPartName) {
        return bookRepository.findMostSellingByAuthorPartName(authorPartName.toLowerCase());
    }

    @Override
    public List<Book> findMostPublishedByAuthorPartName(String authorPartName) {
        return bookRepository.findMostPublishedByAuthorPartName(authorPartName);
    }

    @Override
    public List<Book> findMostSuccessfulByAuthorPartName(String authorPartName) {
        return bookRepository.findMostSuccessfulByAuthorPartName(authorPartName);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Can`t find book by id: " + id));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
