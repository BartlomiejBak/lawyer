package pl.bartekbak.lawyer.repository;

import com.github.javafaker.Faker;
import org.jooq.DSLContext;

import java.util.Locale;

public class DataProvider {

    private final DSLContext context;
    private final Faker faker = Faker.instance(new Locale("pl"));

    public DataProvider(DSLContext context) {
        this.context = context;
    }

    void initStandardScenario() {

    }
}
