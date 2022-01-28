package book.manager.controller;

import book.manager.dto.request.AuthorRequestDto;
import book.manager.dto.request.mapper.RequestMapper;
import book.manager.dto.response.AuthorRateResponseDto;
import book.manager.dto.response.AuthorResponseDto;
import book.manager.dto.response.mapper.ResponseMapper;
import book.manager.model.Author;
import book.manager.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;
    private RequestMapper<AuthorRequestDto, Author> requestMapper;
    private ResponseMapper<Author, AuthorResponseDto> responseMapper;

    @GetMapping("/find-most-successful")
    public AuthorRateResponseDto findMostSuccessfulAuthor() {
        return authorService.findMostSuccessfulAuthorRate();
    }

    @PostMapping
    public AuthorResponseDto save(@RequestBody AuthorRequestDto requestDto) {
        Author author = authorService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(author);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto update(@PathVariable Long id,
                                    @RequestBody AuthorRequestDto requestDto) {
        authorService.findById(id);
        Author author = requestMapper.mapToModel(requestDto);
        author.setId(id);
        authorService.save(author);
        return responseMapper.mapToDto(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
