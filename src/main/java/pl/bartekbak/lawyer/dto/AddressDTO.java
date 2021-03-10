package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AddressDTO {

    private int addressId;

    @Size(min = 2, max = 50)
    private String street;

    @Size(max = 10)
    private String zipCode;

    @Size(min = 2, max = 40)
    private String city;

    @Size(min = 2, max = 40)
    private String country;
}
