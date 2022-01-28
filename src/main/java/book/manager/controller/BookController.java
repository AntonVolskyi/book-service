package book.manager.controller;

import java.util.List;
import java.util.stream.Collectors;
import book.manager.dto.request.BookRequestDto;
import book.manager.dto.request.mapper.RequestMapper;
import book.manager.dto.response.BookResponseDto;
import book.manager.dto.response.mapper.ResponseMapper;
import book.manager.model.Book;
import book.manager.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    private RequestMapper<BookRequestDto, Book> requestMapper;
    private ResponseMapper<Book, BookResponseDto> responseMapper;

    @GetMapping
    public List<BookResponseDto> findByAuthor(@RequestParam String authorName) {
        return bookService.findByAuthor(authorName)
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/most-selling")
    public BookResponseDto findMostSellingByAuthorFullName(
            @RequestParam String authorName) {
        return responseMapper.mapToDto(bookService.findMostSellingByAuthorFullName(authorName));
    }

    @GetMapping("/most-published")
    public BookResponseDto findMostPublisherByAuthorFullName(
            @RequestParam String authorName) {
        return responseMapper.mapToDto(bookService.findMostPublishedByAuthorFullName(authorName));
    }

    @GetMapping("/most-selling-by-author-part-name")
    public List<BookResponseDto> findMostSellingByAuthorPartName(
            @RequestParam String authorPartName) {
        return bookService.findMostSellingByAuthorPartName(authorPartName)
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/most-published-by-author-part-name")
    public List<BookResponseDto> findMostPublishedByAuthorPartName(
            @RequestParam String authorPartName) {
        return bookService.findMostPublishedByAuthorPartName(authorPartName)
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/most-successful-by-author-part-name")
    public List<BookResponseDto> findMostSuccessfulByAuthorPartName(
            @RequestParam String authorPartName) {
        return bookService.findMostSuccessfulByAuthorPartName(authorPartName)
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookResponseDto save(@RequestBody BookRequestDto requestDto) {
        Book book = bookService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(book);
    }

    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable Long id,
                                  @RequestBody BookRequestDto requestDto) {
        bookService.findById(id);
        Book book = requestMapper.mapToModel(requestDto);
        book.setId(id);
        return responseMapper.mapToDto(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
