package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class PoaRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private Faker faker = Faker.instance();

    static DataProvider provider;
    static PoaRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new PoaRepositoryImpl(container.dsl());
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
    void should_return_optional_of_existing_poa() {
        // given
        int poaId = DataProvider.POA_ID;

        // when
        final var result = repository.poaById(poaId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getPoaId()).isEqualTo(poaId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        int poaId = Integer.MAX_VALUE;

        // when
        final var result = repository.poaById(poaId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_poa_when_not_exists() {
        // given
        int givenId = Integer.MAX_VALUE;
        String type = faker.dog().name();
        var givenpoa = Poa.builder().poaId(givenId).type(type).build();

        // when
        repository.add(givenpoa);
        var result = repository.poaById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getType()).isEqualTo(type);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        int givenId = Integer.MAX_VALUE;
        String name = faker.dog().name();
        String newName = faker.cat().name();
        var givenPoa = Poa.builder().poaId(givenId).type(name).build();
        var duplicatePoa = Poa.builder().poaId(givenId).type(newName).build();

        // when
        repository.add(givenPoa);
        repository.add(duplicatePoa);
        var result = repository.poaById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getType()).isEqualTo(name);
    }

    @Test
    void should_update_value_when_poa_exists() {
        // given
        int givenId = DataProvider.POA_ID;
        String type = faker.cat().name();
        var givenPoa = Poa.builder().poaId(givenId).type(type).build();

        // when
        repository.update(givenPoa);
        var result = repository.poaById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getType()).isEqualTo(type);
    }

    @Test
    void should_do_nothing_when_poa_not_exists() {
        // given
        int givenId = Integer.MAX_VALUE;
        String name = faker.cat().name();
        var givenPoa = Poa.builder().poaId(givenId).type(name).build();

        // when
        repository.update(givenPoa);
        var result = repository.poaById(givenId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_delete_record_when_exists() {
        // given
        int givenId = DataProvider.POA_ID;
        var beforeDelete = repository.poaById(givenId);

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.poaById(givenId);

        // then
        assertThat(beforeDelete).isPresent();
        assertThat(afterDelete).isEmpty();
    }

    @Test
    void should_do_nothing_when_delete_nonexistent_record() {
        // given
        int givenId = Integer.MAX_VALUE;
        var beforeDelete = repository.list();

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.list();

        // then
        assertThat(beforeDelete.size()).isEqualTo(afterDelete.size());
    }
    
}
