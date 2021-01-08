package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "court")
public class Court {
    @Id
    @Column(name = "court_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courtId;
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    @Column(name = "description")
    private String description;

    public Court(String name, Address address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }
}
