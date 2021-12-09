package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class PaymentRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private Faker faker = Faker.instance();

    static DataProvider provider;
    static PaymentRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new PaymentRepositoryImpl(container.dsl());
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
    void should_return_optional_of_existing_payment() {
        // given
        int paymentId = DataProvider.PAYMENT_ID;

        // when
        final var result = repository.paymentById(paymentId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getPaymentId()).isEqualTo(paymentId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        int paymentId = Integer.MAX_VALUE;

        // when
        final var result = repository.paymentById(paymentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_payment_when_not_exists() {
        // given
        int givenId = Integer.MAX_VALUE;
        String comment = faker.dog().name();
        var givenPayment = Payment.builder().paymentId(givenId).comment(comment).build();

        // when
        repository.add(givenPayment);
        var result = repository.paymentById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getComment()).isEqualTo(comment);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        int givenId = Integer.MAX_VALUE;
        String comment = faker.dog().name();
        String newComment = faker.cat().name();
        var givenPayment = Payment.builder().paymentId(givenId).comment(comment).build();
        var duplicatePayment = Payment.builder().paymentId(givenId).comment(newComment).build();

        // when
        repository.add(givenPayment);
        repository.add(duplicatePayment);
        var result = repository.paymentById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getComment()).isEqualTo(comment);
    }

    @Test
    void should_update_value_when_payment_exists() {
        // given
        int givenId = DataProvider.PAYMENT_ID;
        String comment = faker.cat().name();
        var givenPayment = Payment.builder().paymentId(givenId).comment(comment).build();

        // when
        repository.update(givenPayment);
        var result = repository.paymentById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getComment()).isEqualTo(comment);
    }

    @Test
    void should_do_nothing_when_payment_not_exists() {
        // given
        int givenId = Integer.MAX_VALUE;
        String comment = faker.cat().name();
        var givenPayment = Payment.builder().paymentId(givenId).comment(comment).build();

        // when
        repository.update(givenPayment);
        var result = repository.paymentById(givenId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_delete_record_when_exists() {
        // given
        int givenId = DataProvider.PAYMENT_ID;
        var beforeDelete = repository.paymentById(givenId);

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.paymentById(givenId);

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
