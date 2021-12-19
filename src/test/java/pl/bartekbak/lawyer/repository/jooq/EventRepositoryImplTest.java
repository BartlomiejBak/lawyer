package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.repository.DataProvider;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class EventRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private Faker faker = Faker.instance();

    static DataProvider provider;
    static EventRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new EventRepositoryImpl(container.dsl());
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
    void should_return_optional_of_existing_event() {
        // given
        int eventId = DataProvider.EVENT_ID;

        // when
        final var result = repository.eventById(eventId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getEventId()).isEqualTo(eventId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        int eventId = Integer.MAX_VALUE;

        // when
        final var result = repository.eventById(eventId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_event_when_not_exists() {
        // given
        int givenId = Integer.MAX_VALUE;
        var givenEvent = Event.builder()
                .eventId(givenId)
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .dateTime(LocalDateTime.now().plus(100, ChronoUnit.DAYS))
                .build();

        // when
        repository.add(givenEvent);
        var result = repository.eventById(givenId);

        // then
        assertThat(result).isPresent().contains(givenEvent);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        int givenId = Integer.MAX_VALUE;
        int duplicateId = givenId - 1;

        var givenEvent = Event.builder()
                .eventId(givenId)
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .dateTime(LocalDateTime.now().plus(100, ChronoUnit.DAYS))
                .build();
        var duplicateEvent = Event.builder()
                .eventId(givenId)
                .title(givenEvent.getTitle())
                .description(givenEvent.getDescription())
                .dateTime(givenEvent.getDateTime())
                .build();

        // when
        repository.add(givenEvent);
        repository.add(duplicateEvent);
        var result = repository.eventById(duplicateId);

        // then
        assertThat(result).isEmpty();
    }
}
