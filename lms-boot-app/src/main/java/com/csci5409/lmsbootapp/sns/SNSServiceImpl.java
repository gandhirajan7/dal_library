package com.csci5409.lmsbootapp.sns;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.csci5409.lmsbootapp.books.BooksModel;


@Service
public class SNSServiceImpl implements SNSService {
    
    @Value(value = "${aws.cognito.region}")
    private String region;

    @Value(value = "${aws.access-key}")
    private String accessKey;
    @Value(value = "${aws.access-secret}")
    private String secretKey;
    @Value(value = "${aws.session-token}")
    private String sessionToken;

    private String TOPIC_ARN_EMAIL = "arn:aws:sns:us-east-1:974654846627:NewBookEmail";
    private String TOPIC_ARN_NEWBOOK = "";
    private String EMAIL_SUBJECT = "We got new Book for you!";
    private String EMAIL_MESSAGE = "%s Book added to the store You can get it from the store now.";

    private static AmazonSNSClient snsClient = null;

    @PostMapping(path = "/subscribe")
    public SNSResponse subscribe (SNSRequest emailRequestPayload){
         
         //credential of AWS
         BasicSessionCredentials credentials = new BasicSessionCredentials(accessKey, secretKey,sessionToken);
         //creating SNS client
         snsClient = (AmazonSNSClient)AmazonSNSClientBuilder.standard()
         .withRegion(region).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

         //ARN response sent
         SNSResponse response = new SNSResponse();

         String email = emailRequestPayload.getEmail();
         try{
             SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN_EMAIL, "email", email);
             snsClient.subscribe(subscribeRequest);
             response.setTopic(TOPIC_ARN_EMAIL);
             return response;
             
         }catch(Exception e){
             e.printStackTrace();
             System.exit(1);
         }
         return response;
    }
    
    @PostMapping(path = "/notify")  
    public String notifyEmail(){
         //credential of AWS
        String response="Email not sent Successfully";
    	BasicSessionCredentials credentials = new BasicSessionCredentials(accessKey, secretKey,sessionToken);
        //creating SNS client
        snsClient = (AmazonSNSClient)AmazonSNSClientBuilder.standard()
        .withRegion(region)
        .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        try{
            //Publish message to the topic and send email to all subscriber
             snsClient.publish(TOPIC_ARN_EMAIL, EMAIL_MESSAGE, EMAIL_SUBJECT);
             response = "Email Sent successfully";
            
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return response;
        
    }

    @PostMapping(path = "/notifybook")  
    public void notifyNewBook(BooksModel booksModel){
         //credential of AWS
        String response="Email not sent Successfully";
    	BasicSessionCredentials credentials = new BasicSessionCredentials(accessKey, secretKey,sessionToken);
        //creating SNS client
        snsClient = (AmazonSNSClient)AmazonSNSClientBuilder.standard()
        .withRegion(region)
        .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        try{
            //Publish message to the topic and send email to all subscriber
             snsClient.publish(TOPIC_ARN_EMAIL, String.format(EMAIL_MESSAGE, booksModel.getName()), EMAIL_SUBJECT);
             response = "Email Sent successfully";
            
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }
}