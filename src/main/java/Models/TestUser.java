package Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestUser {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
