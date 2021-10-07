package passion.spring.env.service;

import passion.spring.env.domain.News;

import java.util.List;

public interface BlogService {
	int postBlog(News news); // 생성
	News getBlog(Long id);		// primary key에 해당하는 id로  조회
	List<News> getBlogs(); // 모든 사용자 조회

	List<News> getBlogsByTitle(String title); // title으로 조회
	List<News> getBlogsByBlogger(String blogger); // blogger으로 조회
	List<News> getBlogsByPage(int index, int size); // 페이지로 조회
	int updateBlog(News news); // 수정
	int deleteBlog(Long id); // 삭제
	int getTotalRowCount(); // total row count for pagination
}
