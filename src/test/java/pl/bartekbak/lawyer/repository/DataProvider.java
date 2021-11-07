package pl.bartekbak.lawyer.repository;

import com.github.javafaker.Faker;
import org.jooq.DSLContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_TAG;

public class DataProvider {

    private final DSLContext context;
    private final Faker faker = Faker.instance(new Locale("pl"));

    public DataProvider(DSLContext context) {
        this.context = context;
    }

    public void initStandardScenario() {
        addRandomTag(1);
        addRandomTag(2);
        addRandomTag(3);
        addRandomTag(4);
        addRandomTag(5);
        addRandomTag(6);
    }

    @Transactional
    public void addRandomTag(int id) {
        context.insertInto(DB_TAG)
                .set(DB_TAG.TAG_ID, id)
                .set(DB_TAG.NAME, faker.dog().name())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Transactional
    public void clearDatabase() {
        context.deleteFrom(DB_TAG).execute();
    }
}
