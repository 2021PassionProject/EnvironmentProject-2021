package passion.spring.env.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import passion.spring.env.domain.News;
import passion.spring.env.service.BlogService;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService)  { // 생성자를 활용한 주입
        this.blogService = blogService;
    }

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @GetMapping("/ad-news")
    public String getBlogs(@RequestParam("list") int list, Model model) {
        int num = 0;

        model.addAttribute("num", num);
        model.addAttribute("list", list);

        List<News> newsList = blogService.getBlogs();
        model.addAttribute("newsList", newsList);
        return "admin/ad-news";
    }

    @GetMapping("/adNewsView")
    public String getBlogsView(@RequestParam("newsId") Long id, Model model) {

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
        model.addAttribute("news", news);

        return "admin/adNewsView";
    }

    @PostMapping("/ad-news-upload")
    @Transactional
    public String postBlog(
            MultipartHttpServletRequest request,
    		/*
    		@RequestParam final String title,    		@RequestParam final String content,    		@RequestParam final String blogger,
    		@RequestParam Timestamp regDateTime,    		@RequestParam("filepath") MultipartFile file,
    		*/
            Model model) throws IllegalStateException, IOException {
            News news = new News();
            news.setNewsTitle(request.getParameter("newsTitle"));
            news.setNewsDate(Timestamp.valueOf(request.getParameter("newsDate"))) ;
            news.setReporter(request.getParameter("reporter"));
            MultipartFile file = request.getFile("filepath");
            news.setContent(request.getParameter("content"));

        if (!file.getOriginalFilename().isEmpty()) {
            file.transferTo(new File(uploadPath, file.getOriginalFilename()));
            news.setFilepath(file.getOriginalFilename());
        }
//        else {
//            model.addAttribute("message", "파일 업로드 실패");
//            return "errors/message";
//        }
        if(blogService.postBlog(news) > 0)
            return "redirect:/ad-news?list=1";
//        else {
//            model.addAttribute("message", "블로그 등록 실패");
//            return "errors/message";
//        }
        return "redirect:/ad-index";
    }

    @GetMapping("/newsEdit")
    public String updateForm(@RequestParam("newsId") Long id, Model model) {
        System.out.println(id.hashCode());
        News news = blogService.getBlog(Long.valueOf(id));
        model.addAttribute("news", news);
        return "admin/newsEdit";
    }

    @PutMapping("/newsUpdate")
    @Transactional
    public String putBlog(
            @RequestParam("newsId") long newsId,
            @RequestParam final String newsTitle,
            @RequestParam Timestamp newsDate,
            @RequestParam final String reporter,
            @RequestParam("filepath") MultipartFile file,
            @RequestParam final String content,
            Model model) throws IllegalStateException, IOException {
        News news = new News();
        news.setNewsId(newsId);
        news.setNewsTitle(newsTitle);
        news.setNewsDate(newsDate);
        news.setReporter(reporter);
        news.setContent(content);

        if (!file.getOriginalFilename().isEmpty()) {
            file.transferTo(new File(uploadPath, file.getOriginalFilename()));
            news.setFilepath(file.getOriginalFilename());
        } else {
            news.setFilepath(file.getOriginalFilename());
        }

        if(blogService.updateBlog(news) > 0)
            return "redirect:/adNewsView?newsId=" + newsId;
//        else
//            return "errors/message";
        return "redirect:/newsEdit";
    }

    @DeleteMapping("/newsDelete")
    public String deleteBlog(@RequestParam("newsId") Long id, Model model) {
        int count = blogService.deleteBlog(id);
        if(count > 0)
            return "redirect:/ad-news?list=1";
        else
            return "redirect:/adNewsView?newsId=" + id;
    }
    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam("filepath") String fileName) {
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
