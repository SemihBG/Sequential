package com.semihbkgr.sequential.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vocabulary_lists")
public class VocabularyList {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "vocabularyList")
    public List<Vocabulary> vocabularies;

}
