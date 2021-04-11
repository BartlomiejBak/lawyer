package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.TagDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "name")
    private String name;

    public TagDTO toDto() {
        return TagDTO.builder()
                .tagId(tagId)
                .name(name)
                .build();
    }

    public static Tag fromDto(TagDTO dto) {
        return Tag.builder()
                .tagId(dto.getTagId())
                .name(dto.getName())
                .build();
    }
}
