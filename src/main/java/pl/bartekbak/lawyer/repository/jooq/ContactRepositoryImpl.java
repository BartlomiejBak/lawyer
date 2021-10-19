package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.generate.jooq.tables.DbAddress;
import pl.bartekbak.lawyer.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_ADDRESS;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_CONTACT_ADDRESS;
import static pl.bartekbak.lawyer.generate.jooq.tables.DbContact.DB_CONTACT;

@Repository
public class ContactRepositoryImpl extends DatabaseContext implements ContactRepository {

    private final DbAddress secondary = DB_ADDRESS.as("secondary");

    public ContactRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Contact> list() {
        return dslContext().select(
                        DB_CONTACT.asterisk(),
                        field(
                                select(jsonObject(DB_ADDRESS.fields()))
                                        .from(DB_ADDRESS)
                                        .join(DB_CONTACT_ADDRESS)
                                        .on(DB_ADDRESS.ADDRESS_ID.eq(DB_CONTACT_ADDRESS.ADDRESS))
                                        .where(DB_CONTACT_ADDRESS.CONTACT.eq(DB_CONTACT.CONTACT_ID))
                        )
                                .as("address"),
                        field(
                                select(jsonObject(secondary.fields()))
                                        .from(secondary)
                                        .join(DB_CONTACT_ADDRESS)
                                        .on(secondary.ADDRESS_ID.eq(DB_CONTACT_ADDRESS.ADDRESS))
                                        .where(DB_CONTACT_ADDRESS.CONTACT.eq(DB_CONTACT.CONTACT_ID))
                        )
                                .as("correspondenceAddress")
                )
                .from(DB_CONTACT)
                .fetchInto(Contact.class);
    }

    @Override
    public Optional<Contact> contactById(int id) {
        return Optional.ofNullable(dslContext().select(
                        DB_CONTACT.asterisk(),
                        field(
                                select(jsonObject(DB_ADDRESS.fields()))
                                        .from(DB_ADDRESS)
                                        .join(DB_CONTACT_ADDRESS)
                                        .on(DB_ADDRESS.ADDRESS_ID.eq(DB_CONTACT_ADDRESS.ADDRESS))
                                        .where(DB_CONTACT_ADDRESS.CONTACT.eq(DB_CONTACT.CONTACT_ID))
                                        .and(DB_CONTACT_ADDRESS.TYPE.eq("primary"))
                        )
                                .as("address"),
                        field(
                                select(jsonObject(secondary.fields()))
                                        .from(secondary)
                                        .join(DB_CONTACT_ADDRESS)
                                        .on(secondary.ADDRESS_ID.eq(DB_CONTACT_ADDRESS.ADDRESS))
                                        .where(DB_CONTACT_ADDRESS.CONTACT.eq(DB_CONTACT.CONTACT_ID))
                                        .and(DB_CONTACT_ADDRESS.TYPE.eq("secondary"))
                        )
                                .as("correspondenceAddress")
                )
                .from(DB_CONTACT)
                .where(DB_CONTACT.CONTACT_ID.eq(id))
                .fetchOneInto(Contact.class));
    }

    @Override
    @Transactional
    public void add(Contact contact) {
        dslContext().insertInto(DB_CONTACT)
                .set(DB_CONTACT.NAME, contact.getName())
                .set(DB_CONTACT.FIRST_NAME, contact.getFirstName())
                .set(DB_CONTACT.LAST_NAME, contact.getLastName())
                .set(DB_CONTACT.EMAIL, contact.getEmail())
                .set(DB_CONTACT.ALT_EMAIL, contact.getAltEmail())
                .set(DB_CONTACT.PHONE, contact.getPhone())
                .set(DB_CONTACT.ALT_PHONE, contact.getAltPhone())
                .set(DB_CONTACT.COMPANY_NAME, contact.getCompanyName())
                .set(DB_CONTACT.PESEL, contact.getPesel())
                .set(DB_CONTACT.NIP, contact.getNip())
                .set(DB_CONTACT.REGON, contact.getRegon())
                .set(DB_CONTACT.KRS, contact.getKrs())
                .set(DB_CONTACT.DATE_CREATED, contact.getDateCreated())
                .set(DB_CONTACT.MODIFIED, contact.getDateModified())
                .onDuplicateKeyIgnore()
                .execute();
    }

    // todo add/remove address to contact

    @Override
    @Transactional
    public void update(Contact contact) {
        dslContext().update(DB_CONTACT)
                .set(DB_CONTACT.NAME, contact.getName())
                .set(DB_CONTACT.FIRST_NAME, contact.getFirstName())
                .set(DB_CONTACT.LAST_NAME, contact.getLastName())
                .set(DB_CONTACT.EMAIL, contact.getEmail())
                .set(DB_CONTACT.ALT_EMAIL, contact.getAltEmail())
                .set(DB_CONTACT.PHONE, contact.getPhone())
                .set(DB_CONTACT.ALT_PHONE, contact.getAltPhone())
                .set(DB_CONTACT.COMPANY_NAME, contact.getCompanyName())
                .set(DB_CONTACT.PESEL, contact.getPesel())
                .set(DB_CONTACT.NIP, contact.getNip())
                .set(DB_CONTACT.REGON, contact.getRegon())
                .set(DB_CONTACT.KRS, contact.getKrs())
                .set(DB_CONTACT.DATE_CREATED, contact.getDateCreated())
                .set(DB_CONTACT.MODIFIED, contact.getDateModified())
                .where(DB_CONTACT.CONTACT_ID.eq(contact.getContactId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_CONTACT_ADDRESS)
                .where(DB_CONTACT_ADDRESS.CONTACT.eq(id))
                .execute();
        dslContext().deleteFrom(DB_CONTACT)
                .where(DB_CONTACT.CONTACT_ID.eq(id))
                .execute();
    }
}
