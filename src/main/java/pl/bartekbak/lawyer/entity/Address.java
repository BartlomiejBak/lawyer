package pl.bartekbak.lawyer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbAddressRecord;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @JsonProperty("address_id")
    private UUID addressId;

    private String street;

    @JsonProperty("zip_code")
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
