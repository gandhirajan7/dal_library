package com.csci5409.lmsbootapp.user;

import com.csci5409.lmsbootapp.books.BooksModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }

    @Cacheable(value = "users")
    @GetMapping("/get_all")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @CacheEvict(value = "users", allEntries = true)
    @PostMapping("/save")
    public UserModel saveUser(@RequestBody UserModel userModel){
        return userService.saveUser(userModel);
    }

    @Cacheable(value = "user", key = "#id")
    @GetMapping("/get/{id}")
    public UserModel getUserById(@PathVariable  Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/loaned_books/{id}")
    public List<BooksModel> getLoanedBooks(@PathVariable Long id){
        return userService.getLoanedBooks(id);
    }

    @GetMapping("/getuserdetails")
    public UserModel getUserDetails(@RequestParam String emailId){
        return userService.getUserByEmail(emailId);
    }
}
