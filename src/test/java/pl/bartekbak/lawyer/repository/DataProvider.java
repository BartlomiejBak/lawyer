package pl.bartekbak.lawyer.repository;

import com.github.javafaker.Faker;
import org.jooq.DSLContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_ADDRESS;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_EVENT;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_NOTE;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_PAYMENT;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_POA;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_TAG;

public class DataProvider {

    private final DSLContext context;
    private final Faker faker = Faker.instance(new Locale("pl"));

    public static UUID TAG_ID = UUID.randomUUID();
    public static UUID NOTE_ID = UUID.randomUUID();
    public static UUID POA_ID = UUID.randomUUID();
    public static UUID PAYMENT_ID = UUID.randomUUID();
    public static UUID ADDRESS_ID = UUID.randomUUID();
    public static UUID EVENT_ID = UUID.randomUUID();

    public DataProvider(DSLContext context) {
        this.context = context;
    }

    public void initStandardScenario() {
        addRandomTag(TAG_ID);
        addRandomTag();
        addRandomTag();
        addRandomTag();
        addRandomTag();
        addRandomTag();

        addRandomNote(NOTE_ID);
        addRandomNote();
        addRandomNote();
        addRandomNote();
        addRandomNote();
        addRandomNote();

        addRandomPoa(POA_ID);
        addRandomPoa();
        addRandomPoa();
        addRandomPoa();
        addRandomPoa();
        addRandomPoa();

        addRandomPayment(PAYMENT_ID);
        addRandomPayment();
        addRandomPayment();
        addRandomPayment();
        addRandomPayment();
        addRandomPayment();

        addRandomAddress(ADDRESS_ID);
        addRandomAddress();
        addRandomAddress();
        addRandomAddress();
        addRandomAddress();
        addRandomAddress();

        addRandomEvent(EVENT_ID);
        addRandomEvent();
        addRandomEvent();
        addRandomEvent();
        addRandomEvent();
        addRandomEvent();
    }

    public void addRandomTag() {
        addRandomTag(UUID.randomUUID());
    }

    @Transactional
    public void addRandomTag(UUID id) {
        context.insertInto(DB_TAG)
                .set(DB_TAG.TAG_ID, id)
                .set(DB_TAG.NAME, faker.dog().name())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public void addRandomNote() {
        addRandomNote(UUID.randomUUID());
    }

    @Transactional
    public void addRandomNote(UUID id) {
        context.insertInto(DB_NOTE)
                .set(DB_NOTE.NOTE_ID, id)
                .set(DB_NOTE.TITLE, faker.book().title())
                .set(DB_NOTE.TEXT, faker.lorem().paragraph())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public void addRandomPoa() {
        addRandomPoa(UUID.randomUUID());
    }

    @Transactional
    public void addRandomPoa(UUID id) {
        var date = LocalDate.ofInstant(faker.date().past(10, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault());
        context.insertInto(DB_POA)
                .set(DB_POA.POA_ID, id)
                .set(DB_POA.TYPE, faker.name().firstName())
                .set(DB_POA.PAYMENT, faker.numerify("123456"))
                .set(DB_POA.KPC, faker.bool().bool())
                .set(DB_POA.TERMINATION, faker.bool().bool())
                .set(DB_POA.START_DATE, date)
                .set(DB_POA.END_DATE, date.plusDays(faker.number().randomDigitNotZero()))
                .set(DB_POA.NOTIFICATION_DUTY, true)
                .set(DB_POA.DUTY_COMPLETED, faker.bool().bool())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public void addRandomPayment() {
        addRandomPayment(UUID.randomUUID());
    }

    @Transactional
    public void addRandomPayment(UUID id) {
        var date = LocalDate.ofInstant(faker.date().past(10, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault());
        context.insertInto(DB_PAYMENT)
                .set(DB_PAYMENT.PAYMENT_ID, id)
                .set(DB_PAYMENT.PAYMENT_VALUE, faker.number().randomDouble(2, 1, Integer.MAX_VALUE))
                .set(DB_PAYMENT.PAYMENT_DATE, date)
                .set(DB_PAYMENT.PAID, true)
                .set(DB_PAYMENT.PAID_DATE, date.plusDays(30))
                .set(DB_PAYMENT.COMMENT, faker.lordOfTheRings().location())
                .set(DB_PAYMENT.US, faker.bool().bool())
                .set(DB_PAYMENT.INCOMING, faker.bool().bool())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public void addRandomAddress() {
        addRandomAddress(UUID.randomUUID());
    }

    @Transactional
    public void addRandomAddress(UUID id) {
        context.insertInto(DB_ADDRESS)
                .set(DB_ADDRESS.ADDRESS_ID, id)
                .set(DB_ADDRESS.STREET, faker.address().streetName())
                .set(DB_ADDRESS.ZIP_CODE, faker.address().zipCode())
                .set(DB_ADDRESS.CITY, faker.lordOfTheRings().location())
                .set(DB_ADDRESS.COUNTRY, faker.address().country())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public void addRandomEvent() {
        addRandomEvent(UUID.randomUUID());
    }

    @Transactional
    public void addRandomEvent(UUID id) {
        var date = LocalDateTime.ofInstant(faker.date().future(100, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault());
        context.insertInto(DB_EVENT)
                .set(DB_EVENT.EVENT_ID, id)
                .set(DB_EVENT.TITLE, faker.book().title())
                .set(DB_EVENT.DATE_TIME, date)
                .set(DB_EVENT.DESCRIPTION, faker.lorem().paragraph())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Transactional
    public void clearDatabase() {
        context.deleteFrom(DB_TAG).execute();
        context.deleteFrom(DB_NOTE).execute();
        context.deleteFrom(DB_POA).execute();
        context.deleteFrom(DB_PAYMENT).execute();
        context.deleteFrom(DB_ADDRESS).execute();
        context.deleteFrom(DB_EVENT).execute();
    }
}
