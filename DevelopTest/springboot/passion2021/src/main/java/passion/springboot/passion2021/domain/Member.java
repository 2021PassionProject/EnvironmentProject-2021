package passion.springboot.passion2021.domain;

import lombok.*;

@AllArgsConstructor // new Member(1, "hanjs@induk.ac.kr" .....);
@NoArgsConstructor  // new Member();
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Member {
    /**
     * DTO, VO : 데이터를 보내고 받는데 사용하는 객체, Data Access Layer에서 사용하는 객체
     * DTO(Data Transfer Object) - creat, update, delete 포함
     * VO(Valuable Object) - read 중심, select query 대상
     * 데이터베이스 기본 연산 - CRUD(Create, Read, Update, Delete) - 의 대상
     */
    private Long id;
    //  @NotBlank
    //  @Email
    private String email;
    private String pw;
    private String name;
    private String birth;
    private String phone;
    private String address;
    private String address2;


}