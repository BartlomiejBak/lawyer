package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.repository.DataProvider;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class LawsuitRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private final Faker faker = Faker.instance();

    static DataProvider provider;
    static LawsuitRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new LawsuitRepositoryImpl(container.dsl());
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
        var recordId = DataProvider.LAWSUIT_ID;

        // when
        final var result = repository.lawsuitById(recordId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getLawsuitId()).isEqualTo(recordId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        var recordId = UUID.randomUUID();

        // when
        final var result = repository.lawsuitById(recordId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_record_when_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String signature = faker.dog().name();
        var givenRecord = Lawsuit.builder().lawsuitId(givenId).signature(signature).build();

        // when
        repository.add(givenRecord);
        var result = repository.lawsuitById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getSignature()).isEqualTo(signature);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        var givenId = UUID.randomUUID();
        var additionalId = UUID.randomUUID();
        String signature = faker.dog().name();
        var givenRecord = Lawsuit.builder().lawsuitId(givenId).signature(signature).build();
        var duplicateRecord = Lawsuit.builder().lawsuitId(additionalId).signature(signature).build();

        // when
        repository.add(givenRecord);
        repository.add(duplicateRecord);
        var result = repository.lawsuitById(additionalId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_update_value_when_record_exists() {
        // given
        var givenId = DataProvider.LAWSUIT_ID;
        String signature = faker.cat().name();
        var givenRecord = Lawsuit.builder().lawsuitId(givenId).signature(signature).build();

        // when
        repository.update(givenRecord);
        var result = repository.lawsuitById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getSignature()).isEqualTo(signature);
    }

    @Test
    void should_do_nothing_when_update_duplicates_value() {
        // given
        var givenId = UUID.randomUUID();
        var updatedId = DataProvider.LAWSUIT_ID;
        String signature = faker.dog().name();
        var givenRecord = Lawsuit.builder().lawsuitId(givenId).signature(signature).build();
        var updatedRecord = Lawsuit.builder().lawsuitId(updatedId).signature(signature).build();

        // when
        repository.add(givenRecord);
        repository.update(updatedRecord);
        var result = repository.lawsuitById(updatedId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isNotEqualTo(signature);
    }

    @Test
    void should_add_record_when_record_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String signature = faker.cat().name();
        var initialResult = repository.lawsuitById(givenId);
        var givenRecord = Lawsuit.builder().lawsuitId(givenId).signature(signature).build();

        // when
        repository.update(givenRecord);
        var result = repository.lawsuitById(givenId);

        // then
        assertThat(initialResult).isEmpty();
        assertThat(result).isPresent();
    }

    @Test
    void should_delete_record_when_exists() {
        // given
        var givenId = DataProvider.LAWSUIT_ID;
        var beforeDelete = repository.lawsuitById(givenId);

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.lawsuitById(givenId);

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
