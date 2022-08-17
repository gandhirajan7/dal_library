package com.csci5409.lmsbootapp.books;

import com.csci5409.lmsbootapp.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<BooksModel, Long> {
    List<BooksModel> findAllByLoanedUser(UserModel loanedUser);
    List<BooksModel> findAllByLoanedUserIsNotNull();
}
