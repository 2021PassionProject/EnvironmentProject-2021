package passion.springboot.environment.domain;

import lombok.*;

@AllArgsConstructor // new Member(1, "egyou@induk.ac.kr" ...);
@NoArgsConstructor //new Member();
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Member {
    private Long id;
    private String email;
    private String pw;
    private String name;
    private String phone;
    private String address;

}
