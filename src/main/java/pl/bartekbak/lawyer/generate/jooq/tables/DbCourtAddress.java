/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import pl.bartekbak.lawyer.generate.jooq.Keys;
import pl.bartekbak.lawyer.generate.jooq.Public;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbCourtAddressRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbCourtAddress extends TableImpl<DbCourtAddressRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_court_address</code>
     */
    public static final DbCourtAddress DB_COURT_ADDRESS = new DbCourtAddress();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbCourtAddressRecord> getRecordType() {
        return DbCourtAddressRecord.class;
    }

    /**
     * The column <code>public.db_court_address.id</code>.
     */
    public final TableField<DbCourtAddressRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_court_address.address</code>.
     */
    public final TableField<DbCourtAddressRecord, Integer> ADDRESS = createField(DSL.name("address"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.db_court_address.court</code>.
     */
    public final TableField<DbCourtAddressRecord, Integer> COURT = createField(DSL.name("court"), SQLDataType.INTEGER, this, "");

    private DbCourtAddress(Name alias, Table<DbCourtAddressRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbCourtAddress(Name alias, Table<DbCourtAddressRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_court_address</code> table reference
     */
    public DbCourtAddress(String alias) {
        this(DSL.name(alias), DB_COURT_ADDRESS);
    }

    /**
     * Create an aliased <code>public.db_court_address</code> table reference
     */
    public DbCourtAddress(Name alias) {
        this(alias, DB_COURT_ADDRESS);
    }

    /**
     * Create a <code>public.db_court_address</code> table reference
     */
    public DbCourtAddress() {
        this(DSL.name("db_court_address"), null);
    }

    public <O extends Record> DbCourtAddress(Table<O> child, ForeignKey<O, DbCourtAddressRecord> key) {
        super(child, key, DB_COURT_ADDRESS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbCourtAddressRecord, Integer> getIdentity() {
        return (Identity<DbCourtAddressRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbCourtAddressRecord> getPrimaryKey() {
        return Keys.COURT_ADDRESS_PKEY;
    }

    @Override
    public List<ForeignKey<DbCourtAddressRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DB_COURT_ADDRESS__COURT_ADDRESS_ADDRESS_FKEY, Keys.DB_COURT_ADDRESS__COURT_ADDRESS_CONTACT_FKEY);
    }

    private transient DbAddress _dbAddress;
    private transient DbCourt _dbCourt;

    public DbAddress dbAddress() {
        if (_dbAddress == null)
            _dbAddress = new DbAddress(this, Keys.DB_COURT_ADDRESS__COURT_ADDRESS_ADDRESS_FKEY);

        return _dbAddress;
    }

    public DbCourt dbCourt() {
        if (_dbCourt == null)
            _dbCourt = new DbCourt(this, Keys.DB_COURT_ADDRESS__COURT_ADDRESS_CONTACT_FKEY);

        return _dbCourt;
    }

    @Override
    public DbCourtAddress as(String alias) {
        return new DbCourtAddress(DSL.name(alias), this);
    }

    @Override
    public DbCourtAddress as(Name alias) {
        return new DbCourtAddress(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbCourtAddress rename(String name) {
        return new DbCourtAddress(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbCourtAddress rename(Name name) {
        return new DbCourtAddress(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
