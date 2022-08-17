package com.csci5409.lmsbootapp.books;

import com.csci5409.lmsbootapp.sections.SectionsModel;
import com.csci5409.lmsbootapp.sections.SectionsService;
import com.csci5409.lmsbootapp.sns.SNSService;
import com.csci5409.lmsbootapp.user.AmazonClient;
import com.csci5409.lmsbootapp.user.UserModel;
import com.csci5409.lmsbootapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService
{
    private BooksRepository booksRepository;

    private SectionsService sectionsService;

    private UserService userService;

    private SNSService snsService;

    private AmazonClient amazonClient;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository, SectionsService sectionsService, UserService userService, SNSService snsService, AmazonClient amazonClient) {
        this.booksRepository=booksRepository;
        this.sectionsService=sectionsService;
        this.userService=userService;
        this.snsService=snsService;
        this.amazonClient=amazonClient;
    }

    public BooksModel saveBooks(BooksModel booksModel)
    {
        snsService.notifyNewBook(booksModel);
        return booksRepository.save(booksModel);
    }

    public List<BooksModel> getAllBooks() {
        List<BooksModel> books = booksRepository.findAll();


        return books;
    }

    public BooksModel getBookById(Long id) {
        return booksRepository.getById(id);
    }

    public BooksModel updateBook(Long id, BooksModel booksModel) {
        BooksModel book = getBookById(id);
        book.setName(booksModel.getName());
        book.setAuthor(booksModel.getAuthor());
        book.setGenre(booksModel.getGenre());
        return booksRepository.save(book);
    }

    public BooksModel assignBookToSection(Long bookId, Long sectionId) {
        BooksModel book = getBookById(bookId);
        SectionsModel section = sectionsService.findSectionById(sectionId);
        book.setSection(section);
        return booksRepository.save(book);
    }

    public BooksModel assignBookToUser(Long bookId, Long userId){
        UserModel loaningUser = userService.getUserById(userId);
        BooksModel book = getBookById(bookId);
        book.setLoanedUser(loaningUser);
        return booksRepository.save(book);
    }

    public void returnLoanedBook(Long bookId){
        BooksModel book = getBookById(bookId);
        book.setLoanedUser(null);
        booksRepository.save(book);
    }

    public List<BooksModel> getBorrowableBooks(){
        //if loaned user is null then the book is available for loaning
        return booksRepository.findAllByLoanedUser(null);
    }

    public List<BooksModel> getLoanedBooks(){
        //if loaned user is present then the books is not available to borrow
        return booksRepository.findAllByLoanedUserIsNotNull();
    }

    public BooksModel updateImageToBook(Long bookId, MultipartFile imageFile){
        BooksModel book = getBookById(bookId);
        if(imageFile!=null){
            String imageUrl=amazonClient.upload(imageFile);
            book.setImageUrl(imageUrl);
            book = booksRepository.save(book);
        }
        return book;
    }
}
