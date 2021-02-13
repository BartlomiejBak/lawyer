package pl.bartekbak.lawyer.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "street")
    @Size(min = 2, max = 50)
    private String street;

    @Column(name = "zip_code")
    @Size(max = 10)
    private String zipCode;

    @Column(name = "city")
    @Size(min = 2, max = 40)
    private String city;

    @Column(name = "country")
    @Size(min = 2, max = 40)
    private String country;

}
