package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.repository.DataProvider;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class TaskRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private final Faker faker = Faker.instance();

    static DataProvider provider;
    static TaskRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new TaskRepositoryImpl(container.dsl());
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

    @Test
    void should_return_optional_of_existing_record() {
        // given
        var recordId = DataProvider.TASK_ID;

        // when
        final var result = repository.taskById(recordId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getTaskId()).isEqualTo(recordId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        var recordId = UUID.randomUUID();

        // when
        final var result = repository.taskById(recordId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_record_when_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String description = faker.dog().name();
        var givenRecord = Task.builder().taskId(givenId).description(description).build();

        // when
        repository.add(givenRecord);
        var result = repository.taskById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo(description);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        var givenId = UUID.randomUUID();
        String description = faker.dog().name();
        String duplicateDescription = faker.dog().name();
        var givenRecord = Task.builder().taskId(givenId).description(description).build();
        var duplicateRecord = Task.builder().taskId(givenId).description(duplicateDescription).build();

        // when
        repository.add(givenRecord);
        repository.add(duplicateRecord);
        var result = repository.taskById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getDescription()).isEqualTo(description);
    }

    @Test
    void should_update_value_when_record_exists() {
        // given
        var givenId = DataProvider.TASK_ID;
        String description = faker.cat().name();
        var givenRecord = Task.builder().taskId(givenId).description(description).build();

        // when
        repository.update(givenRecord);
        var result = repository.taskById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getDescription()).isEqualTo(description);
    }

    @Test
    void should_do_nothing_when_record_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String description = faker.cat().name();
        var givenRecord = Task.builder().taskId(givenId).description(description).build();

        // when
        repository.update(givenRecord);
        var result = repository.taskById(givenId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_delete_record_when_exists() {
        // given
        var givenId = DataProvider.TASK_ID;
        var beforeDelete = repository.taskById(givenId);

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.taskById(givenId);

        // then
        assertThat(beforeDelete).isPresent();
        assertThat(afterDelete).isEmpty();
    }

    @Test
    void should_do_nothing_when_delete_nonexistent_record() {
        // given
        var givenId = UUID.randomUUID();
        var beforeDelete = repository.list();

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.list();

        // then
        assertThat(beforeDelete.size()).isEqualTo(afterDelete.size());
    }

}
