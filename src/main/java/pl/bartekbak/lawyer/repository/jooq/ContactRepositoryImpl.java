package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
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
        return Optional.empty();
    }

    @Override
    public void add(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void deleteById(int id) {

    }
}
