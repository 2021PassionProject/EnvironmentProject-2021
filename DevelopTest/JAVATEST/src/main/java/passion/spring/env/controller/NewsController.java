package passion.spring.env.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import passion.spring.env.domain.News;
import passion.spring.env.service.BlogService;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/")
public class NewsController {

    private BlogService blogService;

    @Autowired
    public NewsController(BlogService blogService)  { // 생성자를 활용한 주입
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String blog(Model model) {

        List<News> newsList = blogService.getBlogs();
        model.addAttribute("newsList", newsList);

        return "news/blog";
    }

    @GetMapping("/blog-details")
    public String blogDetails(@RequestParam("newsId") Long id, Model model) {

//                model.addAttribute("newsList", BlogRepositoryImpl.jdbcTemplate1.query("SELECT * FROM news",
//                new RowMapper<News>() {
//                    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        News news = new News();
//                        news.setNewsId(rs.getLong("newsId"));
//                        news.setNewsTitle(rs.getString("newsTitle"));
//                        news.setNewsDate(rs.getTimestamp("newsDate"));
//                        news.setReporter(rs.getString("reporter"));
//                        news.setFilepath(rs.getString("filepath"));
//                        news.setContent(rs.getString("content"));
//
//                        return news;
//                    }
//                }
//            )
//        );
        System.out.println(id.hashCode());
        News news = blogService.getBlog(Long.valueOf(id));
        model.addAttribute("newsList", news);

        return "news/blog-details";
    }
}
