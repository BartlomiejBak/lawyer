/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbEventLawsuitRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbEventLawsuit extends TableImpl<DbEventLawsuitRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_event_lawsuit</code>
     */
    public static final DbEventLawsuit DB_EVENT_LAWSUIT = new DbEventLawsuit();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbEventLawsuitRecord> getRecordType() {
        return DbEventLawsuitRecord.class;
    }

    /**
     * The column <code>public.db_event_lawsuit.id</code>.
     */
    public final TableField<DbEventLawsuitRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.db_event_lawsuit.event</code>.
     */
    public final TableField<DbEventLawsuitRecord, UUID> EVENT = createField(DSL.name("event"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.db_event_lawsuit.lawsuit</code>.
     */
    public final TableField<DbEventLawsuitRecord, UUID> LAWSUIT = createField(DSL.name("lawsuit"), SQLDataType.UUID, this, "");

    private DbEventLawsuit(Name alias, Table<DbEventLawsuitRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbEventLawsuit(Name alias, Table<DbEventLawsuitRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_event_lawsuit</code> table reference
     */
    public DbEventLawsuit(String alias) {
        this(DSL.name(alias), DB_EVENT_LAWSUIT);
    }

    /**
     * Create an aliased <code>public.db_event_lawsuit</code> table reference
     */
    public DbEventLawsuit(Name alias) {
        this(alias, DB_EVENT_LAWSUIT);
    }

    /**
     * Create a <code>public.db_event_lawsuit</code> table reference
     */
    public DbEventLawsuit() {
        this(DSL.name("db_event_lawsuit"), null);
    }

    public <O extends Record> DbEventLawsuit(Table<O> child, ForeignKey<O, DbEventLawsuitRecord> key) {
        super(child, key, DB_EVENT_LAWSUIT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DbEventLawsuitRecord> getPrimaryKey() {
        return Keys.EVENT_LAWSUIT_PKEY;
    }

    @Override
    public List<ForeignKey<DbEventLawsuitRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DB_EVENT_LAWSUIT__EVENT_LAWSUIT_EVENT_FKEY, Keys.DB_EVENT_LAWSUIT__EVENT_LAWSUIT_LAWSUIT_FKEY);
    }

    private transient DbEvent _dbEvent;
    private transient DbLawsuit _dbLawsuit;

    public DbEvent dbEvent() {
        if (_dbEvent == null)
            _dbEvent = new DbEvent(this, Keys.DB_EVENT_LAWSUIT__EVENT_LAWSUIT_EVENT_FKEY);

        return _dbEvent;
    }

    public DbLawsuit dbLawsuit() {
        if (_dbLawsuit == null)
            _dbLawsuit = new DbLawsuit(this, Keys.DB_EVENT_LAWSUIT__EVENT_LAWSUIT_LAWSUIT_FKEY);

        return _dbLawsuit;
    }

    @Override
    public DbEventLawsuit as(String alias) {
        return new DbEventLawsuit(DSL.name(alias), this);
    }

    @Override
    public DbEventLawsuit as(Name alias) {
        return new DbEventLawsuit(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbEventLawsuit rename(String name) {
        return new DbEventLawsuit(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbEventLawsuit rename(Name name) {
        return new DbEventLawsuit(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
