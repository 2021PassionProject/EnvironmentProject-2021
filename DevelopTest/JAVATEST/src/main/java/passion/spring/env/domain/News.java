package passion.spring.env.domain;

import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Long newsId;
    private String newsTitle;
    private Timestamp newsDate;
    private String reporter;
    private String filepath;
    private String content;
}
