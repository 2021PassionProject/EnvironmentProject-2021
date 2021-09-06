package passion.springboot.passion2021.domain;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long board_id;
    private String writer;
    private String writer_email;
    private String title;
    private String write_time;
    private Long views;
    private String content;
}
