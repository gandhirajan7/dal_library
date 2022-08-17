package com.csci5409.lmsbootapp.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/session")
public class SessionController {

    private String sessionKey = "Logged_User_Email";

    @Autowired
    private UserService userService;

    @Autowired
    private AmazonClient client;

    @PostMapping("/store_session")
    public String setUserInSession(@RequestBody UserModel userModel,  HttpServletRequest request){
        request.getSession().setAttribute(sessionKey, userModel.getEmailId());
        return userModel.getEmailId();
    }

    @GetMapping("/get_logged_user")
    public UserModel getUserFromSession(HttpServletRequest request){
        String emailId = request.getSession().getAttribute(sessionKey).toString();
        System.out.print("Logged user email="+emailId);
        return userService.getUserByEmail(emailId);
    }

    @PostMapping("destroy_session")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "Success";
    }

    @PostMapping("/upload")
    public String handleUploadForm(@RequestParam("file") MultipartFile multipart) {
        String message = "";
        try {
            message = client.upload(multipart);
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }
        return message;
    }
}