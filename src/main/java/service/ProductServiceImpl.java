package main.java.service;

import java.util.List;

import main.java.dao.ProductDAO;
import main.java.model.Product;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO = new ProductDAO();

	@Override
	public void addProduct(Product product) {
		productDAO.addProduct(product);
		
	}

	@Override
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Override
	public Product findProductById(int id) {
		return productDAO.findProductById(id);
	}

	@Override
	public List<Product> findProductsByName(String keyword) {
		return productDAO.findProductsByName(keyword);
	}

	@Override
	public boolean updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}

	@Override
	public boolean deleteProduct(int id) {
		return productDAO.deleteProduct(id);
	}

	@Override
	public List<Product> getProductsByPage(int page, int pageSize) {
		return productDAO.getProductsByPage(page, pageSize);
	}

	@Override
	public boolean addMultipleProducts(List<Product> products) {
		return productDAO.addMultipleProducts(products);
	}

}
