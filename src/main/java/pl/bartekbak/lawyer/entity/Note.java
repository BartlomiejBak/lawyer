package pl.bartekbak.lawyer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;



}
