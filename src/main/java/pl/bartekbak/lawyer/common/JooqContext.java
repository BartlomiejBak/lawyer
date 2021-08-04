package pl.bartekbak.lawyer.common;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JooqContext {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    private final DSLContext context;

    public JooqContext() throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        this.context = DSL.using(conn, SQLDialect.POSTGRES);
    }

    public DSLContext dsl() {
        return this.context;
    }
}
