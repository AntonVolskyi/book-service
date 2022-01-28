package book.manager.dto.response.mapper;

import book.manager.dto.response.AuthorResponseDto;
import book.manager.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorResponseMapper implements ResponseMapper<Author, AuthorResponseDto> {
    public AuthorResponseDto mapToDto(Author author) {
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setId(author.getId());
        responseDto.setAuthorName(author.getAuthorName());
        responseDto.setBirthDate(author.getBirthDate());
        responseDto.setPhone(author.getPhone());
        responseDto.setEmail(author.getEmail());
        return responseDto;
    }
}
