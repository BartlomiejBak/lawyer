/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbTagRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbTag extends TableImpl<DbTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_tag</code>
     */
    public static final DbTag DB_TAG = new DbTag();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbTagRecord> getRecordType() {
        return DbTagRecord.class;
    }

    /**
     * The column <code>public.db_tag.tag_id</code>.
     */
    public final TableField<DbTagRecord, Integer> TAG_ID = createField(DSL.name("tag_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_tag.name</code>.
     */
    public final TableField<DbTagRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(40), this, "");

    private DbTag(Name alias, Table<DbTagRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbTag(Name alias, Table<DbTagRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_tag</code> table reference
     */
    public DbTag(String alias) {
        this(DSL.name(alias), DB_TAG);
    }

    /**
     * Create an aliased <code>public.db_tag</code> table reference
     */
    public DbTag(Name alias) {
        this(alias, DB_TAG);
    }

    /**
     * Create a <code>public.db_tag</code> table reference
     */
    public DbTag() {
        this(DSL.name("db_tag"), null);
    }

    public <O extends Record> DbTag(Table<O> child, ForeignKey<O, DbTagRecord> key) {
        super(child, key, DB_TAG);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbTagRecord, Integer> getIdentity() {
        return (Identity<DbTagRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbTagRecord> getPrimaryKey() {
        return Keys.TAG_PKEY;
    }

    @Override
    public DbTag as(String alias) {
        return new DbTag(DSL.name(alias), this);
    }

    @Override
    public DbTag as(Name alias) {
        return new DbTag(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbTag rename(String name) {
        return new DbTag(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbTag rename(Name name) {
        return new DbTag(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}