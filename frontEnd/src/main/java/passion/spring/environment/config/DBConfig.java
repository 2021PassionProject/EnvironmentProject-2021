package passion.spring.environment.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DBConfig { // dataSource bean 객체 설정 및 생성
    // @Component : 개발자가 정의한 클래스로 부터 Bean 객체를 생성하는 애노테이션
    // DataSource 클래스형의 Bean 객체를 생성하는 애노테이션
    // 이름을 지정하여 Bean Id는 지정한 이름으로 등록됨
    // 이름을 지정하지 않는 경우 메소드 이름, getHikariDataSource로 등록됨

    @Bean("dateSource2")
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public DataSource getDataSourceBuilder() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

    @Primary
    @Bean(name="dataSource1", destroyMethod="close")
    public HikariDataSource getHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setUsername("system");
        //hikariConfig.setMaximumPoolSize(5);
        //hikariConfig.setConnectionTestQuery("SELECT 1");
        //hikariConfig.setPoolName("springHikariCP");
        hikariConfig.setPassword("cometrue");

        return new HikariDataSource(hikariConfig);
    }
}
