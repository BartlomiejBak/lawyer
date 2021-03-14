package pl.bartekbak.lawyer.dto;

import org.springframework.format.annotation.DateTimeFormat;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.entity.Task;

import javax.persistence.Lob;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class LawsuitDTO {

    private int lawsuitId;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String caseSide;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate inputDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate deadline;

    @Size(min = 2, max = 50)
    private String signature;

    @PositiveOrZero
    private double claimAmount;

    @Lob
    @Size
    private String additionalInfo;

    private List<Contact> contactList;

    private List<Task> taskList;

    private List<Contact> plaintiff;

    private List<Contact> defendant;

    private Set<Event> eventSet;

}
