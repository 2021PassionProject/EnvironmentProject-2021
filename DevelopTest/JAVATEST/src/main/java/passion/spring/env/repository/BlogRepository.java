package passion.spring.env.repository;

import passion.spring.env.domain.News;

import java.util.List;

public interface BlogRepository {
	int create(News news);
	News read(News news);
	List<News> readList();
	int update(News news);
	int delete(News news);
	List<News> getBlogsByPage(int index, int size);
}
