package book.manager.dto.request.mapper;

import book.manager.dto.request.BookRequestDto;
import book.manager.model.Book;
import book.manager.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookRequestMapper implements RequestMapper<BookRequestDto, Book> {
    private AuthorService authorService;

    public Book mapToModel(BookRequestDto requestDto) {
        Book book = new Book();
        book.setBookName(requestDto.getBookName());
        book.setSoldAmount(requestDto.getSoldAmount());
        book.setPublishedAmount(requestDto.getPublishedAmount());
        book.setAuthor(authorService.findById(requestDto.getAuthorId()));
        return book;
    }
}
