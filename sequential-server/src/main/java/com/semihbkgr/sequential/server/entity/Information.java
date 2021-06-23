package com.semihbkgr.sequential.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="information")
@Getter
@Setter
//This entity is being used by Hibernate
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="list_id")
    private int listId;

    @Column(name="list_name")
    private String listName;

    @Column(name="list_size")
    private int listSize;

}
