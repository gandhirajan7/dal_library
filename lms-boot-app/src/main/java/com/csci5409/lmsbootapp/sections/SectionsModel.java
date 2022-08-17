package com.csci5409.lmsbootapp.sections;

import com.csci5409.lmsbootapp.books.BooksModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionsModel implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sectionName;

    private String location;

    private String imageUrl;

    @Transient
    private Integer bookCount;

    @JsonIgnore
    @OneToMany(mappedBy="section")
    private List<BooksModel> books;

    @PostLoad
    public void processEntityObject(){
        bookCount = books==null ? 0 : books.size();
    }
}
