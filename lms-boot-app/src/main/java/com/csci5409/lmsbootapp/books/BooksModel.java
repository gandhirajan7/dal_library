package com.csci5409.lmsbootapp.books;

import com.csci5409.lmsbootapp.sections.SectionsModel;
import com.csci5409.lmsbootapp.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String genre;

    private String imageUrl;

    @Transient
    private Boolean isBookBorrowable;

    @Transient
    private SectionsModel assignedSection;

    @PostLoad
    public void processEntityObject(){
        isBookBorrowable = loanedUser==null;
        assignedSection=section;
    }

    @JsonIgnore
    @ManyToOne(targetEntity = SectionsModel.class)
    @JoinColumn(name="section_id")
    private SectionsModel section;

    @JsonIgnore
    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(name="user_id")
    private UserModel loanedUser;
}
