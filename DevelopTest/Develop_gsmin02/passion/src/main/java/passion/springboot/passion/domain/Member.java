package passion.springboot.passion.domain;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String pw;
    private String name;
    private String birth;
    private String phone;
    private String address;
    private String address2;
}


