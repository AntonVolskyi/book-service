package book.manager.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {
    private String bookName;
    private Long authorId;
    private Integer publishedAmount;
    private Integer soldAmount;
}
