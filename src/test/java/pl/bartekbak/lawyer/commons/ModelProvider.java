package pl.bartekbak.lawyer.commons;

import lombok.Getter;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.dto.TaskDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class ModelProvider {

    public static final UUID ADDRESS_ID = UUID.randomUUID();
    public static final UUID CONTACT_ID = UUID.randomUUID();
    public static final UUID COURT_ID = UUID.randomUUID();
    public static final UUID EVENT_ID = UUID.randomUUID();
    public static final UUID LAWSUIT_ID = UUID.randomUUID();
    public static final UUID NOTE_ID = UUID.randomUUID();
    public static final UUID PAYMENT_ID = UUID.randomUUID();
    public static final UUID POA_ID = UUID.randomUUID();
    public static final UUID TAG_ID = UUID.randomUUID();
    public static final UUID TASK_ID = UUID.randomUUID();

    private final AddressDTO firstAddress = AddressDTO.builder()
            .addressId(ADDRESS_ID)
            .street("Main Street")
            .zipCode("00-001")
            .city("Warsaw")
            .country("Poland")
            .build();

    private final AddressDTO secondAddress = AddressDTO.builder()
            .addressId(UUID.randomUUID())
            .zipCode("00001")
            .street("Strasse")
            .city("Berlin")
            .country("Germany")
            .build();

    private final AddressDTO thirdAddress = AddressDTO.builder()
            .addressId(UUID.randomUUID())
            .street("Calle")
            .zipCode("123 123")
            .city("Madrid")
            .country("Spain")
            .build();

    private final List<AddressDTO> addresses = List.of(firstAddress, secondAddress, thirdAddress);

    private final ContactDTO firstContact = ContactDTO.builder()
            .contactId(CONTACT_ID)
            .name("FirstContact")
            .firstName("First")
            .lastName("ContactA")
            .email("first@email.com")
            .altEmail("firstAlt@email.com")
            .phone("+48 555 555 555")
            .altPhone("+48 555 555 666")
            .companyName("First Company")
            .pesel("1111 1111 1111 1111")
            .nip("111 111 111 111")
            .regon("11 11 11 11")
            .krs("1 1 1 1")
            .dateCreated(LocalDateTime.of(2021, 11, 11, 11, 11))
            .dateModified(LocalDateTime.of(2021, 11, 11, 11, 12))
            .address(firstAddress)
            .correspondenceAddress(secondAddress)
            .build();

    private final ContactDTO secondContact = ContactDTO.builder()
            .contactId(UUID.randomUUID())
            .name("SecondContact")
            .firstName("Second")
            .lastName("Contact")
            .email("second@email.com")
            .altEmail("secondAlt@email.com")
            .phone("+48 555 666 555")
            .altPhone("+48 555 666 666")
            .companyName("Second Company")
            .pesel("2222 2222 2222 2222")
            .nip("222 222 222 222")
            .regon("22 22 22 22")
            .krs("2 2 2 2")
            .dateCreated(LocalDateTime.of(2021, 11, 11, 11, 13))
            .dateModified(LocalDateTime.of(2021, 11, 11, 11, 14))
            .address(secondAddress)
            .correspondenceAddress(thirdAddress)
            .build();

    private final ContactDTO thirdContact = ContactDTO.builder()
            .contactId(UUID.randomUUID())
            .name("ThirdContact")
            .firstName("Third")
            .lastName("Contact")
            .email("third@email.com")
            .altEmail("thirdAlt@email.com")
            .phone("+48 666 555 555")
            .altPhone("+48 666 555 666")
            .companyName("Third Company")
            .pesel("3333 3333 3333 3333")
            .nip("333 333 333 333")
            .regon("33 33 33 33")
            .krs("3 3 3 3")
            .dateCreated(LocalDateTime.of(2021, 11, 11, 11, 15))
            .dateModified(LocalDateTime.of(2021, 11, 11, 11, 16))
            .address(thirdAddress)
            .correspondenceAddress(firstAddress)
            .build();

    private final List<ContactDTO> contacts = List.of(firstContact, secondContact, thirdContact);

    private final CourtDTO firstCourt = CourtDTO.builder()
            .courtId(COURT_ID)
            .name("1 court")
            .address(firstAddress)
            .description("first court")
            .phone("+48 111 111 111")
            .build();

    private final CourtDTO secondCourt = CourtDTO.builder()
            .courtId(UUID.randomUUID())
            .name("2 court")
            .address(secondAddress)
            .description("second court")
            .phone("+48 222 222 222")
            .build();

    private final CourtDTO thirdCourt = CourtDTO.builder()
            .courtId(UUID.randomUUID())
            .name("3 court")
            .address(thirdAddress)
            .description("third court")
            .phone("+48 333 333 333")
            .build();

    private final List<CourtDTO> courts = List.of(firstCourt, secondCourt, thirdCourt);

    private final LawsuitDTO firstLawsuit = LawsuitDTO.builder()
            .lawsuitId(LAWSUIT_ID)
            .name("1 lawsuit")
            .caseSide("defendant")
            .inputDate(LocalDate.of(2021, 11, 12))
            .deadline(LocalDate.of(2022, 11, 11))
            .signature("ABC123")
            .claimAmount(500)
            .additionalInfo("Additional 1")
            .contactList(contacts)
//            .taskList(tasks)
            .plaintiff(contacts)
            .defendant(contacts)
//            .eventSet(events)
            .build();

    private final LawsuitDTO secondLawsuit = LawsuitDTO.builder()
            .lawsuitId(UUID.randomUUID())
            .name("2 lawsuit")
            .caseSide("defendant")
            .inputDate(LocalDate.of(2021, 11, 13))
            .deadline(LocalDate.of(2022, 11, 12))
            .signature("ABC124")
            .claimAmount(600)
            .additionalInfo("Additional 2")
            .contactList(contacts)
//            .taskList(tasks)
            .plaintiff(contacts)
            .defendant(contacts)
//            .eventSet(events)
            .build();

    private final LawsuitDTO thirdLawsuit = LawsuitDTO.builder()
            .lawsuitId(UUID.randomUUID())
            .name("3 lawsuit")
            .caseSide("defendant")
            .inputDate(LocalDate.of(2021, 11, 14))
            .deadline(LocalDate.of(2022, 11, 13))
            .signature("ABC125")
            .claimAmount(700)
            .additionalInfo("Additional 3")
            .contactList(contacts)
//            .taskList(tasks)
            .plaintiff(contacts)
            .defendant(contacts)
//            .eventSet(events)
            .build();

    private final List<LawsuitDTO> lawsuits = List.of(firstLawsuit, secondLawsuit, thirdLawsuit);

    private final EventDTO firstEvent = EventDTO.builder()
            .eventId(EVENT_ID)
            .title("FirstEvent")
            .dateTime(LocalDateTime.of(2021, 11, 11, 11 ,1))
            .description("description")
            .build();

    private final EventDTO secondEvent = EventDTO.builder()
            .eventId(UUID.randomUUID())
            .title("SecondEvent")
            .dateTime(LocalDateTime.of(2021, 11, 11, 11 ,2))
            .description("description")
            .build();

    private final EventDTO thirdEvent = EventDTO.builder()
            .eventId(UUID.randomUUID())
            .title("ThirdEvent")
            .dateTime(LocalDateTime.of(2021, 11, 11, 11 ,3))
            .description("description")
            .build();

    private final List<EventDTO> events = List.of(firstEvent, secondEvent, thirdEvent);

    private final NoteDTO firstNote = NoteDTO.builder()
            .noteId(NOTE_ID)
            .title("1 note")
            .text("text 1")
            .build();

    private final NoteDTO secondNote = NoteDTO.builder()
            .noteId(UUID.randomUUID())
            .title("2 note")
            .text("text 2")
            .build();

    private final NoteDTO thirdNote = NoteDTO.builder()
            .noteId(UUID.randomUUID())
            .title("3 note")
            .text("text 3")
            .build();

    private final List<NoteDTO> notes = List.of(firstNote, secondNote, thirdNote);

    private final PaymentDTO firstPayment = PaymentDTO.builder()
            .paymentId(PAYMENT_ID)
            .paymentValue(500)
            .paymentDate(LocalDate.of(2021, 11, 11))
            .paid(true)
            .paidDate(LocalDate.of(2021, 11, 10))
            .comment("comment 1")
            .us(true)
            .incoming(true)
            .build();

    private final PaymentDTO secondPayment = PaymentDTO.builder()
            .paymentId(UUID.randomUUID())
            .paymentValue(600)
            .paymentDate(LocalDate.of(2021, 11, 13))
            .paid(true)
            .paidDate(LocalDate.of(2021, 11, 14))
            .comment("comment 2")
            .us(true)
            .incoming(true)
            .build();

    private final PaymentDTO thirdPayment = PaymentDTO.builder()
            .paymentId(UUID.randomUUID())
            .paymentValue(700)
            .paymentDate(LocalDate.of(2021, 11, 15))
            .paid(true)
            .paidDate(LocalDate.of(2021, 11, 16))
            .comment("comment 3")
            .us(true)
            .incoming(true)
            .build();

    List<PaymentDTO> payments = List.of(firstPayment, secondPayment, thirdPayment);

    private final PoaDTO firstPoa = PoaDTO.builder()
            .poaId(POA_ID)
            .poaType("regular")
            .payment("500")
            .kpc(true)
            .termination(true)
            .startDate(LocalDate.of(2021, 11, 11))
            .endDate(LocalDate.of(2021, 12, 11))
            .terminationNotificationDuty(true)
            .terminationNotificationDutyCompleted(true)
            .build();

    private final PoaDTO secondPoa = PoaDTO.builder()
            .poaId(UUID.randomUUID())
            .poaType("regular")
            .payment("300")
            .kpc(true)
            .termination(true)
            .startDate(LocalDate.of(2021, 11, 12))
            .endDate(LocalDate.of(2021, 12, 12))
            .terminationNotificationDuty(true)
            .terminationNotificationDutyCompleted(false)
            .build();

    private final PoaDTO thirdPoa = PoaDTO.builder()
            .poaId(UUID.randomUUID())
            .poaType("regular")
            .payment("200")
            .kpc(true)
            .termination(true)
            .startDate(LocalDate.of(2021, 11, 13))
            .endDate(LocalDate.of(2021, 12, 13))
            .terminationNotificationDuty(false)
            .terminationNotificationDutyCompleted(false)
            .build();

    List<PoaDTO> poaList = List.of(firstPoa, secondPoa, thirdPoa);

    private final TagDTO firstTag = TagDTO.builder()
            .tagId(TAG_ID)
            .name("1 tag")
            .build();

    private final TagDTO secondTag = TagDTO.builder()
            .tagId(UUID.randomUUID())
            .name("2 tag")
            .build();

    private final TagDTO thirdTag = TagDTO.builder()
            .tagId(UUID.randomUUID())
            .name("3 tag")
            .build();

    private final List<TagDTO> tags = List.of(firstTag, secondTag, thirdTag);

    private final TaskDTO firstTask = TaskDTO.builder()
            .taskId(TASK_ID)
            .priority(true)
            .deadline(LocalDateTime.of(20211, 11, 11, 11, 11))
            .description("1 task to do")
            .contactList(List.of(firstContact, secondContact, thirdContact))
            .build();

    private final TaskDTO secondTask = TaskDTO.builder()
            .taskId(UUID.randomUUID())
            .priority(true)
            .deadline(LocalDateTime.of(20211, 11, 11, 11, 12))
            .description("2 task to do")
            .contactList(List.of(firstContact, secondContact, thirdContact))
            .build();

    private final TaskDTO thirdTask = TaskDTO.builder()
            .taskId(UUID.randomUUID())
            .priority(true)
            .deadline(LocalDateTime.of(20211, 11, 11, 11, 13))
            .description("3 task to do")
            .contactList(List.of(firstContact, secondContact, thirdContact))
            .build();

    private final List<TaskDTO> tasks = List.of(firstTask, secondTask, thirdTask);

}
