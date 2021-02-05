package pl.bartekbak.lawyer.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.entity.Task;
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
import java.util.List;

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
        int count = addressService.findAllAddresses().size();
        if (count == 0) loadData();
    }

    private void loadData() {
        Address firstAddress = Address.builder()
                .country("Poland")
                .city("Warsaw")
                .street("Puławska 1")
                .zipCode("20-000")
                .build();
        Address secondAddress = Address.builder()
                .city("Warszawa")
                .street("Niepodległości 2")
                .zipCode("20-001")
                .build();
        Address thirdAddress = Address.builder()
                .city("Varsowia")
                .street("Mazowiecka 3")
                .zipCode("20-003")
                .build();

        Contact firstContact = Contact.builder()
                .name("1 contact")
                .firstName("1 name")
                .lastName("Doe")
                .address(firstAddress)
                .email("1email@example.com")
                .phone("555 222 222")
                .dateCreated(LocalDate.now())
                .pesel("123123312321321")
                .build();
        Contact secondContact = Contact.builder()
                .name("2 contact")
                .firstName("2 name")
                .lastName("Moe")
                .address(secondAddress)
                .email("2email@example.com")
                .phone("555 222 223")
                .dateCreated(LocalDate.now())
                .pesel("123123312321322")
                .build();
        Contact thirdContact = Contact.builder()
                .name("3 contact")
                .firstName("3 name")
                .lastName("Foe")
                .address(thirdAddress)
                .email("3email@example.com")
                .phone("555 222 224")
                .dateCreated(LocalDate.now())
                .pesel("123123312321323")
                .build();

        Court firstCourt = Court.builder()
                .name("1 court")
                .description("court number 555")
                .address(firstAddress)
                .phone("555-556-555")
                .build();
        Court secondCourt = Court.builder()
                .name("2 court")
                .description("court number 556")
                .address(secondAddress)
                .phone("555-556-556")
                .build();
        Court thirdCourt = Court.builder()
                .name("3 court")
                .description("court number 557")
                .address(firstAddress)
                .phone("555-556-557")
                .build();

        Event firstEvent = Event.builder()
                .title("first event")
                .dateTime(LocalDateTime.of(2021, 6, 14, 12, 30))
                .description("some description 1")
                .build();
        Event secondEvent = Event.builder()
                .title("second event")
                .dateTime(LocalDateTime.of(2021, 6, 14, 12, 31))
                .description("some description 2")
                .build();
        Event thirdEvent = Event.builder()
                .title("third event")
                .dateTime(LocalDateTime.of(2021, 6, 14, 12, 32))
                .description("some description 3")
                .build();

        Lawsuit firstLawsuit = Lawsuit.builder()
                .name("lawsuit no. 1223")
                .caseSide("defendant")
                .claimAmount(25000.2)
                .signature("ABC 123456")
                .deadline(LocalDate.of(2021, 5, 10))
                .additionalInfo("no important info")
                .inputDate(LocalDate.now())
                .build();
        Lawsuit secondLawsuit = Lawsuit.builder()
                .name("lawsuit no. 1224")
                .caseSide("plaintiff")
                .claimAmount(25000.2)
                .signature("ABC 123457")
                .deadline(LocalDate.of(2021, 6, 10))
                .additionalInfo("additional info")
                .inputDate(LocalDate.now())
                .build();
        Lawsuit thirdLawsuit = Lawsuit.builder()
                .name("lawsuit no. 1225")
                .caseSide("winner")
                .claimAmount(25000.2)
                .signature("ABC 123458")
                .deadline(LocalDate.of(2021, 7, 10))
                .additionalInfo("no additional info")
                .inputDate(LocalDate.now())
                .build();

        Note firstNote = Note.builder()
                .title("note no. 1")
                .text("some notes 1... ")
                .build();
        Note secondNote = Note.builder()
                .title("note no. 2")
                .text("some notes 2... ")
                .build();
        Note thirdNote = Note.builder()
                .title("note no. 3")
                .text("some notes 3... ")
                .build();

        Payment firstPayment = Payment.builder()
                .paymentDate(LocalDate.of(2021, 5, 11))
                .paymentValue(1000.5)
                .build();
        Payment secondPayment = Payment.builder()
                .paymentDate(LocalDate.of(2021, 6, 11))
                .paymentValue(1000.5)
                .build();
        Payment thirdPayment = Payment.builder()
                .paymentDate(LocalDate.of(2021, 7, 11))
                .paymentValue(1000.5)
                .build();


        Poa firstPoa = Poa.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2021, 5, 15))
                .kpc(true)
                .payment("paid")
                .termination(false)
                .payment("150 PLN")
                .type("common")
                .terminationNotificationDuty(true)
                .terminationNotificationDutyCompleted(false)
                .build();
        Poa secondPoa = Poa.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2021, 6, 15))
                .kpc(true)
                .payment("to be paid")
                .termination(false)
                .payment("160 PLN")
                .type("common 2")
                .terminationNotificationDuty(true)
                .terminationNotificationDutyCompleted(false)
                .build();
        Poa thirdPoa = Poa.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2021, 7, 15))
                .kpc(true)
                .payment("paid partially")
                .termination(false)
                .payment("250 PLN")
                .type("uncommon")
                .terminationNotificationDuty(true)
                .terminationNotificationDutyCompleted(false)
                .build();


        Tag firstTag = Tag.builder().name("cool tag 1").build();
        Tag secondTag = Tag.builder().name("cool tag 2").build();
        Tag thirdTag = Tag.builder().name("cool tag 3").build();


        Task firstTask = Task.builder()
                .description("do something 1")
                .contactList(List.of())
                .lawsuitList(List.of())
                .deadline(LocalDateTime.of(2021, 5, 13, 12, 30))
                .priority(true)
                .build();
        Task secondTask = Task.builder()
                .description("do something 2")
                .contactList(List.of())
                .lawsuitList(List.of())
                .deadline(LocalDateTime.of(2021, 6, 13, 12, 30))
                .priority(true)
                .build();
        Task thirdTask = Task.builder()
                .description("do something 3")
                .contactList(List.of())
                .lawsuitList(List.of())
                .deadline(LocalDateTime.of(2021, 7, 13, 12, 30))
                .priority(true)
                .build();

        firstContact.addTask(firstTask);
        firstContact.addLawsuit(firstLawsuit);
        secondContact.addTask(secondTask);
        secondContact.addLawsuit(secondLawsuit);
        thirdContact.addTask(thirdTask);
        thirdContact.addTask(secondTask);
        thirdContact.addLawsuit(thirdLawsuit);
        thirdContact.addLawsuit(secondLawsuit);


        addressService.saveAddress(firstAddress);
        addressService.saveAddress(secondAddress);
        addressService.saveAddress(thirdAddress);
        taskService.saveTask(firstTask);
        taskService.saveTask(secondTask);
        taskService.saveTask(thirdTask);
        eventService.saveEvent(firstEvent);
        eventService.saveEvent(secondEvent);
        eventService.saveEvent(thirdEvent);
        lawsuitService.saveLawsuit(firstLawsuit);
        lawsuitService.saveLawsuit(secondLawsuit);
        lawsuitService.saveLawsuit(thirdLawsuit);
        contactService.saveContact(firstContact);
        contactService.saveContact(secondContact);
        contactService.saveContact(thirdContact);
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