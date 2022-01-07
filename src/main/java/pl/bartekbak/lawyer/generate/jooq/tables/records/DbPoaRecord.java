/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq.tables.records;


import java.time.LocalDate;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;

import pl.bartekbak.lawyer.generate.jooq.tables.DbPoa;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbPoaRecord extends UpdatableRecordImpl<DbPoaRecord> implements Record9<UUID, String, String, Boolean, Boolean, LocalDate, LocalDate, Boolean, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.db_poa.poa_id</code>.
     */
    public void setPoaId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.db_poa.poa_id</code>.
     */
    public UUID getPoaId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.db_poa.type</code>.
     */
    public void setType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.db_poa.type</code>.
     */
    public String getType() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.db_poa.payment</code>.
     */
    public void setPayment(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.db_poa.payment</code>.
     */
    public String getPayment() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.db_poa.kpc</code>.
     */
    public void setKpc(Boolean value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.db_poa.kpc</code>.
     */
    public Boolean getKpc() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>public.db_poa.termination</code>.
     */
    public void setTermination(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.db_poa.termination</code>.
     */
    public Boolean getTermination() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.db_poa.start_date</code>.
     */
    public void setStartDate(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.db_poa.start_date</code>.
     */
    public LocalDate getStartDate() {
        return (LocalDate) get(5);
    }

    /**
     * Setter for <code>public.db_poa.end_date</code>.
     */
    public void setEndDate(LocalDate value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.db_poa.end_date</code>.
     */
    public LocalDate getEndDate() {
        return (LocalDate) get(6);
    }

    /**
     * Setter for <code>public.db_poa.notification_duty</code>.
     */
    public void setNotificationDuty(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.db_poa.notification_duty</code>.
     */
    public Boolean getNotificationDuty() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>public.db_poa.duty_completed</code>.
     */
    public void setDutyCompleted(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.db_poa.duty_completed</code>.
     */
    public Boolean getDutyCompleted() {
        return (Boolean) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<UUID, String, String, Boolean, Boolean, LocalDate, LocalDate, Boolean, Boolean> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<UUID, String, String, Boolean, Boolean, LocalDate, LocalDate, Boolean, Boolean> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return DbPoa.DB_POA.POA_ID;
    }

    @Override
    public Field<String> field2() {
        return DbPoa.DB_POA.TYPE;
    }

    @Override
    public Field<String> field3() {
        return DbPoa.DB_POA.PAYMENT;
    }

    @Override
    public Field<Boolean> field4() {
        return DbPoa.DB_POA.KPC;
    }

    @Override
    public Field<Boolean> field5() {
        return DbPoa.DB_POA.TERMINATION;
    }

    @Override
    public Field<LocalDate> field6() {
        return DbPoa.DB_POA.START_DATE;
    }

    @Override
    public Field<LocalDate> field7() {
        return DbPoa.DB_POA.END_DATE;
    }

    @Override
    public Field<Boolean> field8() {
        return DbPoa.DB_POA.NOTIFICATION_DUTY;
    }

    @Override
    public Field<Boolean> field9() {
        return DbPoa.DB_POA.DUTY_COMPLETED;
    }

    @Override
    public UUID component1() {
        return getPoaId();
    }

    @Override
    public String component2() {
        return getType();
    }

    @Override
    public String component3() {
        return getPayment();
    }

    @Override
    public Boolean component4() {
        return getKpc();
    }

    @Override
    public Boolean component5() {
        return getTermination();
    }

    @Override
    public LocalDate component6() {
        return getStartDate();
    }

    @Override
    public LocalDate component7() {
        return getEndDate();
    }

    @Override
    public Boolean component8() {
        return getNotificationDuty();
    }

    @Override
    public Boolean component9() {
        return getDutyCompleted();
    }

    @Override
    public UUID value1() {
        return getPoaId();
    }

    @Override
    public String value2() {
        return getType();
    }

    @Override
    public String value3() {
        return getPayment();
    }

    @Override
    public Boolean value4() {
        return getKpc();
    }

    @Override
    public Boolean value5() {
        return getTermination();
    }

    @Override
    public LocalDate value6() {
        return getStartDate();
    }

    @Override
    public LocalDate value7() {
        return getEndDate();
    }

    @Override
    public Boolean value8() {
        return getNotificationDuty();
    }

    @Override
    public Boolean value9() {
        return getDutyCompleted();
    }

    @Override
    public DbPoaRecord value1(UUID value) {
        setPoaId(value);
        return this;
    }

    @Override
    public DbPoaRecord value2(String value) {
        setType(value);
        return this;
    }

    @Override
    public DbPoaRecord value3(String value) {
        setPayment(value);
        return this;
    }

    @Override
    public DbPoaRecord value4(Boolean value) {
        setKpc(value);
        return this;
    }

    @Override
    public DbPoaRecord value5(Boolean value) {
        setTermination(value);
        return this;
    }

    @Override
    public DbPoaRecord value6(LocalDate value) {
        setStartDate(value);
        return this;
    }

    @Override
    public DbPoaRecord value7(LocalDate value) {
        setEndDate(value);
        return this;
    }

    @Override
    public DbPoaRecord value8(Boolean value) {
        setNotificationDuty(value);
        return this;
    }

    @Override
    public DbPoaRecord value9(Boolean value) {
        setDutyCompleted(value);
        return this;
    }

    @Override
    public DbPoaRecord values(UUID value1, String value2, String value3, Boolean value4, Boolean value5, LocalDate value6, LocalDate value7, Boolean value8, Boolean value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbPoaRecord
     */
    public DbPoaRecord() {
        super(DbPoa.DB_POA);
    }

    /**
     * Create a detached, initialised DbPoaRecord
     */
    public DbPoaRecord(UUID poaId, String type, String payment, Boolean kpc, Boolean termination, LocalDate startDate, LocalDate endDate, Boolean notificationDuty, Boolean dutyCompleted) {
        super(DbPoa.DB_POA);

        setPoaId(poaId);
        setType(type);
        setPayment(payment);
        setKpc(kpc);
        setTermination(termination);
        setStartDate(startDate);
        setEndDate(endDate);
        setNotificationDuty(notificationDuty);
        setDutyCompleted(dutyCompleted);
    }
}
