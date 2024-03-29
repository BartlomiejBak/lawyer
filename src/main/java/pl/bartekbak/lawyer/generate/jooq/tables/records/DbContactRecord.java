/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables.records;


import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.UpdatableRecordImpl;

import pl.bartekbak.lawyer.generate.jooq.tables.DbContact;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbContactRecord extends UpdatableRecordImpl<DbContactRecord> implements Record15<UUID, String, String, String, String, String, String, String, String, String, String, String, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.db_contact.contact_id</code>.
     */
    public void setContactId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.db_contact.contact_id</code>.
     */
    public UUID getContactId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.db_contact.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.db_contact.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.db_contact.first_name</code>.
     */
    public void setFirstName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.db_contact.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.db_contact.last_name</code>.
     */
    public void setLastName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.db_contact.last_name</code>.
     */
    public String getLastName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.db_contact.email</code>.
     */
    public void setEmail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.db_contact.email</code>.
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.db_contact.alt_email</code>.
     */
    public void setAltEmail(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.db_contact.alt_email</code>.
     */
    public String getAltEmail() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.db_contact.phone</code>.
     */
    public void setPhone(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.db_contact.phone</code>.
     */
    public String getPhone() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.db_contact.alt_phone</code>.
     */
    public void setAltPhone(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.db_contact.alt_phone</code>.
     */
    public String getAltPhone() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.db_contact.pesel</code>.
     */
    public void setPesel(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.db_contact.pesel</code>.
     */
    public String getPesel() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.db_contact.company_name</code>.
     */
    public void setCompanyName(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.db_contact.company_name</code>.
     */
    public String getCompanyName() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.db_contact.nip</code>.
     */
    public void setNip(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.db_contact.nip</code>.
     */
    public String getNip() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.db_contact.regon</code>.
     */
    public void setRegon(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.db_contact.regon</code>.
     */
    public String getRegon() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public.db_contact.krs</code>.
     */
    public void setKrs(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.db_contact.krs</code>.
     */
    public String getKrs() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.db_contact.date_created</code>.
     */
    public void setDateCreated(LocalDateTime value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.db_contact.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>public.db_contact.modified</code>.
     */
    public void setModified(LocalDateTime value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.db_contact.modified</code>.
     */
    public LocalDateTime getModified() {
        return (LocalDateTime) get(14);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row15<UUID, String, String, String, String, String, String, String, String, String, String, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    @Override
    public Row15<UUID, String, String, String, String, String, String, String, String, String, String, String, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row15) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return DbContact.DB_CONTACT.CONTACT_ID;
    }

    @Override
    public Field<String> field2() {
        return DbContact.DB_CONTACT.NAME;
    }

    @Override
    public Field<String> field3() {
        return DbContact.DB_CONTACT.FIRST_NAME;
    }

    @Override
    public Field<String> field4() {
        return DbContact.DB_CONTACT.LAST_NAME;
    }

    @Override
    public Field<String> field5() {
        return DbContact.DB_CONTACT.EMAIL;
    }

    @Override
    public Field<String> field6() {
        return DbContact.DB_CONTACT.ALT_EMAIL;
    }

    @Override
    public Field<String> field7() {
        return DbContact.DB_CONTACT.PHONE;
    }

    @Override
    public Field<String> field8() {
        return DbContact.DB_CONTACT.ALT_PHONE;
    }

    @Override
    public Field<String> field9() {
        return DbContact.DB_CONTACT.PESEL;
    }

    @Override
    public Field<String> field10() {
        return DbContact.DB_CONTACT.COMPANY_NAME;
    }

    @Override
    public Field<String> field11() {
        return DbContact.DB_CONTACT.NIP;
    }

    @Override
    public Field<String> field12() {
        return DbContact.DB_CONTACT.REGON;
    }

    @Override
    public Field<String> field13() {
        return DbContact.DB_CONTACT.KRS;
    }

    @Override
    public Field<LocalDateTime> field14() {
        return DbContact.DB_CONTACT.DATE_CREATED;
    }

    @Override
    public Field<LocalDateTime> field15() {
        return DbContact.DB_CONTACT.MODIFIED;
    }

    @Override
    public UUID component1() {
        return getContactId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getFirstName();
    }

    @Override
    public String component4() {
        return getLastName();
    }

    @Override
    public String component5() {
        return getEmail();
    }

    @Override
    public String component6() {
        return getAltEmail();
    }

    @Override
    public String component7() {
        return getPhone();
    }

    @Override
    public String component8() {
        return getAltPhone();
    }

    @Override
    public String component9() {
        return getPesel();
    }

    @Override
    public String component10() {
        return getCompanyName();
    }

    @Override
    public String component11() {
        return getNip();
    }

    @Override
    public String component12() {
        return getRegon();
    }

    @Override
    public String component13() {
        return getKrs();
    }

    @Override
    public LocalDateTime component14() {
        return getDateCreated();
    }

    @Override
    public LocalDateTime component15() {
        return getModified();
    }

    @Override
    public UUID value1() {
        return getContactId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getFirstName();
    }

    @Override
    public String value4() {
        return getLastName();
    }

    @Override
    public String value5() {
        return getEmail();
    }

    @Override
    public String value6() {
        return getAltEmail();
    }

    @Override
    public String value7() {
        return getPhone();
    }

    @Override
    public String value8() {
        return getAltPhone();
    }

    @Override
    public String value9() {
        return getPesel();
    }

    @Override
    public String value10() {
        return getCompanyName();
    }

    @Override
    public String value11() {
        return getNip();
    }

    @Override
    public String value12() {
        return getRegon();
    }

    @Override
    public String value13() {
        return getKrs();
    }

    @Override
    public LocalDateTime value14() {
        return getDateCreated();
    }

    @Override
    public LocalDateTime value15() {
        return getModified();
    }

    @Override
    public DbContactRecord value1(UUID value) {
        setContactId(value);
        return this;
    }

    @Override
    public DbContactRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public DbContactRecord value3(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public DbContactRecord value4(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public DbContactRecord value5(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public DbContactRecord value6(String value) {
        setAltEmail(value);
        return this;
    }

    @Override
    public DbContactRecord value7(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public DbContactRecord value8(String value) {
        setAltPhone(value);
        return this;
    }

    @Override
    public DbContactRecord value9(String value) {
        setPesel(value);
        return this;
    }

    @Override
    public DbContactRecord value10(String value) {
        setCompanyName(value);
        return this;
    }

    @Override
    public DbContactRecord value11(String value) {
        setNip(value);
        return this;
    }

    @Override
    public DbContactRecord value12(String value) {
        setRegon(value);
        return this;
    }

    @Override
    public DbContactRecord value13(String value) {
        setKrs(value);
        return this;
    }

    @Override
    public DbContactRecord value14(LocalDateTime value) {
        setDateCreated(value);
        return this;
    }

    @Override
    public DbContactRecord value15(LocalDateTime value) {
        setModified(value);
        return this;
    }

    @Override
    public DbContactRecord values(UUID value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, LocalDateTime value14, LocalDateTime value15) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbContactRecord
     */
    public DbContactRecord() {
        super(DbContact.DB_CONTACT);
    }

    /**
     * Create a detached, initialised DbContactRecord
     */
    public DbContactRecord(UUID contactId, String name, String firstName, String lastName, String email, String altEmail, String phone, String altPhone, String pesel, String companyName, String nip, String regon, String krs, LocalDateTime dateCreated, LocalDateTime modified) {
        super(DbContact.DB_CONTACT);

        setContactId(contactId);
        setName(name);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAltEmail(altEmail);
        setPhone(phone);
        setAltPhone(altPhone);
        setPesel(pesel);
        setCompanyName(companyName);
        setNip(nip);
        setRegon(regon);
        setKrs(krs);
        setDateCreated(dateCreated);
        setModified(modified);
    }
}
