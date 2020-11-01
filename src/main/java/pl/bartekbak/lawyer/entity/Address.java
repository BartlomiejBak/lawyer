package pl.bartekbak.lawyer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;


}
