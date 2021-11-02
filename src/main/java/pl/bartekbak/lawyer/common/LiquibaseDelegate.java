package pl.bartekbak.lawyer.common;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

import java.sql.SQLException;

public class LiquibaseDelegate extends JdbcDatabaseDelegate {

    public LiquibaseDelegate(JdbcDatabaseContainer<PostgreSQLJooqContainer> container) {
        super(container, "");
    }

    void executeChangelog(String changelogFile) {
        try (var liquibase = new Liquibase(changelogFile,
                new ClassLoaderResourceAccessor(),
                DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                        new JdbcConnection(getConnection().getConnection())))) {
            liquibase.update((String) null);
        } catch (LiquibaseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
