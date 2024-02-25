package com.example.upload.ProductRepo;

import com.example.upload.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
