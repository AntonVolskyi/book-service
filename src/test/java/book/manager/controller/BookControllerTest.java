package book.manager.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import book.manager.dto.request.BookRequestDto;
import book.manager.model.Author;
import book.manager.model.Book;
import book.manager.service.AuthorService;
import book.manager.service.BookService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;

    private static Author authorForBook;

    @BeforeAll
    static void beforeAll() {
        authorForBook = new Author();
        authorForBook.setId(1L);
        authorForBook.setAuthorName("Johnson");
        authorForBook.setEmail("test@temail.com");
        authorForBook.setPhone("223556211");
        authorForBook.setBirthDate(LocalDate.of(1980, 11, 15));
    }

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void save_Ok() {
        Book bookToSave = new Book();
        bookToSave.setBookName("Brender");
        bookToSave.setSoldAmount(13_000);
        bookToSave.setPublishedAmount(15_000);
        bookToSave.setAuthor(authorForBook);

        BookRequestDto requestDto = new BookRequestDto();
        requestDto.setBookName(bookToSave.getBookName());
        requestDto.setSoldAmount(bookToSave.getSoldAmount());
        requestDto.setPublishedAmount(bookToSave.getPublishedAmount());
        requestDto.setAuthorId(bookToSave.getAuthor().getId());

        Book bookToReturn = new Book();
        bookToReturn.setId(2L);
        bookToReturn.setBookName("Brender");
        bookToReturn.setSoldAmount(13_000);
        bookToReturn.setPublishedAmount(15_000);
        bookToReturn.setAuthor(authorForBook);

        Mockito.when(bookService.save(bookToSave)).thenReturn(bookToReturn);
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post("/books")
                .then()
                .body("id", Matchers.equalTo(2))
                .body("bookName", Matchers.equalTo("Brender"))
                .body("soldAmount", Matchers.equalTo(13_000))
                .body("publishedAmount", Matchers.equalTo(15_000))
                .body("authorName", Matchers.equalTo("Johnson"));
    }

    @Test
    void update_Ok() {
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("Brender");
        bookToUpdate.setSoldAmount(13_000);
        bookToUpdate.setPublishedAmount(15_000);
        bookToUpdate.setAuthor(authorForBook);

        BookRequestDto requestDto = new BookRequestDto();
        requestDto.setBookName(bookToUpdate.getBookName());
        requestDto.setSoldAmount(bookToUpdate.getSoldAmount());
        requestDto.setPublishedAmount(bookToUpdate.getPublishedAmount());
        requestDto.setAuthorId(bookToUpdate.getAuthor().getId());

        Book bookToReturn = new Book();
        bookToReturn.setId(2L);
        bookToReturn.setBookName("Brender");
        bookToReturn.setSoldAmount(13_000);
        bookToReturn.setPublishedAmount(15_000);
        bookToReturn.setAuthor(authorForBook);

        Mockito.when(bookService.save(bookToUpdate)).thenReturn(bookToReturn);
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .put("/books/2")
                .then()
                .body("id", Matchers.equalTo(2))
                .body("bookName", Matchers.equalTo("Brender"))
                .body("soldAmount", Matchers.equalTo(13_000))
                .body("publishedAmount", Matchers.equalTo(15_000))
                .body("authorName", Matchers.equalTo("Johnson"));
    }

    @Test
    void findByAuthor_Ok() {
        Book firstBookToReturn = new Book();
        firstBookToReturn.setId(2L);
        firstBookToReturn.setBookName("Brender");
        firstBookToReturn.setSoldAmount(13_000);
        firstBookToReturn.setPublishedAmount(15_000);
        firstBookToReturn.setAuthor(authorForBook);

        Book secondBookToReturn = new Book();
        secondBookToReturn.setId(3L);
        secondBookToReturn.setBookName("German Roman");
        secondBookToReturn.setSoldAmount(20_000);
        secondBookToReturn.setPublishedAmount(56_000);
        secondBookToReturn.setAuthor(authorForBook);

        Mockito.when(bookService.findByAuthor("Johnson")).thenReturn(List.of(firstBookToReturn,
                secondBookToReturn));
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.when()
                .get("/books?authorName=Johnson")
                .then()
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(2))
                .body("[0].bookName", Matchers.equalTo("Brender"))
                .body("[0].soldAmount", Matchers.equalTo(13_000))
                .body("[0].publishedAmount", Matchers.equalTo(15_000))
                .body("[0].authorName", Matchers.equalTo("Johnson"))
                .body("[1].id", Matchers.equalTo(3))
                .body("[1].bookName", Matchers.equalTo("German Roman"))
                .body("[1].soldAmount", Matchers.equalTo(20_000))
                .body("[1].publishedAmount", Matchers.equalTo(56_000))
                .body("[1].authorName", Matchers.equalTo("Johnson"));

    }

    @Test
    void findMostSellingByAuthorFullName_Ok() {
        Book mostSellingBook = new Book();
        mostSellingBook.setId(3L);
        mostSellingBook.setBookName("German Roman");
        mostSellingBook.setSoldAmount(40_000);
        mostSellingBook.setPublishedAmount(56_000);
        mostSellingBook.setAuthor(authorForBook);

        Mockito.when(bookService.findMostSellingByAuthorFullName("Johnson"))
                .thenReturn(mostSellingBook);
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.when()
                .get("/books/most-selling?authorName=Johnson")
                .then()
                .body("id", Matchers.equalTo(3))
                .body("bookName", Matchers.equalTo("German Roman"))
                .body("soldAmount", Matchers.equalTo(40_000))
                .body("publishedAmount", Matchers.equalTo(56_000))
                .body("authorName", Matchers.equalTo("Johnson"));

    }

    @Test
    void findMostPublisherByAuthorFullName_Ok() {
        Book mostPublishedBook = new Book();
        mostPublishedBook.setId(23L);
        mostPublishedBook.setBookName("Big Road");
        mostPublishedBook.setSoldAmount(12_000);
        mostPublishedBook.setPublishedAmount(150_000);
        mostPublishedBook.setAuthor(authorForBook);

        Mockito.when(bookService.findMostPublishedByAuthorFullName("Johnson"))
                .thenReturn(mostPublishedBook);
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.when()
                .get("/books/most-published?authorName=Johnson")
                .then()
                .body("id", Matchers.equalTo(23))
                .body("bookName", Matchers.equalTo("Big Road"))
                .body("soldAmount", Matchers.equalTo(12_000))
                .body("publishedAmount", Matchers.equalTo(150_000))
                .body("authorName", Matchers.equalTo("Johnson"));
    }

    @Test
    void findMostSellingByAuthorPartName_Ok() {
        Book mostSellingBook = new Book();
        mostSellingBook.setId(123L);
        mostSellingBook.setBookName("All about DinDin");
        mostSellingBook.setSoldAmount(145_000);
        mostSellingBook.setPublishedAmount(150_000);
        mostSellingBook.setAuthor(authorForBook);

        Mockito.when(bookService.findMostSellingByAuthorPartName("oh"))
                .thenReturn(List.of(mostSellingBook));
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.when()
                .get("/books/most-selling-by-author-part-name?authorPartName=oh")
                .then()
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(123))
                .body("[0].bookName", Matchers.equalTo("All about DinDin"))
                .body("[0].soldAmount", Matchers.equalTo(145_000))
                .body("[0].publishedAmount", Matchers.equalTo(150_000))
                .body("[0].authorName", Matchers.equalTo("Johnson"));
    }

    @Test
    void findMostPublishedByAuthorPartName_Ok() {
        Book mostPublishedBook = new Book();
        mostPublishedBook.setId(51L);
        mostPublishedBook.setBookName("Perl Harbor");
        mostPublishedBook.setSoldAmount(50_000);
        mostPublishedBook.setPublishedAmount(90_000);
        mostPublishedBook.setAuthor(authorForBook);

        Mockito.when(bookService.findMostPublishedByAuthorPartName("john"))
                .thenReturn(List.of(mostPublishedBook));
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.when()
                .get("/books/most-published-by-author-part-name?authorPartName=john")
                .then()
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(51))
                .body("[0].bookName", Matchers.equalTo("Perl Harbor"))
                .body("[0].soldAmount", Matchers.equalTo(50_000))
                .body("[0].publishedAmount", Matchers.equalTo(90_000))
                .body("[0].authorName", Matchers.equalTo("Johnson"));
    }

    @Test
    void findMostSuccessfulByAuthorPartName_Ok() {
        Book mostSuccessfulBook = new Book();
        mostSuccessfulBook.setId(11L);
        mostSuccessfulBook.setBookName("Black & White");
        mostSuccessfulBook.setSoldAmount(130_000);
        mostSuccessfulBook.setPublishedAmount(140_000);
        mostSuccessfulBook.setAuthor(authorForBook);

        Mockito.when(bookService.findMostSuccessfulByAuthorPartName("jo"))
                .thenReturn(List.of(mostSuccessfulBook));
        Mockito.when(authorService.findById(1L)).thenReturn(authorForBook);

        RestAssuredMockMvc.when()
                .get("/books/most-successful-by-author-part-name?authorPartName=jo")
                .then()
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(11))
                .body("[0].bookName", Matchers.equalTo("Black & White"))
                .body("[0].soldAmount", Matchers.equalTo(130_000))
                .body("[0].publishedAmount", Matchers.equalTo(140_000))
                .body("[0].authorName", Matchers.equalTo("Johnson"));
    }
}
