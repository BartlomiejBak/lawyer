package pl.bartekbak.lawyer.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
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
import pl.bartekbak.lawyer.service.AddressService;
import pl.bartekbak.lawyer.service.ContactService;
import pl.bartekbak.lawyer.service.CourtService;
import pl.bartekbak.lawyer.service.EventService;
import pl.bartekbak.lawyer.service.LawsuitService;
import pl.bartekbak.lawyer.service.NoteService;
import pl.bartekbak.lawyer.service.PaymentService;
import pl.bartekbak.lawyer.service.PoaService;
import pl.bartekbak.lawyer.service.TagService;
import pl.bartekbak.lawyer.service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AddressService addressService;
    private final ContactService contactService;
    private final CourtService courtService;
    private final EventService eventService;
    private final LawsuitService lawsuitService;
    private final NoteService noteService;
    private final PaymentService paymentService;
    private final PoaService poaService;
    private final TagService tagService;
    private final TaskService taskService;


    @Override
    public void run(String... args) {
        AddressDTO firstAddress = AddressDTO.builder()
                .country("Poland")
                .city("Warsaw")
                .street("Puławska 1")
                .zipCode("20-000")
                .build();
        AddressDTO secondAddress = AddressDTO.builder()
                .city("Warszawa")
                .street("Niepodległości 2")
                .zipCode("20-001")
                .build();
        AddressDTO thirdAddress = AddressDTO.builder()
                .city("Varsowia")
                .street("Mazowiecka 3")
                .zipCode("20-003")
                .build();

        ContactDTO firstContact = ContactDTO.builder()
                .name("1 contact")
                .firstName("1 name")
                .lastName("Doe")
                .address(firstAddress)
                .correspondenceAddress(firstAddress)
                .email("1email@example.com")
                .phone("555 222 222")
                .dateCreated(LocalDateTime.now())
                .pesel("12345678901")
                .build();
        ContactDTO secondContact = ContactDTO.builder()
                .name("2 contact")
                .firstName("2 name")
                .lastName("Moe")
                .address(secondAddress)
                .correspondenceAddress(secondAddress)
                .email("2email@example.com")
                .phone("555 222 223")
                .dateCreated(LocalDateTime.now())
                .pesel("12345678901")
                .build();
        ContactDTO thirdContact = ContactDTO.builder()
                .name("3 contact")
                .firstName("3 name")
                .lastName("Foe")
                .address(thirdAddress)
                .correspondenceAddress(thirdAddress)
                .email("3email@example.com")
                .phone("555 222 224")
                .dateCreated(LocalDateTime.now())
                .pesel("12345678901")
                .build();

        CourtDTO firstCourt = CourtDTO.builder()
                .name("1 court")
                .description("court number 555")
                .address(firstAddress)
                .phone("555-556-555")
                .build();
        CourtDTO secondCourt = CourtDTO.builder()
                .name("2 court")
                .description("court number 556")
                .address(secondAddress)
                .phone("555-556-556")
                .build();
        CourtDTO thirdCourt = CourtDTO.builder()
                .name("3 court")
                .description("court number 557")
                .address(firstAddress)
                .phone("555-556-557")
                .build();

        EventDTO firstEvent = EventDTO.builder()
                .title("first event")
                .dateTime(LocalDateTime.of(2021, 6, 14, 12, 30))
                .description("some description 1")
                .build();
        EventDTO secondEvent = EventDTO.builder()
                .title("second event")
                .dateTime(LocalDateTime.of(2021, 6, 14, 12, 31))
                .description("some description 2")
                .build();
        EventDTO thirdEvent = EventDTO.builder()
                .title("third event")
                .dateTime(LocalDateTime.of(2021, 6, 14, 12, 32))
                .description("some description 3")
                .build();

        LawsuitDTO firstLawsuit = LawsuitDTO.builder()
                .name("lawsuit no. 1223")
                .caseSide("defendant")
                .claimAmount(25000.2)
                .signature("ABC 123456")
                .deadline(LocalDate.of(2021, 5, 10))
                .additionalInfo("no important info")
                .inputDate(LocalDate.now())
                .build();
        LawsuitDTO secondLawsuit = LawsuitDTO.builder()
                .name("lawsuit no. 1224")
                .caseSide("plaintiff")
                .claimAmount(25000.2)
                .signature("ABC 123457")
                .deadline(LocalDate.of(2021, 6, 10))
                .additionalInfo("additional info")
                .inputDate(LocalDate.now())
                .build();
        LawsuitDTO thirdLawsuit = LawsuitDTO.builder()
                .name("lawsuit no. 1225")
                .caseSide("winner")
                .claimAmount(25000.2)
                .signature("ABC 123458")
                .deadline(LocalDate.of(2021, 7, 10))
                .additionalInfo("no additional info")
                .inputDate(LocalDate.now())
                .build();

        NoteDTO firstNote = NoteDTO.builder()
                .title("note no. 1")
                .text("some notes 1... ")
                .build();
        NoteDTO secondNote = NoteDTO.builder()
                .title("note no. 2")
                .text("some notes 2... ")
                .build();
        NoteDTO thirdNote = NoteDTO.builder()
                .title("note no. 3")
                .text("some notes 3... ")
                .build();

        PaymentDTO firstPayment = PaymentDTO.builder()
                .paymentDate(LocalDate.of(2021, 5, 11))
                .paymentValue(1000.5)
                .build();
        PaymentDTO secondPayment = PaymentDTO.builder()
                .paymentDate(LocalDate.of(2021, 6, 11))
                .paymentValue(1000.5)
                .build();
        PaymentDTO thirdPayment = PaymentDTO.builder()
                .paymentDate(LocalDate.of(2021, 7, 11))
                .paymentValue(1000.5)
                .build();


        PoaDTO firstPoa = PoaDTO.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2021, 5, 15))
                .kpc(true)
                .payment("paid")
                .termination(false)
                .payment("150 PLN")
                .poaType("common")
                .terminationNotificationDuty(true)
                .terminationNotificationDutyCompleted(false)
                .build();
        PoaDTO secondPoa = PoaDTO.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2021, 6, 15))
                .kpc(true)
                .payment("to be paid")
                .termination(false)
                .payment("160 PLN")
                .poaType("common 2")
                .terminationNotificationDuty(true)
                .terminationNotificationDutyCompleted(false)
                .build();
        PoaDTO thirdPoa = PoaDTO.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2021, 7, 15))
                .kpc(true)
                .payment("paid partially")
                .termination(false)
                .payment("250 PLN")
                .poaType("uncommon")
                .terminationNotificationDuty(true)
                .terminationNotificationDutyCompleted(false)
                .build();


        TagDTO firstTag = TagDTO.builder().name("cool tag 1").build();
        TagDTO secondTag = TagDTO.builder().name("cool tag 2").build();
        TagDTO thirdTag = TagDTO.builder().name("cool tag 3").build();


        TaskDTO firstTask = TaskDTO.builder()
                .description("do something 1")
                .deadline(LocalDateTime.of(2021, 5, 13, 12, 30))
                .priority(true)
                .build();
        TaskDTO secondTask = TaskDTO.builder()
                .description("do something 2")
                .deadline(LocalDateTime.of(2021, 6, 13, 12, 30))
                .priority(true)
                .build();
        TaskDTO thirdTask = TaskDTO.builder()
                .description("do something 3")
                .deadline(LocalDateTime.of(2021, 7, 13, 12, 30))
                .priority(true)
                .build();


        firstEvent.setRelatedLawsuit(firstLawsuit);
        secondEvent.setRelatedLawsuit(firstLawsuit);
        thirdEvent.setRelatedLawsuit(firstLawsuit);
        firstLawsuit.addContact(firstContact);
        secondLawsuit.addContact(secondContact);
        secondLawsuit.addContact(secondContact);
        thirdLawsuit.addContact(thirdContact);
        firstTask.addContact(firstContact);
        secondTask.addContact(secondContact);
        thirdTask.addContact(thirdContact);
        secondTask.addContact(thirdContact);

        addressService.saveAddress(firstAddress);
        addressService.saveAddress(secondAddress);
        addressService.saveAddress(thirdAddress);
        eventService.saveEvent(firstEvent);
        eventService.saveEvent(secondEvent);
        eventService.saveEvent(thirdEvent);
        lawsuitService.saveLawsuit(firstLawsuit);
        lawsuitService.saveLawsuit(secondLawsuit);
        lawsuitService.saveLawsuit(thirdLawsuit);
        contactService.saveContact(firstContact);
        contactService.saveContact(secondContact);
        contactService.saveContact(thirdContact);
        taskService.saveTask(firstTask);
        taskService.saveTask(secondTask);
        taskService.saveTask(thirdTask);
        courtService.saveCourt(firstCourt);
        courtService.saveCourt(secondCourt);
        courtService.saveCourt(thirdCourt);
        noteService.saveNote(firstNote);
        noteService.saveNote(secondNote);
        noteService.saveNote(thirdNote);
        paymentService.savePayment(firstPayment);
        paymentService.savePayment(secondPayment);
        paymentService.savePayment(thirdPayment);
        poaService.savePoa(firstPoa);
        poaService.savePoa(secondPoa);
        poaService.savePoa(thirdPoa);
        tagService.saveTag(firstTag);
        tagService.saveTag(secondTag);
        tagService.saveTag(thirdTag);
    }
}
