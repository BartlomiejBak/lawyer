package pl.bartekbak.lawyer.dto;

import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.entity.Lawsuit;

import javax.persistence.Lob;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class TaskDTO {

    private int taskId;

    private boolean priority;

    @Future
    private LocalDateTime deadline;

    @Lob
    @Size(max = 1500)
    private String description;

    private List<Lawsuit> lawsuitList;

    private List<Contact> contactList;
}
