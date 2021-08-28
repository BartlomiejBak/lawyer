package pl.bartekbak.lawyer.entity;

import lombok.*;
import pl.bartekbak.lawyer.dto.CourtDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Court {
    private int courtId;

    private String name;

    private Address address;

    private String description;

    private String phone;

    public CourtDTO toDto() {
        return CourtDTO.builder()
                .courtId(courtId)
                .name(name)
                .address(address.toDto())
                .description(description)
                .phone(phone)
                .build();
    }

    public static Court fromDto(CourtDTO dto) {
        return Court.builder()
                .courtId(dto.getCourtId())
                .name(dto.getName())
                .address(Address.fromDto(dto.getAddress()))
                .description(dto.getDescription())
                .phone(dto.getPhone())
                .build();
    }

}
