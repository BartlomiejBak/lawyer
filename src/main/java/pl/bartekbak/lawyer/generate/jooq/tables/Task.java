/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
import pl.bartekbak.lawyer.generate.jooq.tables.records.TaskRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Task extends TableImpl<TaskRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.task</code>
     */
    public static final Task TASK = new Task();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TaskRecord> getRecordType() {
        return TaskRecord.class;
    }

    /**
     * The column <code>public.task.task_id</code>.
     */
    public final TableField<TaskRecord, Integer> TASK_ID = createField(DSL.name("task_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.task.priority</code>.
     */
    public final TableField<TaskRecord, Boolean> PRIORITY = createField(DSL.name("priority"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.task.deadline</code>.
     */
    public final TableField<TaskRecord, LocalDateTime> DEADLINE = createField(DSL.name("deadline"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.task.description</code>.
     */
    public final TableField<TaskRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(500), this, "");

    private Task(Name alias, Table<TaskRecord> aliased) {
        this(alias, aliased, null);
    }

    private Task(Name alias, Table<TaskRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.task</code> table reference
     */
    public Task(String alias) {
        this(DSL.name(alias), TASK);
    }

    /**
     * Create an aliased <code>public.task</code> table reference
     */
    public Task(Name alias) {
        this(alias, TASK);
    }

    /**
     * Create a <code>public.task</code> table reference
     */
    public Task() {
        this(DSL.name("task"), null);
    }

    public <O extends Record> Task(Table<O> child, ForeignKey<O, TaskRecord> key) {
        super(child, key, TASK);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<TaskRecord, Integer> getIdentity() {
        return (Identity<TaskRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TaskRecord> getPrimaryKey() {
        return Keys.TASK_PKEY;
    }

    @Override
    public Task as(String alias) {
        return new Task(DSL.name(alias), this);
    }

    @Override
    public Task as(Name alias) {
        return new Task(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(String name) {
        return new Task(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(Name name) {
        return new Task(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Boolean, LocalDateTime, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
