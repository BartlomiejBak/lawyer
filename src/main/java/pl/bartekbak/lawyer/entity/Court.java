package pl.bartekbak.lawyer.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "COURT")
public class Court {
    @Id
    @Column(name = "court_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courtId;
    @Column(name = "name")
    private String name;
    @OneToOne
    private Address address;
    @Column(name = "description")
    private String description;
}
