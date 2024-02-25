package com.example.upload.service;

import com.example.upload.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Product saveAttachment(MultipartFile file) throws Exception;
    void saveFiles(MultipartFile[] files) throws Exception;
    List<Product> getAllFiles();
}
