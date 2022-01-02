/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables.records;


import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import pl.bartekbak.lawyer.generate.jooq.tables.DbContactAddress;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbContactAddressRecord extends UpdatableRecordImpl<DbContactAddressRecord> implements Record4<UUID, UUID, UUID, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.db_contact_address.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.db_contact_address.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.db_contact_address.address</code>.
     */
    public void setAddress(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.db_contact_address.address</code>.
     */
    public UUID getAddress() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.db_contact_address.contact</code>.
     */
    public void setContact(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.db_contact_address.contact</code>.
     */
    public UUID getContact() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.db_contact_address.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.db_contact_address.type</code>.
     */
    public String getType() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, UUID, UUID, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<UUID, UUID, UUID, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return DbContactAddress.DB_CONTACT_ADDRESS.ID;
    }

    @Override
    public Field<UUID> field2() {
        return DbContactAddress.DB_CONTACT_ADDRESS.ADDRESS;
    }

    @Override
    public Field<UUID> field3() {
        return DbContactAddress.DB_CONTACT_ADDRESS.CONTACT;
    }

    @Override
    public Field<String> field4() {
        return DbContactAddress.DB_CONTACT_ADDRESS.TYPE;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getAddress();
    }

    @Override
    public UUID component3() {
        return getContact();
    }

    @Override
    public String component4() {
        return getType();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getAddress();
    }

    @Override
    public UUID value3() {
        return getContact();
    }

    @Override
    public String value4() {
        return getType();
    }

    @Override
    public DbContactAddressRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public DbContactAddressRecord value2(UUID value) {
        setAddress(value);
        return this;
    }

    @Override
    public DbContactAddressRecord value3(UUID value) {
        setContact(value);
        return this;
    }

    @Override
    public DbContactAddressRecord value4(String value) {
        setType(value);
        return this;
    }

    @Override
    public DbContactAddressRecord values(UUID value1, UUID value2, UUID value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbContactAddressRecord
     */
    public DbContactAddressRecord() {
        super(DbContactAddress.DB_CONTACT_ADDRESS);
    }

    /**
     * Create a detached, initialised DbContactAddressRecord
     */
    public DbContactAddressRecord(UUID id, UUID address, UUID contact, String type) {
        super(DbContactAddress.DB_CONTACT_ADDRESS);

        setId(id);
        setAddress(address);
        setContact(contact);
        setType(type);
    }
}
