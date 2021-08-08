package pl.bartekbak.lawyer.configuration;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class JooqConfiguration {

    private final Environment env;

    public JooqConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DSLContext dslContext(Connection connection) {
        return DSL.using(connection, SQLDialect.POSTGRES);
    }

    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection(
                env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password")
        );
    }

}
