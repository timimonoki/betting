package application;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by NegrutiA on 3/17/2017.
 */

@EnableTransactionManagement
@Configuration
public class Config {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUser("root");
        ds.setPassword("root");
        ds.setDatabaseName("betting_db");
        return ds;
    }

}
