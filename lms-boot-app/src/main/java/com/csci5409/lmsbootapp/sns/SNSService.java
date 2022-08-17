package com.csci5409.lmsbootapp.sns;

import com.csci5409.lmsbootapp.books.BooksModel;

public interface SNSService {
    
    public SNSResponse subscribe(SNSRequest SnsRequestPayload);
    public void notifyNewBook(BooksModel booksModel);
    public String notifyEmail();
        
}
