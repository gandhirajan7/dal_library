package com.csci5409.lmsbootapp.user;

import com.csci5409.lmsbootapp.books.BooksModel;
import com.csci5409.lmsbootapp.sns.SNSRequest;
import com.csci5409.lmsbootapp.sns.SNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private SNSService snsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SNSService snsService){
        this.userRepository=userRepository;
        this.snsService=snsService;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel saveUser(UserModel userModel){
        snsService.subscribe(new SNSRequest(userModel.getEmailId()));
        return this.userRepository.save(userModel);
    }

    public UserModel getUserById(Long id){
        return userRepository.getById(id);
    }

    public List<BooksModel> getLoanedBooks(Long id){
        UserModel user = getUserById(id);
        return user.getLoanedBooks();
    }

    public UserModel getUserByEmail(String emailId){
        return userRepository.findByEmailId(emailId);
    }
}
