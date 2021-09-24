package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbAddressRecord;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private int addressId;

    private String street;

    private String zipCode;

    private String city;

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

    public static Address fromDbRecord(DbAddressRecord record) {
        return Address.builder()
                .addressId(record.getAddressId())
                .street(record.getStreet())
                .zipCode(record.getZipCode())
                .city(record.getCity())
                .country(record.getCountry())
                .build();
    }

}
