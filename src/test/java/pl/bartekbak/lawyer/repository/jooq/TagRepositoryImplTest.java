package pl.bartekbak.lawyer.repository.jooq;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bartekbak.lawyer.common.PostgreSQLJooqContainer;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.repository.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class TagRepositoryImplTest {

    @Container
    public static PostgreSQLJooqContainer container = new PostgreSQLJooqContainer();
    private Faker faker = Faker.instance();

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

    @Test
    void should_return_optional_of_existing_tag() {
        // given
        int tagId = 1;

        // when
        final var result = repository.tagById(tagId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getTagId()).isEqualTo(tagId);
    }

    @Test
    void should_return_empty_optional() {
        // given
        int tagId = Integer.MAX_VALUE;

        // when
        final var result = repository.tagById(tagId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_add_tag_when_not_exists() {
        // given
        int givenId = Integer.MAX_VALUE;
        String name = faker.dog().name();
        Tag givenTag = Tag.builder().tagId(givenId).name(name).build();

        // when
        repository.add(givenTag);
        var result = repository.tagById(givenId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(name);
    }

    @Test
    void should_do_nothing_when_adding_non_unique_value() {
        // given
        int givenId = Integer.MAX_VALUE;
        int additionalId = Integer.MAX_VALUE - 1;
        String name = faker.dog().name();
        Tag givenTag = Tag.builder().tagId(givenId).name(name).build();
        Tag duplicateTag = Tag.builder().tagId(additionalId).name(name).build();

        // when
        repository.add(givenTag);
        repository.add(duplicateTag);
        var result = repository.tagById(additionalId);

        // then
        assertThat(result).isEmpty();
    }

}
