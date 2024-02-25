package com.example.upload.service;

import com.example.upload.ProductRepo.ProductRepo;
import com.example.upload.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo fileRepository;
    @Override
    public Product saveAttachment(MultipartFile file) throws Exception {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence " + fileName);
            }
            if (file.getBytes().length > (1024 * 1024)) {
                throw new Exception("File size exceeds maximum limit");
            }
            Product attachment = new Product(fileName, file.getContentType(), file.getBytes());
            return fileRepository.save(attachment);
        } catch (MaxUploadSizeExceededException e) {
            throw new MaxUploadSizeExceededException(file.getSize());
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }
    @Override
    public void saveFiles(MultipartFile[] files) {

        Arrays.asList(files).forEach(file -> {
            try {
                saveAttachment(file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    public List<Product> getAllFiles() {
        return fileRepository.findAll();
    }
}
