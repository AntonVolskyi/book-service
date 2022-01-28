package book.manager.service;

import book.manager.dto.response.AuthorRateResponseDto;
import book.manager.model.Author;

public interface AuthorService {
    Author save(Author author);

    Author findById(Long id);

    AuthorRateResponseDto findMostSuccessfulAuthorRate();

    void delete(Long id);
}
