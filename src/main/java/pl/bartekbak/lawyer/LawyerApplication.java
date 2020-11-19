package pl.bartekbak.lawyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.bartekbak.lawyer.dao.*;
import pl.bartekbak.lawyer.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LawyerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(LawyerApplication.class, args);

        //populate address table
        AddressRepository addressRepository =
                context.getBean(AddressRepository.class);
        Address addressA = new Address("Sądowa 22", "26-600", "Radom", "Polska");
        Address addressB = new Address("Sądowa 23", "26-600", "Radom", "Polska");
        Address addressC = new Address("Kliencka 24", "26-600", "Radom", "Polska");
        Address addressD = new Address("Kliencka 25", "26-600", "Radom", "Polska");
        addressRepository.save(addressA);
        addressRepository.save(addressB);
        addressRepository.save(addressC);
        addressRepository.save(addressD);

        //populate note table
        NoteRepository noteRepository = context.getBean(NoteRepository.class);
        Note noteA = new Note("Notka 1", "lorem ipsum.....1");
        Note noteB = new Note("Notka 2", "lorem ipsum.....2");
        Note noteC = new Note("Notka 3", "lorem ipsum.....3");
        Note noteD = new Note("Notka 4", "lorem ipsum.....4");
        noteRepository.save(noteA);
        noteRepository.save(noteB);
        noteRepository.save(noteC);
        noteRepository.save(noteD);

        //populate tag table
        TagRepository tagRepository = context.getBean(TagRepository.class);
        Tag tagA = new Tag("Sprawy sądowe");
        Tag tagB = new Tag("Sprawy przedsądowe");
        Tag tagC = new Tag("Sprawy pozasądowe");
        Tag tagD = new Tag("Sprawy nieokreślone");
        tagRepository.save(tagA);
        tagRepository.save(tagB);
        tagRepository.save(tagC);
        tagRepository.save(tagD);

        //populate court table
        CourtRepository courtRepository = context.getBean(CourtRepository.class);
        Court courtA = new Court("pierwszy sąd", addressA, "taki wysoki budynek");
        Court courtB = new Court("drugi sąd", addressB, "taki szeroki budynek");
        Court courtC = new Court("trzeci sąd", addressC, "taki kolorowy budynek");
        Court courtD = new Court("czwarty sąd", addressD, "taki stary budynek");
        courtRepository.save(courtA);
        courtRepository.save(courtB);
        courtRepository.save(courtC);
        courtRepository.save(courtD);

        //populate contact table
        ContactRepository contactRepository = context.getBean(ContactRepository.class);
        Contact contactA = Contact.builder()
                .name("pierwszy kontakt")
                .address(addressA)
                .build();
        Contact contactB = Contact.builder()
                .name("drugi kontakt")
                .address(addressB)
                .build();
        Contact contactC = Contact.builder()
                .name("trzeci kontakt")
                .address(addressC)
                .build();
        Contact contactD = Contact.builder()
                .name("czwarty kontakt")
                .address(addressD)
                .build();
        contactRepository.save(contactA);
        contactRepository.save(contactB);
        contactRepository.save(contactC);
        contactRepository.save(contactD);

        //populate lawsuit table
        LawsuitRepository lawsuitRepository = context.getBean(LawsuitRepository.class);
        Lawsuit lawsuitA = Lawsuit.builder()
                .caseSide("left")
                .claimAmount(20000)
                .name("case 1")
                .build();
        Lawsuit lawsuitB = Lawsuit.builder()
                .caseSide("left")
                .claimAmount(20000)
                .name("case 2")
                .build();
        Lawsuit lawsuitC = Lawsuit.builder()
                .caseSide("left")
                .claimAmount(20000)
                .name("case 3")
                .build();
        Lawsuit lawsuitD = Lawsuit.builder()
                .caseSide("left")
                .claimAmount(20000)
                .name("case 4")
                .build();
        lawsuitRepository.save(lawsuitA);
        lawsuitRepository.save(lawsuitB);
        lawsuitRepository.save(lawsuitC);
        lawsuitRepository.save(lawsuitD);


        //populate task table
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        Task taskA = Task.builder()
                .description("very important task 1")
                .priority(true)
                .deadline(LocalDateTime.of(2021, 12, 12, 0, 0))
                .contactList(List.of(contactA, contactB))
                .build();
        Task taskB = Task.builder()
                .description("very important task 2")
                .priority(true)
                .deadline(LocalDateTime.of(2021, 11, 12, 0, 0))
                .contactList(List.of(contactC, contactB))
                .build();
        Task taskC = Task.builder()
                .description("very important task 3")
                .priority(true)
                .deadline(LocalDateTime.of(2021, 2, 12, 0, 0))
                .contactList(List.of(contactA, contactC))
                .build();
        Task taskD = Task.builder()
                .description("very important task 4")
                .priority(true)
                .deadline(LocalDateTime.of(2021, 3, 12, 0, 0))
                .contactList(List.of(contactD, contactB))
                .build();
        taskRepository.save(taskA);
        taskRepository.save(taskB);
        taskRepository.save(taskC);
        taskRepository.save(taskD);

    }
}
