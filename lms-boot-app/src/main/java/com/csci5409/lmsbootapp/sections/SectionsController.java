package com.csci5409.lmsbootapp.sections;

import com.csci5409.lmsbootapp.books.BooksModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/section")
public class SectionsController
{
    private SectionsService sectionsService;

    @Autowired
    public SectionsController(SectionsService sectionsService) {
        this.sectionsService=sectionsService;
    }

    @PostMapping("/save")
    public SectionsModel saveSection(@RequestBody SectionsModel sectionsModel) {
        return sectionsService.saveSection(sectionsModel);
    }

    @GetMapping("/get_sections")
    public List<SectionsModel> getAllSections()
    {
        return sectionsService.getAllSections();
    }

    @GetMapping("/get_section/{id}")
    public SectionsModel getSectionById(@PathVariable Long id)
    {
        return sectionsService.findSectionById(id);
    }

    @GetMapping("/get_books/{id}")
    public List<BooksModel> getBooksInSection(@PathVariable Long id){
        return sectionsService.getBooksInSection(id);
    }

    @PutMapping("/upload_image/{id}")
    public SectionsModel updateImageToSection(@PathVariable Long id,@RequestParam("file") MultipartFile imageFile){
        return sectionsService.updateImageToSection(id, imageFile);
    }
}
