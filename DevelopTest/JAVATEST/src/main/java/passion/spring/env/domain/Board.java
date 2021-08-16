package passion.spring.env.domain;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long board_id;
    private String title;
    private String content;
}
