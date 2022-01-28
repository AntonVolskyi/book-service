package book.manager.util;

import java.time.LocalDate;
import javax.annotation.PostConstruct;
import book.manager.model.Author;
import book.manager.model.Book;
import book.manager.service.AuthorService;
import book.manager.service.BookService;
import org.springframework.stereotype.Component;

@Component
public class DataInject {
    private Author authorKing;
    private Author authorLelush;
    private Author authorJohnson;
    private Author authorKinson;
    private BookService bookService;
    private AuthorService authorService;

    public DataInject(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void injectAllData() {
        injectAuthors();
        injectBooks();
    }

    private void injectAuthors() {
        authorJohnson = new Author();
        authorKing = new Author();
        authorLelush = new Author();
        authorKinson = new Author();

        authorJohnson.setAuthorName("Johnson");
        authorJohnson.setEmail("johnsonAuthor@gmail.com");
        authorJohnson.setBirthDate(LocalDate.now().minusYears(53));
        authorJohnson.setPhone("63255631");

        authorKing.setAuthorName("King");
        authorKing.setEmail("king_of_all_books@gmail.com");
        authorKing.setBirthDate(LocalDate.now().minusYears(28));
        authorKing.setPhone("022351145");

        authorLelush.setAuthorName("Lelush");
        authorLelush.setEmail("agentlelush001@bing.com");
        authorLelush.setBirthDate(LocalDate.now().minusYears(42));
        authorLelush.setPhone("5556332000");

        authorKinson.setAuthorName("Kinson");
        authorKinson.setEmail("booksreader12345@gmail.com");
        authorKinson.setBirthDate(LocalDate.now().minusYears(31));
        authorKinson.setPhone("0322544820");

        authorService.save(authorJohnson);
        authorService.save(authorKing);
        authorService.save(authorLelush);
        authorService.save(authorKinson);
    }

    private void injectBooks() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();
        Book book5 = new Book();
        Book book6 = new Book();
        Book book7 = new Book();
        Book book8 = new Book();
        Book book9 = new Book();
        Book book10 = new Book();

        book1.setBookName("John Killer");
        book1.setAuthor(authorJohnson);
        book1.setPublishedAmount(15_125);
        book1.setSoldAmount(10_123);

        book2.setBookName("Abstraction in Mars");
        book2.setAuthor(authorJohnson);
        book2.setPublishedAmount(26_500);
        book2.setSoldAmount(19_150);

        book3.setBookName("Draw and shoot");
        book3.setAuthor(authorKing);
        book3.setPublishedAmount(35_000);
        book3.setSoldAmount(6_500);

        book4.setBookName("Where are you?");
        book4.setAuthor(authorKing);
        book4.setPublishedAmount(26_500);
        book4.setSoldAmount(26_000);

        book5.setBookName("Just smile or...");
        book5.setAuthor(authorKing);
        book5.setPublishedAmount(30_000);
        book5.setSoldAmount(25_368);

        book6.setBookName("Pathfinder");
        book6.setAuthor(authorLelush);
        book6.setPublishedAmount(2_350);
        book6.setSoldAmount(153);

        book7.setBookName("Human born");
        book7.setAuthor(authorLelush);
        book7.setPublishedAmount(150_000);
        book7.setSoldAmount(136_255);

        book8.setBookName("Amogus");
        book8.setAuthor(authorKinson);
        book8.setPublishedAmount(50_000);
        book8.setSoldAmount(30_235);

        book9.setBookName("Piti and Pipi");
        book9.setAuthor(authorKinson);
        book9.setPublishedAmount(100_000);
        book9.setSoldAmount(99_999);

        book10.setBookName("Amogus-Fight with yellow");
        book10.setAuthor(authorKinson);
        book10.setPublishedAmount(70_000);
        book10.setSoldAmount(30_559);

        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);
        bookService.save(book4);
        bookService.save(book5);
        bookService.save(book6);
        bookService.save(book7);
        bookService.save(book8);
        bookService.save(book9);
        bookService.save(book10);
    }
}
