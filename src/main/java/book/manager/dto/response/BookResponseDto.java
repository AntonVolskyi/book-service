package book.manager.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {
    private Long id;
    private String bookName;
    private String authorName;
    private Integer publishedAmount;
    private Integer soldAmount;
}
