package passion.springboot.passion.domain;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long comment_id;
    private Long board_id;
    private String member_name;
    private String content;
    private String write_time;
}
