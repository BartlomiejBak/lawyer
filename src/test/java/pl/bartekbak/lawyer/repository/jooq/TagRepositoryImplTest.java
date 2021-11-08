package pl.bartekbak.lawyer.repository.jooq;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class TagRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();

    static DataProvider provider;
    static TagRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new TagRepositoryImpl(container.dsl());
    }

    @BeforeEach
    void setUp() {
        provider.initStandardScenario();
    }

    @AfterEach
    void tearDown() {
        provider.clearDatabase();
    }

    @Test
    void should_return_initial_list() {
        // given

        // when
        final var result = repository.list();
        // then
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    void should_return_empty_list_when_database_cleared() {
        // given
        provider.clearDatabase();
        // when
        final var result = repository.list();
        // then
        assertThat(result).isEmpty();
    }
}
