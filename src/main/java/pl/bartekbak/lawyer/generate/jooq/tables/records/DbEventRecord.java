/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables.records;


import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import pl.bartekbak.lawyer.generate.jooq.tables.DbEvent;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbEventRecord extends UpdatableRecordImpl<DbEventRecord> implements Record4<UUID, String, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.db_event.event_id</code>.
     */
    public void setEventId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.db_event.event_id</code>.
     */
    public UUID getEventId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.db_event.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.db_event.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.db_event.date_time</code>.
     */
    public void setDateTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.db_event.date_time</code>.
     */
    public LocalDateTime getDateTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.db_event.description</code>.
     */
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.db_event.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, String, LocalDateTime, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<UUID, String, LocalDateTime, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return DbEvent.DB_EVENT.EVENT_ID;
    }

    @Override
    public Field<String> field2() {
        return DbEvent.DB_EVENT.TITLE;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return DbEvent.DB_EVENT.DATE_TIME;
    }

    @Override
    public Field<String> field4() {
        return DbEvent.DB_EVENT.DESCRIPTION;
    }

    @Override
    public UUID component1() {
        return getEventId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public LocalDateTime component3() {
        return getDateTime();
    }

    @Override
    public String component4() {
        return getDescription();
    }

    @Override
    public UUID value1() {
        return getEventId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public LocalDateTime value3() {
        return getDateTime();
    }

    @Override
    public String value4() {
        return getDescription();
    }

    @Override
    public DbEventRecord value1(UUID value) {
        setEventId(value);
        return this;
    }

    @Override
    public DbEventRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public DbEventRecord value3(LocalDateTime value) {
        setDateTime(value);
        return this;
    }

    @Override
    public DbEventRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public DbEventRecord values(UUID value1, String value2, LocalDateTime value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbEventRecord
     */
    public DbEventRecord() {
        super(DbEvent.DB_EVENT);
    }

    /**
     * Create a detached, initialised DbEventRecord
     */
    public DbEventRecord(UUID eventId, String title, LocalDateTime dateTime, String description) {
        super(DbEvent.DB_EVENT);

        setEventId(eventId);
        setTitle(title);
        setDateTime(dateTime);
        setDescription(description);
    }
}
