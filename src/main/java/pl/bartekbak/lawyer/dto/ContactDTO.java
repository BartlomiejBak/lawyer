package pl.bartekbak.lawyer.dto;

import org.springframework.format.annotation.DateTimeFormat;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.entity.Task;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class ContactDTO {

    private int contactId;

    @Size(min = 2, max = 50)
    private String name;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    private String email;

    @Email
    private String altEmail;

    @Size(max = 20)
    private String phone;

    @Size(max = 20)
    private String altPhone;

    @Size(max = 255)
    private String companyName;

    @Size(max = 11)
    private String pesel;

    @Size(max = 20)
    private String nip;

    @Size(max = 20)
    private String regon;

    @Size(max = 20)
    private String krs;

    @DateTimeFormat
    private LocalDateTime dateCreated;

    @DateTimeFormat
    private LocalDateTime dateModified;

    private Address address;

    private Address correspondenceAddress;

    private List<Lawsuit> lawsuitList;

    private List<Task> taskList;
}
