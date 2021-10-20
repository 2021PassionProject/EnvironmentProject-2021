package passion.spring.env.domain;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Long newsId;
    private String newsTitle;
    private Date newsDate;
    private String reporter;
    private String filepath;
    private String content;
}
