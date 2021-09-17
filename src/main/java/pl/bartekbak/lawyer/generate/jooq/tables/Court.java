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
import pl.bartekbak.lawyer.generate.jooq.tables.records.CourtRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Court extends TableImpl<CourtRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.court</code>
     */
    public static final Court COURT = new Court();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CourtRecord> getRecordType() {
        return CourtRecord.class;
    }

    /**
     * The column <code>public.court.court_id</code>.
     */
    public final TableField<CourtRecord, Integer> COURT_ID = createField(DSL.name("court_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.court.name</code>.
     */
    public final TableField<CourtRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(55), this, "");

    /**
     * The column <code>public.court.description</code>.
     */
    public final TableField<CourtRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>public.court.address</code>.
     */
    public final TableField<CourtRecord, Integer> ADDRESS = createField(DSL.name("address"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.court.phone</code>.
     */
    public final TableField<CourtRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR(16), this, "");

    private Court(Name alias, Table<CourtRecord> aliased) {
        this(alias, aliased, null);
    }

    private Court(Name alias, Table<CourtRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.court</code> table reference
     */
    public Court(String alias) {
        this(DSL.name(alias), COURT);
    }

    /**
     * Create an aliased <code>public.court</code> table reference
     */
    public Court(Name alias) {
        this(alias, COURT);
    }

    /**
     * Create a <code>public.court</code> table reference
     */
    public Court() {
        this(DSL.name("court"), null);
    }

    public <O extends Record> Court(Table<O> child, ForeignKey<O, CourtRecord> key) {
        super(child, key, COURT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CourtRecord, Integer> getIdentity() {
        return (Identity<CourtRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CourtRecord> getPrimaryKey() {
        return Keys.COURT_PKEY;
    }

    @Override
    public List<ForeignKey<CourtRecord, ?>> getReferences() {
        return Arrays.asList(Keys.COURT__COURT_ADDRESS_FKEY);
    }

    private transient Address _address;

    public Address address() {
        if (_address == null)
            _address = new Address(this, Keys.COURT__COURT_ADDRESS_FKEY);

        return _address;
    }

    @Override
    public Court as(String alias) {
        return new Court(DSL.name(alias), this);
    }

    @Override
    public Court as(Name alias) {
        return new Court(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Court rename(String name) {
        return new Court(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Court rename(Name name) {
        return new Court(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, Integer, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
