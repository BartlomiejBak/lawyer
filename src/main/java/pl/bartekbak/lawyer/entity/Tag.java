package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.TagDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    private int tagId;

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
