/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbLawsuitRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbLawsuit extends TableImpl<DbLawsuitRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_lawsuit</code>
     */
    public static final DbLawsuit DB_LAWSUIT = new DbLawsuit();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbLawsuitRecord> getRecordType() {
        return DbLawsuitRecord.class;
    }

    /**
     * The column <code>public.db_lawsuit.lawsuit_id</code>.
     */
    public final TableField<DbLawsuitRecord, UUID> LAWSUIT_ID = createField(DSL.name("lawsuit_id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.db_lawsuit.name</code>.
     */
    public final TableField<DbLawsuitRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.db_lawsuit.case_side</code>.
     */
    public final TableField<DbLawsuitRecord, String> CASE_SIDE = createField(DSL.name("case_side"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.db_lawsuit.input_date</code>.
     */
    public final TableField<DbLawsuitRecord, LocalDate> INPUT_DATE = createField(DSL.name("input_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.db_lawsuit.deadline</code>.
     */
    public final TableField<DbLawsuitRecord, LocalDate> DEADLINE = createField(DSL.name("deadline"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.db_lawsuit.signature</code>.
     */
    public final TableField<DbLawsuitRecord, String> SIGNATURE = createField(DSL.name("signature"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>public.db_lawsuit.claim_amount</code>.
     */
    public final TableField<DbLawsuitRecord, Double> CLAIM_AMOUNT = createField(DSL.name("claim_amount"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.db_lawsuit.additional_info</code>.
     */
    public final TableField<DbLawsuitRecord, String> ADDITIONAL_INFO = createField(DSL.name("additional_info"), SQLDataType.VARCHAR(500), this, "");

    private DbLawsuit(Name alias, Table<DbLawsuitRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbLawsuit(Name alias, Table<DbLawsuitRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_lawsuit</code> table reference
     */
    public DbLawsuit(String alias) {
        this(DSL.name(alias), DB_LAWSUIT);
    }

    /**
     * Create an aliased <code>public.db_lawsuit</code> table reference
     */
    public DbLawsuit(Name alias) {
        this(alias, DB_LAWSUIT);
    }

    /**
     * Create a <code>public.db_lawsuit</code> table reference
     */
    public DbLawsuit() {
        this(DSL.name("db_lawsuit"), null);
    }

    public <O extends Record> DbLawsuit(Table<O> child, ForeignKey<O, DbLawsuitRecord> key) {
        super(child, key, DB_LAWSUIT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DbLawsuitRecord> getPrimaryKey() {
        return Keys.LAWSUIT_PKEY;
    }

    @Override
    public List<UniqueKey<DbLawsuitRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.UNIQUE_LAWSUIT);
    }

    @Override
    public DbLawsuit as(String alias) {
        return new DbLawsuit(DSL.name(alias), this);
    }

    @Override
    public DbLawsuit as(Name alias) {
        return new DbLawsuit(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbLawsuit rename(String name) {
        return new DbLawsuit(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbLawsuit rename(Name name) {
        return new DbLawsuit(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<UUID, String, String, LocalDate, LocalDate, String, Double, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
