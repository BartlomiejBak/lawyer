package pl.bartekbak.lawyer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TagDTO {

    private int tagId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
