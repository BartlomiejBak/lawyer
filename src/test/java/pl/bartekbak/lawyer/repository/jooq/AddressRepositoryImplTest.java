package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.repository.DataProvider;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class AddressRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private final Faker faker = Faker.instance();

    static DataProvider provider;
    static AddressRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new AddressRepositoryImpl(container.dsl());
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
    void should_return_optional_of_existing_address() {
        // given
        var addressId = DataProvider.ADDRESS_ID;

        // when
        final var result = repository.addressById(addressId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getAddressId()).isEqualTo(addressId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        var addressId = UUID.randomUUID();

        // when
        final var result = repository.addressById(addressId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_address_when_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        var givenAddress = Address.builder()
                .addressId(givenId)
                .street(faker.address().streetName())
                .city(faker.address().city())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .build();

        // when
        repository.add(givenAddress);
        var result = repository.addressById(givenId);

        // then
        assertThat(result).isPresent().contains(givenAddress);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        var givenId = UUID.randomUUID();
        var duplicateId = UUID.randomUUID();

        var givenAddress = Address.builder()
                .addressId(givenId)
                .street(faker.address().streetName())
                .city(faker.address().city())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .build();
        var duplicateAddress = Address.builder()
                .addressId(duplicateId)
                .street(givenAddress.getStreet())
                .city(givenAddress.getCity())
                .country(givenAddress.getCountry())
                .zipCode(givenAddress.getZipCode())
                .build();

        // when
        repository.add(givenAddress);
        repository.add(duplicateAddress);
        var result = repository.addressById(duplicateId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_update_value_when_address_exists() {
        // given
        var givenId = DataProvider.ADDRESS_ID;
        var givenAddress = Address.builder()
                .addressId(givenId)
                .street(faker.address().streetName())
                .city(faker.address().city())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .build();

        // when
        repository.update(givenAddress);
        var result = repository.addressById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(givenAddress);
    }

    @Test
    void should_do_nothing_when_update_duplicates_value() {
        // given
        var givenId = UUID.randomUUID();
        var updatedId = DataProvider.ADDRESS_ID;

        var givenAddress = Address.builder()
                .addressId(givenId)
                .street(faker.address().streetName())
                .city(faker.address().city())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .build();
        var duplicateAddress = Address.builder()
                .addressId(updatedId)
                .street(givenAddress.getStreet())
                .city(givenAddress.getCity())
                .country(givenAddress.getCountry())
                .zipCode(givenAddress.getZipCode())
                .build();

        // when
        repository.add(givenAddress);
        repository.update(duplicateAddress);
        var result = repository.addressById(updatedId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isNotEqualTo(duplicateAddress);
    }

    @Test
    void should_do_nothing_when_address_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        var givenAddress = Address.builder()
                .addressId(givenId)
                .street(faker.address().streetName())
                .city(faker.address().city())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .build();

        // when
        repository.update(givenAddress);
        var result = repository.addressById(givenId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_delete_record_when_exists() {
        // given
        var givenId = DataProvider.ADDRESS_ID;
        var beforeDelete = repository.addressById(givenId);

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.addressById(givenId);

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
