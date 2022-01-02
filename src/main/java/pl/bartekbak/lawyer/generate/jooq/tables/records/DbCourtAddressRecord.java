/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables.records;


import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import pl.bartekbak.lawyer.generate.jooq.tables.DbCourtAddress;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbCourtAddressRecord extends UpdatableRecordImpl<DbCourtAddressRecord> implements Record3<UUID, UUID, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.db_court_address.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.db_court_address.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.db_court_address.address</code>.
     */
    public void setAddress(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.db_court_address.address</code>.
     */
    public UUID getAddress() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.db_court_address.court</code>.
     */
    public void setCourt(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.db_court_address.court</code>.
     */
    public UUID getCourt() {
        return (UUID) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, UUID, UUID> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return DbCourtAddress.DB_COURT_ADDRESS.ID;
    }

    @Override
    public Field<UUID> field2() {
        return DbCourtAddress.DB_COURT_ADDRESS.ADDRESS;
    }

    @Override
    public Field<UUID> field3() {
        return DbCourtAddress.DB_COURT_ADDRESS.COURT;
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
        return getCourt();
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
        return getCourt();
    }

    @Override
    public DbCourtAddressRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public DbCourtAddressRecord value2(UUID value) {
        setAddress(value);
        return this;
    }

    @Override
    public DbCourtAddressRecord value3(UUID value) {
        setCourt(value);
        return this;
    }

    @Override
    public DbCourtAddressRecord values(UUID value1, UUID value2, UUID value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbCourtAddressRecord
     */
    public DbCourtAddressRecord() {
        super(DbCourtAddress.DB_COURT_ADDRESS);
    }

    /**
     * Create a detached, initialised DbCourtAddressRecord
     */
    public DbCourtAddressRecord(UUID id, UUID address, UUID court) {
        super(DbCourtAddress.DB_COURT_ADDRESS);

        setId(id);
        setAddress(address);
        setCourt(court);
    }
}
