/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbAddressRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbAddress extends TableImpl<DbAddressRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_address</code>
     */
    public static final DbAddress DB_ADDRESS = new DbAddress();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbAddressRecord> getRecordType() {
        return DbAddressRecord.class;
    }

    /**
     * The column <code>public.db_address.address_id</code>.
     */
    public final TableField<DbAddressRecord, Integer> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_address.street</code>.
     */
    public final TableField<DbAddressRecord, String> STREET = createField(DSL.name("street"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.db_address.zip_code</code>.
     */
    public final TableField<DbAddressRecord, String> ZIP_CODE = createField(DSL.name("zip_code"), SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>public.db_address.city</code>.
     */
    public final TableField<DbAddressRecord, String> CITY = createField(DSL.name("city"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.db_address.country</code>.
     */
    public final TableField<DbAddressRecord, String> COUNTRY = createField(DSL.name("country"), SQLDataType.VARCHAR(20), this, "");

    private DbAddress(Name alias, Table<DbAddressRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbAddress(Name alias, Table<DbAddressRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_address</code> table reference
     */
    public DbAddress(String alias) {
        this(DSL.name(alias), DB_ADDRESS);
    }

    /**
     * Create an aliased <code>public.db_address</code> table reference
     */
    public DbAddress(Name alias) {
        this(alias, DB_ADDRESS);
    }

    /**
     * Create a <code>public.db_address</code> table reference
     */
    public DbAddress() {
        this(DSL.name("db_address"), null);
    }

    public <O extends Record> DbAddress(Table<O> child, ForeignKey<O, DbAddressRecord> key) {
        super(child, key, DB_ADDRESS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbAddressRecord, Integer> getIdentity() {
        return (Identity<DbAddressRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbAddressRecord> getPrimaryKey() {
        return Keys.ADDRESS_PKEY;
    }

    @Override
    public DbAddress as(String alias) {
        return new DbAddress(DSL.name(alias), this);
    }

    @Override
    public DbAddress as(Name alias) {
        return new DbAddress(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbAddress rename(String name) {
        return new DbAddress(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbAddress rename(Name name) {
        return new DbAddress(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
