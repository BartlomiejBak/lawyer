package pl.bartekbak.lawyer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(name = "name")
    private String name;

}
