package pl.bartekbak.lawyer.repository;

import com.github.javafaker.Faker;
import org.jooq.DSLContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
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

    public static int TAG_ID = 1;
    public static int NOTE_ID = 7;
    public static int POA_ID = 13;
    public static int PAYMENT_ID = 19;
    public static int ADDRESS_ID = 25;
    public static int EVENT_ID = 31;

    public DataProvider(DSLContext context) {
        this.context = context;
    }

    public void initStandardScenario() {
        addRandomTag(TAG_ID);
        addRandomTag(2);
        addRandomTag(3);
        addRandomTag(4);
        addRandomTag(5);
        addRandomTag(6);

        addRandomNote(NOTE_ID);
        addRandomNote(8);
        addRandomNote(9);
        addRandomNote(10);
        addRandomNote(11);
        addRandomNote(12);

        addRandomPoa(POA_ID);
        addRandomPoa(14);
        addRandomPoa(15);
        addRandomPoa(16);
        addRandomPoa(17);
        addRandomPoa(18);

        addRandomPayment(PAYMENT_ID);
        addRandomPayment(20);
        addRandomPayment(21);
        addRandomPayment(22);
        addRandomPayment(23);
        addRandomPayment(24);

        addRandomAddress(ADDRESS_ID);
        addRandomAddress(26);
        addRandomAddress(27);
        addRandomAddress(28);
        addRandomAddress(29);
        addRandomAddress(30);

        addRandomEvent(EVENT_ID);
        addRandomEvent(32);
        addRandomEvent(33);
        addRandomEvent(34);
        addRandomEvent(35);
        addRandomEvent(36);
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
    public void addRandomNote(int id) {
        context.insertInto(DB_NOTE)
                .set(DB_NOTE.NOTE_ID, id)
                .set(DB_NOTE.TITLE, faker.book().title())
                .set(DB_NOTE.TEXT, faker.lorem().paragraph())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Transactional
    public void addRandomPoa(int id) {
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
                .set(DB_POA.NOTIFICATION_DUTY, faker.bool().bool())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Transactional
    public void addRandomPayment(int id) {
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

    @Transactional
    public void addRandomAddress(int id) {
        context.insertInto(DB_ADDRESS)
                .set(DB_ADDRESS.ADDRESS_ID, id)
                .set(DB_ADDRESS.STREET, faker.address().streetName())
                .set(DB_ADDRESS.ZIP_CODE, faker.address().zipCode())
                .set(DB_ADDRESS.CITY, faker.lordOfTheRings().location())
                .set(DB_ADDRESS.COUNTRY, faker.address().country())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Transactional
    public void addRandomEvent(int id) {
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
