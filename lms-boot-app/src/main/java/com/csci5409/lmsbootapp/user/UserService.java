package com.csci5409.lmsbootapp.user;

import com.csci5409.lmsbootapp.books.BooksModel;

import java.util.List;

public interface UserService {
    public List<UserModel> getAllUsers();
    public UserModel saveUser(UserModel userModel);
    public UserModel getUserById(Long id);
    public List<BooksModel> getLoanedBooks(Long id);
    public UserModel getUserByEmail(String emailId);
}
