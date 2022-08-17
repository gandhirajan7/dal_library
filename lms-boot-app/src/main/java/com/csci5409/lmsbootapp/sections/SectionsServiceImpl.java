package com.csci5409.lmsbootapp.sections;

import com.csci5409.lmsbootapp.books.BooksModel;
import com.csci5409.lmsbootapp.user.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SectionsServiceImpl implements SectionsService{

    private SectionsRepository sectionsRepository;

    private AmazonClient amazonClient;

    @Autowired
    public SectionsServiceImpl(SectionsRepository sectionsRepository, AmazonClient amazonClient) {
        this.sectionsRepository=sectionsRepository;
        this.amazonClient=amazonClient;
    }

    public List<SectionsModel> getAllSections() {
        return sectionsRepository.findAll();
    }

    public SectionsModel findSectionById(Long id) {
        return sectionsRepository.getById(id);
    }

    public SectionsModel saveSection(SectionsModel sectionsModel) {
        return sectionsRepository.save(sectionsModel);
    }

    public List<BooksModel> getBooksInSection(Long id){
        SectionsModel section = findSectionById(id);
        return section.getBooks();
    }

    public SectionsModel updateImageToSection(Long id, MultipartFile imageFile){
        SectionsModel sectionsModel = findSectionById(id);
        if(imageFile!=null){
            String imageUrl = amazonClient.upload(imageFile);
            sectionsModel.setImageUrl(imageUrl);
            sectionsModel = sectionsRepository.save(sectionsModel);
        }
        return sectionsModel;
    }
}
