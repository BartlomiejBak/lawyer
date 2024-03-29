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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbTaskContactRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbTaskContact extends TableImpl<DbTaskContactRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_task_contact</code>
     */
    public static final DbTaskContact DB_TASK_CONTACT = new DbTaskContact();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbTaskContactRecord> getRecordType() {
        return DbTaskContactRecord.class;
    }

    /**
     * The column <code>public.db_task_contact.id</code>.
     */
    public final TableField<DbTaskContactRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.db_task_contact.task</code>.
     */
    public final TableField<DbTaskContactRecord, UUID> TASK = createField(DSL.name("task"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.db_task_contact.contact</code>.
     */
    public final TableField<DbTaskContactRecord, UUID> CONTACT = createField(DSL.name("contact"), SQLDataType.UUID, this, "");

    private DbTaskContact(Name alias, Table<DbTaskContactRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbTaskContact(Name alias, Table<DbTaskContactRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_task_contact</code> table reference
     */
    public DbTaskContact(String alias) {
        this(DSL.name(alias), DB_TASK_CONTACT);
    }

    /**
     * Create an aliased <code>public.db_task_contact</code> table reference
     */
    public DbTaskContact(Name alias) {
        this(alias, DB_TASK_CONTACT);
    }

    /**
     * Create a <code>public.db_task_contact</code> table reference
     */
    public DbTaskContact() {
        this(DSL.name("db_task_contact"), null);
    }

    public <O extends Record> DbTaskContact(Table<O> child, ForeignKey<O, DbTaskContactRecord> key) {
        super(child, key, DB_TASK_CONTACT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DbTaskContactRecord> getPrimaryKey() {
        return Keys.TASK_CONTACT_PKEY;
    }

    @Override
    public List<ForeignKey<DbTaskContactRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DB_TASK_CONTACT__TASK_CONTACT_TASK_FKEY, Keys.DB_TASK_CONTACT__TASK_CONTACT_CONTACT_FKEY);
    }

    private transient DbTask _dbTask;
    private transient DbContact _dbContact;

    public DbTask dbTask() {
        if (_dbTask == null)
            _dbTask = new DbTask(this, Keys.DB_TASK_CONTACT__TASK_CONTACT_TASK_FKEY);

        return _dbTask;
    }

    public DbContact dbContact() {
        if (_dbContact == null)
            _dbContact = new DbContact(this, Keys.DB_TASK_CONTACT__TASK_CONTACT_CONTACT_FKEY);

        return _dbContact;
    }

    @Override
    public DbTaskContact as(String alias) {
        return new DbTaskContact(DSL.name(alias), this);
    }

    @Override
    public DbTaskContact as(Name alias) {
        return new DbTaskContact(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbTaskContact rename(String name) {
        return new DbTaskContact(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbTaskContact rename(Name name) {
        return new DbTaskContact(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
