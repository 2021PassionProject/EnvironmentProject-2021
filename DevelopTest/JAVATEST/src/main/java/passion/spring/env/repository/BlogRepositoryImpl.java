package passion.spring.env.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import passion.spring.env.domain.Board;
import passion.spring.env.domain.News;

import javax.sql.DataSource;
import java.util.List;

@Repository("blogRepositoryImpl")
public class BlogRepositoryImpl implements BlogRepository {
	public static JdbcTemplate jdbcTemplate1;

	@Autowired
	public BlogRepositoryImpl(DataSource dataSource) {
		jdbcTemplate1 = new JdbcTemplate(dataSource); // Spring Bean dataSource 객체를 주입해야함.
	}

	@Override
	public int create(News news) {
		String sql = "insert into news values(seq_news.nextval,?,?,?,?)";
		Object[] params = new Object[]{news.getNewsTitle(), news.getReporter(),  news.getFilepath(), news.getContent()};
		return jdbcTemplate1.update(sql, params);
	}

	@Override
	public News read(News news) {
		String sql = "select * from news where newsId=?";
		Object[] id = new Object[]{news.getNewsId()}; // email -> id
		try {
			return jdbcTemplate1.queryForObject(sql, new BeanPropertyRowMapper<News>(News.class), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<News> readList() {
		String sql = "select * from news";
		return jdbcTemplate1.query(sql, new BeanPropertyRowMapper<>(News.class));
	}

	@Override
	public int update(News news) {
		return 0;
	}

	@Override
	public int delete(News news) {
		String sql = "delete from news where newsId=?";
		Object[] params = new Object[]{news.getNewsId()};
		return jdbcTemplate1.update(sql, params);
	}

	@Override
	public List<News> getBlogsByPage(int index, int size) {
		return null;
	}

}
