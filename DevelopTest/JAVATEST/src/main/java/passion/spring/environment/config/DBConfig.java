package passion.spring.environment.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DBConfig { // dataSource bean 객체 설정 및 생성
    // @Component : 개발자가 정의한 클래스로 부터 Bean 객체를 생성하는 애노테이션
    // DataSource 클래스형의 Bean 객체를 생성하는 애노테이션
    // 이름을 지정하여 Bean Id는 지정한 이름으로 등록됨
    // 이름을 지정하지 않는 경우 메소드 이름, getHikariDataSource로 등록됨


//
//        @Bean(name="dataSource1", destroyMethod="close")
//        public HikariDataSource getHikariDataSourciConfig() {
//            hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
//            hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
//            hikariConfig.setUsername("system");
//            //hikariConfig.setMaximumPoolSize(5);
//            //hikariConfig.setConnectionTestQuery("SELECT 1");
//            //hikariConfig.setPoolName("springHikariCP");
//            hikariConfig.setPassword("cometrue");
//
//            return new HikariDataSource(hikariConfig);
//        }


    //    @Bean("dateSource2")
//    @ConfigurationProperties(prefix="spring.datasource.hikari")
//    public DataSource getDataSourceBuilder() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        return dataSourceBuilder.build();
//    }

    @Bean(name = "dataSource3")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSourceBuilderUrl() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        // url -> jdbc-url로 변경하는 경우 생략 가능
        dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1521:XE");
        return dataSourceBuilder.build();
    }

    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;


    private final Environment environment;

    @Autowired
    public DBConfig(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean(name = "dataSource4")
    public DataSource getDriverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));    // "jdbc:oracle:thin:@localhost:1521:XE"
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

//    @Bean("dataSource5")
//    public DataSource getSimpleDriverDataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
//        dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
//        dataSource.setUsername("system");
//        dataSource.setPassword("cometrue");
//        return dataSource;
//    }
}
