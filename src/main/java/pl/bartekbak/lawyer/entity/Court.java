package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.CourtDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
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
