package pl.bartekbak.lawyer.common;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.testcontainers.containers.PostgreSQLContainer;
import pl.bartekbak.lawyer.exceptions.InitializationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.SQLDialect.POSTGRES;

public class PostgreSQLJooqContainer extends PostgreSQLContainer<PostgreSQLJooqContainer> {

    private static final String POSTGRES_VERSION = "postgres:14-alpine";
    private final String changelogPath = "classpath:db/changelog/db.changelog.sql";

    private DSLContext dsl;

    public PostgreSQLJooqContainer() {
        super(POSTGRES_VERSION);
    }

    public DSLContext dsl() {
        return dsl;
    }

    @Override
    protected void containerIsStarted(InspectContainerResponse containerInfo) {
        super.containerIsStarted(containerInfo);
        this.dsl = initDsl(initConnection());
        getDelegate().executeChangelog(changelogPath);
    }

    private LiquibaseDelegate getDelegate() {
        return new LiquibaseDelegate(self());
    }

    private Connection initConnection() {
        try {
            return DriverManager.getConnection(super.getJdbcUrl(), super.getUsername(), super.getPassword());
        } catch (SQLException e) {
            throw new InitializationException("initialization error");
        }
    }

    private DSLContext initDsl(Connection connection) {
        return DSL.using(connection, POSTGRES);
    }
}
