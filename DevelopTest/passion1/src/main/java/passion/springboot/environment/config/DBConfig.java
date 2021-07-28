package passion.springboot.environment.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:db.properties")
// @MapperScan("iducs.springboot.blog.mapper")
public class DBConfig { //data Source bean 객체 설정 및 생성
    /*
    @Bean("dataSource2")
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public DataSource getDataSourceBuilder() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }
    */

    @Primary
    @Bean(name = "dataSource1", destroyMethod = "close")
    public HikariDataSource getHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setUsername("system");
        hikariConfig.setPassword("cometrue");
        //hikariConfig.setMaximumPoolSize(5);
        //hikariConfig.setConnectionTestQuery("SELECT 1");
        //hikariConfig.setPoolName("springHikariCP");
        return new HikariDataSource(hikariConfig);
    }
}