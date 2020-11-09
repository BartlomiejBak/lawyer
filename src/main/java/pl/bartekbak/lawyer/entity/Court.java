package pl.bartekbak.lawyer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
