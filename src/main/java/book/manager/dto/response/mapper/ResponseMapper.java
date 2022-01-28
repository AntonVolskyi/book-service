package book.manager.dto.response.mapper;

public interface ResponseMapper<I, R> {
    R mapToDto(I model);
}
