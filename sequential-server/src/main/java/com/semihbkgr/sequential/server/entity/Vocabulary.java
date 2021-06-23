package com.semihbkgr.sequential.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
//This entity is being used by JPA
public class Vocabulary {

    @Id
    private int id;
    private String eng;
    private String tr;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    private VocabularyList vocabularyList;

}
