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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbLawsuitTaskRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbLawsuitTask extends TableImpl<DbLawsuitTaskRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_lawsuit_task</code>
     */
    public static final DbLawsuitTask DB_LAWSUIT_TASK = new DbLawsuitTask();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbLawsuitTaskRecord> getRecordType() {
        return DbLawsuitTaskRecord.class;
    }

    /**
     * The column <code>public.db_lawsuit_task.id</code>.
     */
    public final TableField<DbLawsuitTaskRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_lawsuit_task.task</code>.
     */
    public final TableField<DbLawsuitTaskRecord, Integer> TASK = createField(DSL.name("task"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.db_lawsuit_task.lawsuit</code>.
     */
    public final TableField<DbLawsuitTaskRecord, Integer> LAWSUIT = createField(DSL.name("lawsuit"), SQLDataType.INTEGER, this, "");

    private DbLawsuitTask(Name alias, Table<DbLawsuitTaskRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbLawsuitTask(Name alias, Table<DbLawsuitTaskRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_lawsuit_task</code> table reference
     */
    public DbLawsuitTask(String alias) {
        this(DSL.name(alias), DB_LAWSUIT_TASK);
    }

    /**
     * Create an aliased <code>public.db_lawsuit_task</code> table reference
     */
    public DbLawsuitTask(Name alias) {
        this(alias, DB_LAWSUIT_TASK);
    }

    /**
     * Create a <code>public.db_lawsuit_task</code> table reference
     */
    public DbLawsuitTask() {
        this(DSL.name("db_lawsuit_task"), null);
    }

    public <O extends Record> DbLawsuitTask(Table<O> child, ForeignKey<O, DbLawsuitTaskRecord> key) {
        super(child, key, DB_LAWSUIT_TASK);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbLawsuitTaskRecord, Integer> getIdentity() {
        return (Identity<DbLawsuitTaskRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbLawsuitTaskRecord> getPrimaryKey() {
        return Keys.LAWSUIT_TASK_PKEY;
    }

    @Override
    public List<ForeignKey<DbLawsuitTaskRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DB_LAWSUIT_TASK__LAWSUIT_TASK_TASK_FKEY, Keys.DB_LAWSUIT_TASK__LAWSUIT_TASK_LAWSUIT_FKEY);
    }

    private transient DbTask _dbTask;
    private transient DbLawsuit _dbLawsuit;

    public DbTask dbTask() {
        if (_dbTask == null)
            _dbTask = new DbTask(this, Keys.DB_LAWSUIT_TASK__LAWSUIT_TASK_TASK_FKEY);

        return _dbTask;
    }

    public DbLawsuit dbLawsuit() {
        if (_dbLawsuit == null)
            _dbLawsuit = new DbLawsuit(this, Keys.DB_LAWSUIT_TASK__LAWSUIT_TASK_LAWSUIT_FKEY);

        return _dbLawsuit;
    }

    @Override
    public DbLawsuitTask as(String alias) {
        return new DbLawsuitTask(DSL.name(alias), this);
    }

    @Override
    public DbLawsuitTask as(Name alias) {
        return new DbLawsuitTask(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbLawsuitTask rename(String name) {
        return new DbLawsuitTask(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbLawsuitTask rename(Name name) {
        return new DbLawsuitTask(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
