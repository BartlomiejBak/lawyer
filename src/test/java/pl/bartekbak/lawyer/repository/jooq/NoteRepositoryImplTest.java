package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.repository.DataProvider;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class NoteRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private final Faker faker = Faker.instance();

    static DataProvider provider;
    static NoteRepositoryImpl repository;

    @BeforeAll
    static void beforeAll() {
        provider = new DataProvider(container.dsl());
        repository = new NoteRepositoryImpl(container.dsl());
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
    void should_return_optional_of_existing_note() {
        // given
        var noteId = DataProvider.NOTE_ID;

        // when
        final var result = repository.noteById(noteId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getNoteId()).isEqualTo(noteId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        var noteId = UUID.randomUUID();

        // when
        final var result = repository.noteById(noteId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_note_when_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String title = faker.book().title();
        String text = faker.lorem().paragraph();
        var givenNote = Note.builder().noteId(givenId)
                .title(title)
                .text(text)
                .build();

        // when
        repository.add(givenNote);
        var result = repository.noteById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo(title);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        var givenId = UUID.randomUUID();
        var additionalId = UUID.randomUUID();
        String title = faker.dog().name();
        var givenNote = Note.builder().noteId(givenId).title(title).text(Strings.EMPTY).build();
        var duplicateNote = Note.builder().noteId(additionalId).title(title).text(Strings.EMPTY).build();

        // when
        repository.add(givenNote);
        repository.add(duplicateNote);
        var result = repository.noteById(additionalId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_update_value_when_note_exists() {
        // given
        var givenId = DataProvider.NOTE_ID;
        String title = faker.cat().name();
        var givenNote = Note.builder().noteId(givenId).title(title).build();

        // when
        repository.update(givenNote);
        var result = repository.noteById(givenId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getTitle()).isEqualTo(title);
    }

    @Test
    void should_do_nothing_when_update_duplicates_value() {
        // given
        var givenId = UUID.randomUUID();
        var updatedId = DataProvider.NOTE_ID;
        String title = faker.dog().name();
        var givenNote = Note.builder().noteId(givenId).title(title).build();
        var updatedNote = Note.builder().noteId(updatedId).title(title).build();

        // when
        repository.add(givenNote);
        repository.update(updatedNote);
        var result = repository.noteById(updatedId);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getTitle()).isNotEqualTo(title);
    }

    @Test
    void should_do_nothing_when_note_not_exists() {
        // given
        var givenId = UUID.randomUUID();
        String name = faker.cat().name();
        var givenNote = Note.builder().noteId(givenId).title(name).build();

        // when
        repository.update(givenNote);
        var result = repository.noteById(givenId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_delete_record_when_exists() {
        // given
        var givenId = DataProvider.NOTE_ID;
        var beforeDelete = repository.noteById(givenId);

        // when
        repository.deleteById(givenId);
        var afterDelete = repository.noteById(givenId);

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
