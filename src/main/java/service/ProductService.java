package main.java.service;

import java.util.List;

import main.java.model.Product;

public interface ProductService {
	
	void addProduct(Product product);

    List<Product> getAllProducts();

    Product findProductById(int id);
    
    List<Product> findProductsByName(String keyword);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);

    List<Product> getProductsByPage(int page, int pageSize);

    boolean addMultipleProducts(List<Product> products);
    
}
