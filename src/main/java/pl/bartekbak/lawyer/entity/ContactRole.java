package pl.bartekbak.lawyer.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ContactRole {

    private UUID id;

    private Contact contact;

    private String role;
}
