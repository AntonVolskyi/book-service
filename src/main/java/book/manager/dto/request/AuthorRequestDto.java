package book.manager.dto.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDto {
    private String authorName;
    private LocalDate birthDate;
    private String phone;
    private String email;
}
