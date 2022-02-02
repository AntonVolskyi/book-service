package book.manager.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import book.manager.dto.request.AuthorRequestDto;
import book.manager.dto.response.AuthorRateResponseDto;
import book.manager.model.Author;
import book.manager.service.AuthorService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
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
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void findMostSuccessfulAuthor_Ok() {
        Mockito.when(authorService.findMostSuccessfulAuthorRate()).thenReturn(new AuthorRateResponseDto() {
            @Override
            public String getAuthorName() {
                return "Klark";
            }

            @Override
            public Float getAuthorRate() {
                return 0.82F;
            }
        });

        RestAssuredMockMvc.when()
                .get("/authors/find-most-successful")
                .then()
                .body("authorName", Matchers.equalTo("Klark"))
                .body("authorRate", Matchers.equalTo(0.82F));
    }

    @Test
    void save_Ok() {
        Author authorToSave = new Author();
        authorToSave.setAuthorName("Johnson");
        authorToSave.setEmail("test@temail.com");
        authorToSave.setPhone("223556211");
        authorToSave.setBirthDate(LocalDate.of(1980, 11, 15));

        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setAuthorName(authorToSave.getAuthorName());
        requestDto.setEmail(authorToSave.getEmail());
        requestDto.setPhone(authorToSave.getPhone());
        requestDto.setBirthDate(authorToSave.getBirthDate());

        Author authorToReturn = new Author();
        authorToReturn.setId(15L);
        authorToReturn.setAuthorName("Johnson");
        authorToReturn.setEmail("test@temail.com");
        authorToReturn.setPhone("223556211");
        authorToReturn.setBirthDate(LocalDate.of(1980, 11, 15));

        Mockito.when(authorService.save(authorToSave)).thenReturn(authorToReturn);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post("/authors")
                .then()
                .body("id", Matchers.equalTo(15))
                .body("authorName", Matchers.equalTo("Johnson"))
                .body("email", Matchers.equalTo("test@temail.com"))
                .body("phone", Matchers.equalTo("223556211"))
                .body("birthDate", Matchers.equalTo(LocalDate.of(1980, 11, 15)
                        .toString()));
    }

    @Test
    void update_Ok() {
        Author authorToUpdate = new Author();
        authorToUpdate.setAuthorName("Johnson");
        authorToUpdate.setEmail("test@temail.com");
        authorToUpdate.setPhone("223556211");
        authorToUpdate.setBirthDate(LocalDate.of(1980, 11, 15));

        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setAuthorName(authorToUpdate.getAuthorName());
        requestDto.setEmail(authorToUpdate.getEmail());
        requestDto.setPhone(authorToUpdate.getPhone());
        requestDto.setBirthDate(authorToUpdate.getBirthDate());

        Author authorToReturn = new Author();
        authorToReturn.setId(15L);
        authorToReturn.setAuthorName("Johnson");
        authorToReturn.setEmail("test@temail.com");
        authorToReturn.setPhone("223556211");
        authorToReturn.setBirthDate(LocalDate.of(1980, 11, 15));

        Mockito.when(authorService.save(authorToUpdate)).thenReturn(authorToReturn);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .put("/authors/15")
                .then()
                .body("id", Matchers.equalTo(15))
                .body("authorName", Matchers.equalTo("Johnson"))
                .body("email", Matchers.equalTo("test@temail.com"))
                .body("phone", Matchers.equalTo("223556211"))
                .body("birthDate", Matchers.equalTo(LocalDate.of(1980, 11, 15)
                        .toString()));
    }
}
