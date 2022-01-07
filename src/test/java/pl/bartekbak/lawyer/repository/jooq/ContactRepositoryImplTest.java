package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.repository.DataProvider;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class ContactRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private final Faker faker = Faker.instance();

    static DataProvider provider;
    static ContactRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new ContactRepositoryImpl(container.dsl());
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
        var recordId = DataProvider.CONTACT_ID;

        // when
        final var result = repository.contactById(recordId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getContactId()).isEqualTo(recordId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        var recordId = UUID.randomUUID();

        // when
        final var result = repository.contactById(recordId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_record_when_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String name = faker.dog().name();
        var givenRecord = Contact.builder().contactId(givenId).name(name).build();

        // when
        repository.add(givenRecord);
        var result = repository.contactById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(name);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        var givenId = UUID.randomUUID();
        var additionalId = UUID.randomUUID();
        String name = faker.dog().name();
        var givenRecord = Contact.builder().contactId(givenId).name(name).build();
        var duplicateRecord = Contact.builder().contactId(additionalId).name(name).build();

        // when
        repository.add(givenRecord);
        repository.add(duplicateRecord);
        var result = repository.contactById(additionalId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_update_value_when_record_exists() {
        // given
        var givenId = DataProvider.CONTACT_ID;
        String name = faker.cat().name();
        var givenRecord = Contact.builder().contactId(givenId).name(name).build();

        // when
        repository.update(givenRecord);
        var result = repository.contactById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isEqualTo(name);
    }

    @Test
    void should_do_nothing_when_update_duplicates_value() {
        // given
        var givenId = UUID.randomUUID();
        var updatedId = DataProvider.CONTACT_ID;
        String name = faker.dog().name();
        var givenRecord = Contact.builder().contactId(givenId).name(name).build();
        var updatedRecord = Contact.builder().contactId(updatedId).name(name).build();

        // when
        repository.add(givenRecord);
        repository.update(updatedRecord);
        var result = repository.contactById(updatedId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isNotEqualTo(name);
    }

    @Test
    void should_do_nothing_when_record_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String name = faker.cat().name();
        var givenRecord = Contact.builder().contactId(givenId).name(name).build();

        // when
        repository.update(givenRecord);
        var result = repository.contactById(givenId);

        // then
        assertThat(result).isEmpty();
    }

}
