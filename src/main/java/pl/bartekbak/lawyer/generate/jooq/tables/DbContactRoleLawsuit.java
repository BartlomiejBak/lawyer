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
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbContactRoleLawsuitRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbContactRoleLawsuit extends TableImpl<DbContactRoleLawsuitRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.db_contact_role_lawsuit</code>
     */
    public static final DbContactRoleLawsuit DB_CONTACT_ROLE_LAWSUIT = new DbContactRoleLawsuit();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbContactRoleLawsuitRecord> getRecordType() {
        return DbContactRoleLawsuitRecord.class;
    }

    /**
     * The column <code>public.db_contact_role_lawsuit.id</code>.
     */
    public final TableField<DbContactRoleLawsuitRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.db_contact_role_lawsuit.lawsuit</code>.
     */
    public final TableField<DbContactRoleLawsuitRecord, Integer> LAWSUIT = createField(DSL.name("lawsuit"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.db_contact_role_lawsuit.contact_role</code>.
     */
    public final TableField<DbContactRoleLawsuitRecord, Integer> CONTACT_ROLE = createField(DSL.name("contact_role"), SQLDataType.INTEGER, this, "");

    private DbContactRoleLawsuit(Name alias, Table<DbContactRoleLawsuitRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbContactRoleLawsuit(Name alias, Table<DbContactRoleLawsuitRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.db_contact_role_lawsuit</code> table
     * reference
     */
    public DbContactRoleLawsuit(String alias) {
        this(DSL.name(alias), DB_CONTACT_ROLE_LAWSUIT);
    }

    /**
     * Create an aliased <code>public.db_contact_role_lawsuit</code> table
     * reference
     */
    public DbContactRoleLawsuit(Name alias) {
        this(alias, DB_CONTACT_ROLE_LAWSUIT);
    }

    /**
     * Create a <code>public.db_contact_role_lawsuit</code> table reference
     */
    public DbContactRoleLawsuit() {
        this(DSL.name("db_contact_role_lawsuit"), null);
    }

    public <O extends Record> DbContactRoleLawsuit(Table<O> child, ForeignKey<O, DbContactRoleLawsuitRecord> key) {
        super(child, key, DB_CONTACT_ROLE_LAWSUIT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<DbContactRoleLawsuitRecord, Integer> getIdentity() {
        return (Identity<DbContactRoleLawsuitRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<DbContactRoleLawsuitRecord> getPrimaryKey() {
        return Keys.CONTACT_ROLE_LAWSUIT_PKEY;
    }

    @Override
    public List<ForeignKey<DbContactRoleLawsuitRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DB_CONTACT_ROLE_LAWSUIT__CONTACT_ROLE_LAWSUIT_LAWSUIT_FKEY, Keys.DB_CONTACT_ROLE_LAWSUIT__CONTACT_ROLE_LAWSUIT_CONTACT_ROLE_FKEY);
    }

    private transient DbLawsuit _dbLawsuit;
    private transient DbContactRole _dbContactRole;

    public DbLawsuit dbLawsuit() {
        if (_dbLawsuit == null)
            _dbLawsuit = new DbLawsuit(this, Keys.DB_CONTACT_ROLE_LAWSUIT__CONTACT_ROLE_LAWSUIT_LAWSUIT_FKEY);

        return _dbLawsuit;
    }

    public DbContactRole dbContactRole() {
        if (_dbContactRole == null)
            _dbContactRole = new DbContactRole(this, Keys.DB_CONTACT_ROLE_LAWSUIT__CONTACT_ROLE_LAWSUIT_CONTACT_ROLE_FKEY);

        return _dbContactRole;
    }

    @Override
    public DbContactRoleLawsuit as(String alias) {
        return new DbContactRoleLawsuit(DSL.name(alias), this);
    }

    @Override
    public DbContactRoleLawsuit as(Name alias) {
        return new DbContactRoleLawsuit(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbContactRoleLawsuit rename(String name) {
        return new DbContactRoleLawsuit(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbContactRoleLawsuit rename(Name name) {
        return new DbContactRoleLawsuit(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}