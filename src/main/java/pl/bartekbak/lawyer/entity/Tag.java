package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbTagRecord;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    private UUID tagId;

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

    public static Tag fromDbRecord(DbTagRecord record) {
        return Tag.builder()
                .tagId(record.getTagId())
                .name(record.getName())
                .build();
    }
}
