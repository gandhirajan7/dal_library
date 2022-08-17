package com.csci5409.lmsbootapp.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.csci5409.lmsbootapp.books.BooksModel;

@RestController
@RequestMapping(path = "/rest/sns")
public class SNSController {

    // @Autowired
    // SNSServiceImpl snsServiceImpl;
    @Autowired
    private SNSService snsService;

    @Autowired
    public SNSController(SNSService snsService) {
        this.snsService = snsService;
    }


    @PostMapping(path = "/subscribe")
    public ResponseEntity<Object> subscribe(@RequestBody SNSRequest SnsRequestPayload) {
        try {
            SNSResponse userSubscribeResponsePayload = snsService.subscribe(SnsRequestPayload);
            return new ResponseEntity<>(userSubscribeResponsePayload, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(path = "/notify")
    public ResponseEntity<Object> notifyEmail(){
        try {
            String userSubscribeResponsePayload = snsService.notifyEmail();
            return new ResponseEntity<>(userSubscribeResponsePayload, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "/notifybook")
    public void notifyNewBook(BooksModel booksModel) {
        try {
            snsService.notifyNewBook(booksModel);
            // return new ResponseEntity<>(userSubscribeResponsePayload, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

}
