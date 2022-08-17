package com.csci5409.lmsbootapp.sections;


import com.csci5409.lmsbootapp.books.BooksModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SectionsService
{
    public List<SectionsModel> getAllSections();
    public SectionsModel findSectionById(Long id);
    public SectionsModel saveSection(SectionsModel sectionsModel);
    public List<BooksModel> getBooksInSection(Long id);
    public SectionsModel updateImageToSection(Long id, MultipartFile imageFile);
}
