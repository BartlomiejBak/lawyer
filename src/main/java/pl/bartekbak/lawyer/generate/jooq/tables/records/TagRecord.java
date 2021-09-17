/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import pl.bartekbak.lawyer.generate.jooq.tables.Tag;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TagRecord extends UpdatableRecordImpl<TagRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.tag.tag_id</code>.
     */
    public void setTagId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.tag.tag_id</code>.
     */
    public Integer getTagId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.tag.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.tag.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Tag.TAG.TAG_ID;
    }

    @Override
    public Field<String> field2() {
        return Tag.TAG.NAME;
    }

    @Override
    public Integer component1() {
        return getTagId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer value1() {
        return getTagId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public TagRecord value1(Integer value) {
        setTagId(value);
        return this;
    }

    @Override
    public TagRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public TagRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TagRecord
     */
    public TagRecord() {
        super(Tag.TAG);
    }

    /**
     * Create a detached, initialised TagRecord
     */
    public TagRecord(Integer tagId, String name) {
        super(Tag.TAG);

        setTagId(tagId);
        setName(name);
    }
}
