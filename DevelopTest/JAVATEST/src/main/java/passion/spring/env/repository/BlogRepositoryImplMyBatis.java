package passion.spring.env.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import passion.spring.env.domain.News;

import java.util.List;

@Primary
@Repository("blogRepositoryImplMyBatis")
public class BlogRepositoryImplMyBatis implements BlogRepository {

    private SqlSession sqlSession;
    @Autowired
    public BlogRepositoryImplMyBatis(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private static String namespace = "passion.spring.env.mapper.BlogMapper";

    @Override
    public List<News> readList() {
        System.out.println("MyBatis : " + sqlSession);
        return sqlSession.selectList(namespace + ".readList");
    }

    @Override
    public News read(News news) {
        return sqlSession.selectOne(namespace + ".read", news.getNewsId());
    }

    @Override
    public int create(News news) {
        return sqlSession.insert(namespace + ".create", news);
    }

    @Override
    public int update(News news) {
        return sqlSession.update(namespace + ".update", news);
    }

    @Override
    public int delete(News news) {
        return sqlSession.delete(namespace + ".delete", news.getNewsId());
    }

    @Override
    public List<News> getBlogsByPage(int index, int size) {
        return null;
    }
}
