package book.manager.dto.request.mapper;

public interface RequestMapper<I, R> {
    R mapToModel(I dto);
}
