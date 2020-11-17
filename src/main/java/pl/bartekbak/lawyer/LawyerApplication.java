package pl.bartekbak.lawyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.bartekbak.lawyer.dao.AddressRepository;
import pl.bartekbak.lawyer.dao.NoteRepository;
import pl.bartekbak.lawyer.dao.TagRepository;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.entity.Tag;

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


    }

}
