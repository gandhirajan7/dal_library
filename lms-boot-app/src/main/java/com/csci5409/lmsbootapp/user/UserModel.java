package com.csci5409.lmsbootapp.user;

import com.csci5409.lmsbootapp.books.BooksModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emailId;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy="loanedUser")
    private List<BooksModel> loanedBooks;
}
