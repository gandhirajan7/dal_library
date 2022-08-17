package com.csci5409.lmsbootapp.books;

import com.csci5409.lmsbootapp.sections.SectionsModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BooksService
{
    public BooksModel saveBooks(BooksModel booksModel);
    public List<BooksModel> getAllBooks();
    public BooksModel updateBook(Long id, BooksModel booksModel);
    public BooksModel getBookById(Long id);
    public BooksModel assignBookToSection(Long bookId, Long sectionId);
    public BooksModel assignBookToUser(Long bookId, Long userId);
    public void returnLoanedBook(Long bookId);
    List<BooksModel> getBorrowableBooks();
    public List<BooksModel> getLoanedBooks();
    public BooksModel updateImageToBook(Long bookId, MultipartFile imageFile);
}
