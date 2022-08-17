package com.csci5409.lmsbootapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BooksController
{
    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @CacheEvict(value = "books", allEntries = true)
    @PostMapping("/save")
    public BooksModel saveBooks(@RequestBody BooksModel booksModel)
    {
        return booksService.saveBooks(booksModel);
    }

    @Cacheable(value = "book", key = "#id")
    @GetMapping("/get_book/{id}")
    public BooksModel getBookById(@PathVariable Long id)
    {
        return booksService.getBookById(id);
    }

    @Cacheable(value = "books")
    @GetMapping("/get_all")
    public List<BooksModel> getAllBooks()
    {
        return booksService.getAllBooks();
    }

    @CacheEvict(value = "books", allEntries = true)
    @PutMapping("/update_book/{id}")
    public BooksModel updateBook(@PathVariable Long id, @RequestBody BooksModel booksModel) {
        return booksService.updateBook(id, booksModel);
    }

    @CacheEvict(value = "books", allEntries = true)
    @PutMapping("/assign_book")
    public BooksModel assignBookToSection(@RequestParam Long bookId, @RequestParam Long sectionId){
        return booksService.assignBookToSection(bookId, sectionId);
    }

    @CacheEvict(value = "books", allEntries = true)
    @PutMapping("/loan_book")
    public BooksModel assignBookToUser(@RequestParam Long bookId, @RequestParam Long userId){
        return booksService.assignBookToUser(bookId, userId);
    }

    @PutMapping("/return_book/{id}")
    public void returnLoanedBook(@PathVariable  Long id){
        booksService.returnLoanedBook(id);
    }

    @GetMapping("loaned_books")
    public List<BooksModel> getLoanedBooks(){
        return booksService.getLoanedBooks();
    }

    @GetMapping("/available_books")
    public List<BooksModel> getAvailableBooks(){
        return booksService.getBorrowableBooks();
    }

    @PutMapping("/upload_image/{id}")
    public BooksModel updateImageToBook(@PathVariable Long id,@RequestParam("file") MultipartFile imageFile){
        return booksService.updateImageToBook(id, imageFile);
    }
}