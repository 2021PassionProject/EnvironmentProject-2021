package passion.spring.env.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.spring.env.domain.News;
import passion.spring.env.repository.BlogRepository;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
	private BlogRepository blogRepository;
	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public int postBlog(News news) {
		return blogRepository.create(news);
	}

	@Override
	public News getBlog(Long id) {
		News news = new News();
		news.setNewsId(id);
		return blogRepository.read(news);
	}

	@Override
	public List<News> getBlogs() {
		return blogRepository.readList();
	}

	@Override
	public List<News> getBlogsByTitle(String title) {
		return null;
	}

	@Override
	public List<News> getBlogsByBlogger(String blogger) {
		return null;
	}

	@Override
	public List<News> getBlogsByPage(int index, int size) {
		return null;
	}

	@Override
	public int updateBlog(News news) {
		return blogRepository.update(news);
	}

	@Override
	public int deleteBlog(Long id) {
		News news = new News();
		news.setNewsId(id);
		return blogRepository.delete(news);
	}

	@Override
	public int getTotalRowCount() {
		return 0;
	}
}