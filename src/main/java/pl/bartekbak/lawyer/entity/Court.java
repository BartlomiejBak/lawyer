package pl.bartekbak.lawyer.entity;

import lombok.*;
import org.hibernate.Hibernate;
import pl.bartekbak.lawyer.dto.CourtDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @JoinColumn(name = "address", referencedColumnName = "address_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Court court = (Court) o;
        return Objects.equals(courtId, court.courtId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
