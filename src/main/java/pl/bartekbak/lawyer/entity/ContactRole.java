package pl.bartekbak.lawyer.entity;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ContactRole {

    private int id;

    private Contact contact;

    private String role;
}
