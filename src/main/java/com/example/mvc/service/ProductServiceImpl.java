package com.example.mvc.service;

import com.example.mvc.entity.Product;
import com.example.mvc.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractCrudService<Product> implements ProductService {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }
}
