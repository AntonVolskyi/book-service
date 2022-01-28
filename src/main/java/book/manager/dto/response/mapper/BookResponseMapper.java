package book.manager.dto.response.mapper;

import book.manager.dto.response.BookResponseDto;
import book.manager.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookResponseMapper implements ResponseMapper<Book, BookResponseDto> {
    public BookResponseDto mapToDto(Book book) {
        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setId(book.getId());
        responseDto.setBookName(book.getBookName());
        responseDto.setAuthorName(book.getAuthor().getAuthorName());
        responseDto.setPublishedAmount(book.getPublishedAmount());
        responseDto.setSoldAmount(book.getSoldAmount());
        return responseDto;
    }
}
