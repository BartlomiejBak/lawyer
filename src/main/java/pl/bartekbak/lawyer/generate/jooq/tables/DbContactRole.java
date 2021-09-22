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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbContactRoleRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbContactRole extends TableImpl<DbContactRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_contact_role</code>
     */
    public static final DbContactRole DB_CONTACT_ROLE = new DbContactRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbContactRoleRecord> getRecordType() {
        return DbContactRoleRecord.class;
    }

    /**
     * The column <code>public.db_contact_role.id</code>.
     */
    public final TableField<DbContactRoleRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_contact_role.contact</code>.
     */
    public final TableField<DbContactRoleRecord, Integer> CONTACT = createField(DSL.name("contact"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.db_contact_role.role</code>.
     */
    public final TableField<DbContactRoleRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR(25), this, "");

    private DbContactRole(Name alias, Table<DbContactRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbContactRole(Name alias, Table<DbContactRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_contact_role</code> table reference
     */
    public DbContactRole(String alias) {
        this(DSL.name(alias), DB_CONTACT_ROLE);
    }

    /**
     * Create an aliased <code>public.db_contact_role</code> table reference
     */
    public DbContactRole(Name alias) {
        this(alias, DB_CONTACT_ROLE);
    }

    /**
     * Create a <code>public.db_contact_role</code> table reference
     */
    public DbContactRole() {
        this(DSL.name("db_contact_role"), null);
    }

    public <O extends Record> DbContactRole(Table<O> child, ForeignKey<O, DbContactRoleRecord> key) {
        super(child, key, DB_CONTACT_ROLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbContactRoleRecord, Integer> getIdentity() {
        return (Identity<DbContactRoleRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbContactRoleRecord> getPrimaryKey() {
        return Keys.CONTACT_ROLE_PKEY;
    }

    @Override
    public List<ForeignKey<DbContactRoleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DB_CONTACT_ROLE__CONTACT_ROLE_CONTACT_FKEY);
    }

    private transient DbContact _dbContact;

    public DbContact dbContact() {
        if (_dbContact == null)
            _dbContact = new DbContact(this, Keys.DB_CONTACT_ROLE__CONTACT_ROLE_CONTACT_FKEY);

        return _dbContact;
    }

    @Override
    public DbContactRole as(String alias) {
        return new DbContactRole(DSL.name(alias), this);
    }

    @Override
    public DbContactRole as(Name alias) {
        return new DbContactRole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbContactRole rename(String name) {
        return new DbContactRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbContactRole rename(Name name) {
        return new DbContactRole(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
