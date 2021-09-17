/*
 * This file is generated by jOOQ.
 */
package pl.bartekbak.lawyer.generate.jooq;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import pl.bartekbak.lawyer.generate.jooq.tables.Address;
import pl.bartekbak.lawyer.generate.jooq.tables.Contact;
import pl.bartekbak.lawyer.generate.jooq.tables.ContactAddress;
import pl.bartekbak.lawyer.generate.jooq.tables.ContactRole;
import pl.bartekbak.lawyer.generate.jooq.tables.ContactRoleLawsuit;
import pl.bartekbak.lawyer.generate.jooq.tables.Court;
import pl.bartekbak.lawyer.generate.jooq.tables.CourtAddress;
import pl.bartekbak.lawyer.generate.jooq.tables.Event;
import pl.bartekbak.lawyer.generate.jooq.tables.EventLawsuit;
import pl.bartekbak.lawyer.generate.jooq.tables.Lawsuit;
import pl.bartekbak.lawyer.generate.jooq.tables.LawsuitContact;
import pl.bartekbak.lawyer.generate.jooq.tables.LawsuitTask;
import pl.bartekbak.lawyer.generate.jooq.tables.Note;
import pl.bartekbak.lawyer.generate.jooq.tables.Payment;
import pl.bartekbak.lawyer.generate.jooq.tables.Poa;
import pl.bartekbak.lawyer.generate.jooq.tables.Tag;
import pl.bartekbak.lawyer.generate.jooq.tables.Task;
import pl.bartekbak.lawyer.generate.jooq.tables.TaskContact;
import pl.bartekbak.lawyer.generate.jooq.tables.records.AddressRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.ContactAddressRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.ContactRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.ContactRoleLawsuitRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.ContactRoleRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.CourtAddressRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.CourtRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.EventLawsuitRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.EventRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.LawsuitContactRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.LawsuitRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.LawsuitTaskRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.NoteRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.PaymentRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.PoaRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.TagRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.TaskContactRecord;
import pl.bartekbak.lawyer.generate.jooq.tables.records.TaskRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AddressRecord> ADDRESS_PKEY = Internal.createUniqueKey(Address.ADDRESS, DSL.name("address_pkey"), new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
    public static final UniqueKey<ContactRecord> CONTACT_PKEY = Internal.createUniqueKey(Contact.CONTACT, DSL.name("contact_pkey"), new TableField[] { Contact.CONTACT.CONTACT_ID }, true);
    public static final UniqueKey<ContactAddressRecord> CONTACT_ADDRESS_PKEY = Internal.createUniqueKey(ContactAddress.CONTACT_ADDRESS, DSL.name("contact_address_pkey"), new TableField[] { ContactAddress.CONTACT_ADDRESS.ID }, true);
    public static final UniqueKey<ContactRoleRecord> CONTACT_ROLE_PKEY = Internal.createUniqueKey(ContactRole.CONTACT_ROLE, DSL.name("contact_role_pkey"), new TableField[] { ContactRole.CONTACT_ROLE.ID }, true);
    public static final UniqueKey<ContactRoleLawsuitRecord> CONTACT_ROLE_LAWSUIT_PKEY = Internal.createUniqueKey(ContactRoleLawsuit.CONTACT_ROLE_LAWSUIT, DSL.name("contact_role_lawsuit_pkey"), new TableField[] { ContactRoleLawsuit.CONTACT_ROLE_LAWSUIT.ID }, true);
    public static final UniqueKey<CourtRecord> COURT_PKEY = Internal.createUniqueKey(Court.COURT, DSL.name("court_pkey"), new TableField[] { Court.COURT.COURT_ID }, true);
    public static final UniqueKey<CourtAddressRecord> COURT_ADDRESS_PKEY = Internal.createUniqueKey(CourtAddress.COURT_ADDRESS, DSL.name("court_address_pkey"), new TableField[] { CourtAddress.COURT_ADDRESS.ID }, true);
    public static final UniqueKey<EventRecord> EVENT_PKEY = Internal.createUniqueKey(Event.EVENT, DSL.name("event_pkey"), new TableField[] { Event.EVENT.EVENT_ID }, true);
    public static final UniqueKey<EventLawsuitRecord> EVENT_LAWSUIT_PKEY = Internal.createUniqueKey(EventLawsuit.EVENT_LAWSUIT, DSL.name("event_lawsuit_pkey"), new TableField[] { EventLawsuit.EVENT_LAWSUIT.ID }, true);
    public static final UniqueKey<LawsuitRecord> LAWSUIT_PKEY = Internal.createUniqueKey(Lawsuit.LAWSUIT, DSL.name("lawsuit_pkey"), new TableField[] { Lawsuit.LAWSUIT.LAWSUIT_ID }, true);
    public static final UniqueKey<LawsuitContactRecord> LAWSUIT_CONTACT_PKEY = Internal.createUniqueKey(LawsuitContact.LAWSUIT_CONTACT, DSL.name("lawsuit_contact_pkey"), new TableField[] { LawsuitContact.LAWSUIT_CONTACT.ID }, true);
    public static final UniqueKey<LawsuitTaskRecord> LAWSUIT_TASK_PKEY = Internal.createUniqueKey(LawsuitTask.LAWSUIT_TASK, DSL.name("lawsuit_task_pkey"), new TableField[] { LawsuitTask.LAWSUIT_TASK.ID }, true);
    public static final UniqueKey<NoteRecord> NOTE_PKEY = Internal.createUniqueKey(Note.NOTE, DSL.name("note_pkey"), new TableField[] { Note.NOTE.NOTE_ID }, true);
    public static final UniqueKey<PaymentRecord> PAYMENT_PKEY = Internal.createUniqueKey(Payment.PAYMENT, DSL.name("payment_pkey"), new TableField[] { Payment.PAYMENT.PAYMENT_ID }, true);
    public static final UniqueKey<PoaRecord> POA_PKEY = Internal.createUniqueKey(Poa.POA, DSL.name("poa_pkey"), new TableField[] { Poa.POA.POA_ID }, true);
    public static final UniqueKey<TagRecord> TAG_PKEY = Internal.createUniqueKey(Tag.TAG, DSL.name("tag_pkey"), new TableField[] { Tag.TAG.TAG_ID }, true);
    public static final UniqueKey<TaskRecord> TASK_PKEY = Internal.createUniqueKey(Task.TASK, DSL.name("task_pkey"), new TableField[] { Task.TASK.TASK_ID }, true);
    public static final UniqueKey<TaskContactRecord> TASK_CONTACT_PKEY = Internal.createUniqueKey(TaskContact.TASK_CONTACT, DSL.name("task_contact_pkey"), new TableField[] { TaskContact.TASK_CONTACT.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ContactRecord, AddressRecord> CONTACT__CONTACT_ADDRESS_FKEY = Internal.createForeignKey(Contact.CONTACT, DSL.name("contact_address_fkey"), new TableField[] { Contact.CONTACT.ADDRESS }, Keys.ADDRESS_PKEY, new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
    public static final ForeignKey<ContactRecord, AddressRecord> CONTACT__CONTACT_SECONDARY_ADDRESS_FKEY = Internal.createForeignKey(Contact.CONTACT, DSL.name("contact_secondary_address_fkey"), new TableField[] { Contact.CONTACT.SECONDARY_ADDRESS }, Keys.ADDRESS_PKEY, new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
    public static final ForeignKey<ContactAddressRecord, AddressRecord> CONTACT_ADDRESS__CONTACT_ADDRESS_ADDRESS_FKEY = Internal.createForeignKey(ContactAddress.CONTACT_ADDRESS, DSL.name("contact_address_address_fkey"), new TableField[] { ContactAddress.CONTACT_ADDRESS.ADDRESS }, Keys.ADDRESS_PKEY, new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
    public static final ForeignKey<ContactAddressRecord, ContactRecord> CONTACT_ADDRESS__CONTACT_ADDRESS_CONTACT_FKEY = Internal.createForeignKey(ContactAddress.CONTACT_ADDRESS, DSL.name("contact_address_contact_fkey"), new TableField[] { ContactAddress.CONTACT_ADDRESS.CONTACT }, Keys.CONTACT_PKEY, new TableField[] { Contact.CONTACT.CONTACT_ID }, true);
    public static final ForeignKey<ContactRoleRecord, ContactRecord> CONTACT_ROLE__CONTACT_ROLE_CONTACT_FKEY = Internal.createForeignKey(ContactRole.CONTACT_ROLE, DSL.name("contact_role_contact_fkey"), new TableField[] { ContactRole.CONTACT_ROLE.CONTACT }, Keys.CONTACT_PKEY, new TableField[] { Contact.CONTACT.CONTACT_ID }, true);
    public static final ForeignKey<ContactRoleLawsuitRecord, ContactRoleRecord> CONTACT_ROLE_LAWSUIT__CONTACT_ROLE_LAWSUIT_CONTACT_ROLE_FKEY = Internal.createForeignKey(ContactRoleLawsuit.CONTACT_ROLE_LAWSUIT, DSL.name("contact_role_lawsuit_contact_role_fkey"), new TableField[] { ContactRoleLawsuit.CONTACT_ROLE_LAWSUIT.CONTACT_ROLE }, Keys.CONTACT_ROLE_PKEY, new TableField[] { ContactRole.CONTACT_ROLE.ID }, true);
    public static final ForeignKey<ContactRoleLawsuitRecord, LawsuitRecord> CONTACT_ROLE_LAWSUIT__CONTACT_ROLE_LAWSUIT_LAWSUIT_FKEY = Internal.createForeignKey(ContactRoleLawsuit.CONTACT_ROLE_LAWSUIT, DSL.name("contact_role_lawsuit_lawsuit_fkey"), new TableField[] { ContactRoleLawsuit.CONTACT_ROLE_LAWSUIT.LAWSUIT }, Keys.LAWSUIT_PKEY, new TableField[] { Lawsuit.LAWSUIT.LAWSUIT_ID }, true);
    public static final ForeignKey<CourtRecord, AddressRecord> COURT__COURT_ADDRESS_FKEY = Internal.createForeignKey(Court.COURT, DSL.name("court_address_fkey"), new TableField[] { Court.COURT.ADDRESS }, Keys.ADDRESS_PKEY, new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
    public static final ForeignKey<CourtAddressRecord, AddressRecord> COURT_ADDRESS__COURT_ADDRESS_ADDRESS_FKEY = Internal.createForeignKey(CourtAddress.COURT_ADDRESS, DSL.name("court_address_address_fkey"), new TableField[] { CourtAddress.COURT_ADDRESS.ADDRESS }, Keys.ADDRESS_PKEY, new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
    public static final ForeignKey<CourtAddressRecord, CourtRecord> COURT_ADDRESS__COURT_ADDRESS_CONTACT_FKEY = Internal.createForeignKey(CourtAddress.COURT_ADDRESS, DSL.name("court_address_contact_fkey"), new TableField[] { CourtAddress.COURT_ADDRESS.CONTACT }, Keys.COURT_PKEY, new TableField[] { Court.COURT.COURT_ID }, true);
    public static final ForeignKey<EventLawsuitRecord, EventRecord> EVENT_LAWSUIT__EVENT_LAWSUIT_EVENT_FKEY = Internal.createForeignKey(EventLawsuit.EVENT_LAWSUIT, DSL.name("event_lawsuit_event_fkey"), new TableField[] { EventLawsuit.EVENT_LAWSUIT.EVENT }, Keys.EVENT_PKEY, new TableField[] { Event.EVENT.EVENT_ID }, true);
    public static final ForeignKey<EventLawsuitRecord, LawsuitRecord> EVENT_LAWSUIT__EVENT_LAWSUIT_LAWSUIT_FKEY = Internal.createForeignKey(EventLawsuit.EVENT_LAWSUIT, DSL.name("event_lawsuit_lawsuit_fkey"), new TableField[] { EventLawsuit.EVENT_LAWSUIT.LAWSUIT }, Keys.LAWSUIT_PKEY, new TableField[] { Lawsuit.LAWSUIT.LAWSUIT_ID }, true);
    public static final ForeignKey<LawsuitContactRecord, ContactRecord> LAWSUIT_CONTACT__LAWSUIT_CONTACT_CONTACT_FKEY = Internal.createForeignKey(LawsuitContact.LAWSUIT_CONTACT, DSL.name("lawsuit_contact_contact_fkey"), new TableField[] { LawsuitContact.LAWSUIT_CONTACT.CONTACT }, Keys.CONTACT_PKEY, new TableField[] { Contact.CONTACT.CONTACT_ID }, true);
    public static final ForeignKey<LawsuitContactRecord, LawsuitRecord> LAWSUIT_CONTACT__LAWSUIT_CONTACT_LAWSUIT_FKEY = Internal.createForeignKey(LawsuitContact.LAWSUIT_CONTACT, DSL.name("lawsuit_contact_lawsuit_fkey"), new TableField[] { LawsuitContact.LAWSUIT_CONTACT.LAWSUIT }, Keys.LAWSUIT_PKEY, new TableField[] { Lawsuit.LAWSUIT.LAWSUIT_ID }, true);
    public static final ForeignKey<LawsuitTaskRecord, LawsuitRecord> LAWSUIT_TASK__LAWSUIT_TASK_LAWSUIT_FKEY = Internal.createForeignKey(LawsuitTask.LAWSUIT_TASK, DSL.name("lawsuit_task_lawsuit_fkey"), new TableField[] { LawsuitTask.LAWSUIT_TASK.LAWSUIT }, Keys.LAWSUIT_PKEY, new TableField[] { Lawsuit.LAWSUIT.LAWSUIT_ID }, true);
    public static final ForeignKey<LawsuitTaskRecord, TaskRecord> LAWSUIT_TASK__LAWSUIT_TASK_TASK_FKEY = Internal.createForeignKey(LawsuitTask.LAWSUIT_TASK, DSL.name("lawsuit_task_task_fkey"), new TableField[] { LawsuitTask.LAWSUIT_TASK.TASK }, Keys.TASK_PKEY, new TableField[] { Task.TASK.TASK_ID }, true);
    public static final ForeignKey<TaskContactRecord, ContactRecord> TASK_CONTACT__TASK_CONTACT_CONTACT_FKEY = Internal.createForeignKey(TaskContact.TASK_CONTACT, DSL.name("task_contact_contact_fkey"), new TableField[] { TaskContact.TASK_CONTACT.CONTACT }, Keys.CONTACT_PKEY, new TableField[] { Contact.CONTACT.CONTACT_ID }, true);
    public static final ForeignKey<TaskContactRecord, TaskRecord> TASK_CONTACT__TASK_CONTACT_TASK_FKEY = Internal.createForeignKey(TaskContact.TASK_CONTACT, DSL.name("task_contact_task_fkey"), new TableField[] { TaskContact.TASK_CONTACT.TASK }, Keys.TASK_PKEY, new TableField[] { Task.TASK.TASK_ID }, true);
}
