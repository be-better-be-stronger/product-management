package main.java.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.DBConnection;
import main.java.model.Product;

public class ProductDAO {

	public void addProduct(Product product) {
		String sql = "{CALL proc_add_product(?, ?, ?)}";
		try(Connection connection = DBConnection.getConnection()){
			CallableStatement stmt = connection.prepareCall(sql);
			stmt.setString(1, product.getName());
			stmt.setDouble(2, product.getPrice());
			stmt.setInt(3, product.getQuantity());
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		String sql = "{call proc_get_all_products()}";
		try(Connection connection = DBConnection.getConnection()){
			CallableStatement stmt = connection.prepareCall(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Product product = new Product(
						resultSet.getInt("id"), 
						resultSet.getString("name"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("quantity")
				);
				list.add(product);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product findProductById(int id) {
		String sql = "{CALL proc_find_product_by_id(?)}";
        Product product = null;
        try(Connection connection = DBConnection.getConnection()){
        	CallableStatement stmt = connection.prepareCall(sql);
        	stmt.setInt(1, id);
        	ResultSet resultSet = stmt.executeQuery();
        	if(resultSet.next()) {
        		product = new Product(
        				resultSet.getInt("id"), 
        				resultSet.getString("name"), 
        				resultSet.getDouble("price"), 
        				resultSet.getInt("quantity")
        		);
        	}
        }catch (SQLException e) {
			e.printStackTrace();
		}
        return product;
	}

	public List<Product> findProductsByName(String keyword) {
		List<Product> list = new ArrayList<>();
		String sql = "{CALL proc_find_products_by_name(?)}";
		try(Connection connection = DBConnection.getConnection()){
			CallableStatement stmt = connection.prepareCall(sql);
			stmt.setString(1, keyword);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Product product = new Product(
						resultSet.getInt("id"), 
						resultSet.getString("name"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("quantity")						
				);
				list.add(product);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateProduct(Product product) {
		String sql = "{CALL proc_update_product(?, ?, ?, ?)}";
		try(Connection connection = DBConnection.getConnection()){
			CallableStatement stmt = connection.prepareCall(sql);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			stmt.setInt(4, product.getQuantity());
			
			return stmt.executeUpdate() > 0;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	public boolean deleteProduct(int id) {
		String sql = "{CALL proc_delete_product(?)}";
		try (Connection connection = DBConnection.getConnection()) {
			CallableStatement stmt = connection.prepareCall(sql);
	        stmt.setInt(1, id);
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public List<Product> getProductsByPage(int page, int pageSize) {
		List<Product> list = new ArrayList<>();
        String sql = "{CALL proc_get_products_by_page(?, ?)}";

        try (Connection connection  =DBConnection.getConnection()) {
        	CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1, pageSize);
            stmt.setInt(2, (page - 1) * pageSize);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	public boolean addMultipleProducts(List<Product> products) {
		String sql = "{CALL proc_add_product(?, ?, ?)}";
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				for(Product product : products) {
					stmt.setString(1, product.getName());
					stmt.setDouble(2, product.getPrice());
					stmt.setInt(3, product.getQuantity());
					stmt.execute();
				}
			} 
			connection.commit();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return false;
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
