package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.AddressDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public AddressDTO toDto() {
        return AddressDTO.builder()
                .addressId(getAddressId())
                .street(getStreet())
                .zipCode(getZipCode())
                .city(getCity())
                .country(getCountry())
                .build();
    }

    public static Address fromDto(AddressDTO dto) {
        return Address.builder()
                .addressId(dto.getAddressId())
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .city(dto.getCity())
                .country(dto.getCountry())
                .build();
    }
}
