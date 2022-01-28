package book.manager.service.impl;

import book.manager.dao.repository.AuthorRepository;
import book.manager.dto.response.AuthorRateResponseDto;
import book.manager.model.Author;
import book.manager.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Can`t find author by id: " + id));
    }

    @Override
    public AuthorRateResponseDto findMostSuccessfulAuthorRate() {
        return authorRepository.findMostSuccessfulAuthorRate();
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
