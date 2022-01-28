package book.manager.dto.request.mapper;

import book.manager.dto.request.AuthorRequestDto;
import book.manager.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorRequestMapper implements RequestMapper<AuthorRequestDto, Author> {
    public Author mapToModel(AuthorRequestDto requestDto) {
        Author author = new Author();
        author.setAuthorName(requestDto.getAuthorName());
        author.setBirthDate(requestDto.getBirthDate());
        author.setPhone(requestDto.getPhone());
        author.setEmail(requestDto.getEmail());
        return author;
    }
}
