package book.manager.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorResponseDto {
    private Long id;
    private String authorName;
    private LocalDate birthDate;
    private String phone;
    private String email;
}
