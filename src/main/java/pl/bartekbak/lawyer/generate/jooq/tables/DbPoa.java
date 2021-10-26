/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbPoaRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbPoa extends TableImpl<DbPoaRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_poa</code>
     */
    public static final DbPoa DB_POA = new DbPoa();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbPoaRecord> getRecordType() {
        return DbPoaRecord.class;
    }

    /**
     * The column <code>public.db_poa.poa_id</code>.
     */
    public final TableField<DbPoaRecord, Integer> POA_ID = createField(DSL.name("poa_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_poa.type</code>.
     */
    public final TableField<DbPoaRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.db_poa.payment</code>.
     */
    public final TableField<DbPoaRecord, String> PAYMENT = createField(DSL.name("payment"), SQLDataType.VARCHAR(150), this, "");

    /**
     * The column <code>public.db_poa.kpc</code>.
     */
    public final TableField<DbPoaRecord, Boolean> KPC = createField(DSL.name("kpc"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.db_poa.termination</code>.
     */
    public final TableField<DbPoaRecord, Boolean> TERMINATION = createField(DSL.name("termination"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.db_poa.start_date</code>.
     */
    public final TableField<DbPoaRecord, LocalDate> START_DATE = createField(DSL.name("start_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.db_poa.end_date</code>.
     */
    public final TableField<DbPoaRecord, LocalDate> END_DATE = createField(DSL.name("end_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.db_poa.notification_duty</code>.
     */
    public final TableField<DbPoaRecord, Boolean> NOTIFICATION_DUTY = createField(DSL.name("notification_duty"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.db_poa.duty_completed</code>.
     */
    public final TableField<DbPoaRecord, Boolean> DUTY_COMPLETED = createField(DSL.name("duty_completed"), SQLDataType.BOOLEAN, this, "");

    private DbPoa(Name alias, Table<DbPoaRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbPoa(Name alias, Table<DbPoaRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_poa</code> table reference
     */
    public DbPoa(String alias) {
        this(DSL.name(alias), DB_POA);
    }

    /**
     * Create an aliased <code>public.db_poa</code> table reference
     */
    public DbPoa(Name alias) {
        this(alias, DB_POA);
    }

    /**
     * Create a <code>public.db_poa</code> table reference
     */
    public DbPoa() {
        this(DSL.name("db_poa"), null);
    }

    public <O extends Record> DbPoa(Table<O> child, ForeignKey<O, DbPoaRecord> key) {
        super(child, key, DB_POA);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbPoaRecord, Integer> getIdentity() {
        return (Identity<DbPoaRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbPoaRecord> getPrimaryKey() {
        return Keys.POA_PKEY;
    }

    @Override
    public DbPoa as(String alias) {
        return new DbPoa(DSL.name(alias), this);
    }

    @Override
    public DbPoa as(Name alias) {
        return new DbPoa(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbPoa rename(String name) {
        return new DbPoa(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbPoa rename(Name name) {
        return new DbPoa(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, String, String, Boolean, Boolean, LocalDate, LocalDate, Boolean, Boolean> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}