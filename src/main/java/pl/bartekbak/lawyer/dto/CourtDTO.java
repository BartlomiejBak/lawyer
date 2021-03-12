package pl.bartekbak.lawyer.dto;

import pl.bartekbak.lawyer.entity.Address;

import javax.validation.constraints.Size;

public class CourtDTO {

    private int courtId;

    @Size(min = 2, max = 50)
    private String name;

    private Address address;

    @Size(max = 255)
    private String description;

    @Size(max = 20)
    private String phone;
}
