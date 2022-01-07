/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbTaskRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbTask extends TableImpl<DbTaskRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_task</code>
     */
    public static final DbTask DB_TASK = new DbTask();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbTaskRecord> getRecordType() {
        return DbTaskRecord.class;
    }

    /**
     * The column <code>public.db_task.task_id</code>.
     */
    public final TableField<DbTaskRecord, UUID> TASK_ID = createField(DSL.name("task_id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.db_task.priority</code>.
     */
    public final TableField<DbTaskRecord, Boolean> PRIORITY = createField(DSL.name("priority"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.db_task.deadline</code>.
     */
    public final TableField<DbTaskRecord, LocalDateTime> DEADLINE = createField(DSL.name("deadline"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.db_task.description</code>.
     */
    public final TableField<DbTaskRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(500), this, "");

    private DbTask(Name alias, Table<DbTaskRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbTask(Name alias, Table<DbTaskRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_task</code> table reference
     */
    public DbTask(String alias) {
        this(DSL.name(alias), DB_TASK);
    }

    /**
     * Create an aliased <code>public.db_task</code> table reference
     */
    public DbTask(Name alias) {
        this(alias, DB_TASK);
    }

    /**
     * Create a <code>public.db_task</code> table reference
     */
    public DbTask() {
        this(DSL.name("db_task"), null);
    }

    public <O extends Record> DbTask(Table<O> child, ForeignKey<O, DbTaskRecord> key) {
        super(child, key, DB_TASK);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DbTaskRecord> getPrimaryKey() {
        return Keys.TASK_PKEY;
    }

    @Override
    public DbTask as(String alias) {
        return new DbTask(DSL.name(alias), this);
    }

    @Override
    public DbTask as(Name alias) {
        return new DbTask(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbTask rename(String name) {
        return new DbTask(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbTask rename(Name name) {
        return new DbTask(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, Boolean, LocalDateTime, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
