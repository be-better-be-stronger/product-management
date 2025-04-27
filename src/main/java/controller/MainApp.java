package main.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.model.Product;
import main.java.service.ProductService;
import main.java.service.ProductServiceImpl;

public class MainApp {
	private static final Scanner scanner = new Scanner(System.in);
	private static final ProductService service = new ProductServiceImpl();

	public static void main(String[] args) {
		while (true) {
			showMenu();
			int choice = getInputInt("Nhập lựa chọn: ");
			switch (choice) {
			case 1:
				addProduct();
				break;
			case 2:
				listAllProducts();
				break;
			case 3:
				findProductById();
				break;
			case 4:
				findProductByName();
				break;
			case 5:
				updateProduct();
				break;
			case 6:
				deleteProduct();
				break;
			case 7:
				listProductsByPage();
				break;
			case 8:
				addMultipleProducts();
				break;
			case 0:
				System.out.println("Tạm biệt bạn yêu 💛!");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
			}
		}
	}

	private static void showMenu() {
		System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
		System.out.println("1. Thêm sản phẩm");
		System.out.println("2. Hiển thị tất cả sản phẩm");
		System.out.println("3. Tìm sản phẩm theo ID");
		System.out.println("4. Tìm sản phẩm theo tên");
		System.out.println("5. Cập nhật sản phẩm");
		System.out.println("6. Xóa sản phẩm");
		System.out.println("7. Hiển thị sản phẩm theo phân trang");
		System.out.println("8. Thêm nhiều sản phẩm (Transaction)");
		System.out.println("0. Thoát chương trình");
		System.out.println("=============================");
	}

	private static void addProduct() {
		System.out.println("== Thêm sản phẩm mới ==");
		String name = getInputString("Tên sản phẩm: ");
		double price = getInputDouble("Giá sản phẩm: ");
		int quantity = getInputInt("Số lượng: ");
		Product product = new Product(name, price, quantity);
		service.addProduct(product);
		System.out.println("➔ Thêm sản phẩm thành công!");
	}

	private static void listAllProducts() {
		System.out.println("== Danh sách tất cả sản phẩm ==");
		System.out.printf("%-5s %-25s %-12s %-5s\n", 
				"ID", "Tên sản phẩm", "Giá", "Số lượng");
		List<Product> products = service.getAllProducts();
		for (Product p : products) {
			System.out.printf("%-5d %-25s %-12.2f %-5d\n", 
					p.getId(), p.getName(), p.getPrice(), p.getQuantity());
		}
	}

	private static void findProductById() {
		int id = getInputInt("Nhập ID sản phẩm: ");
		Product product = service.findProductById(id);
		if (product != null) {
			System.out.printf("%-5s %-25s %-12s %-5s\n", 
					"ID", "Tên sản phẩm", "Giá", "Số lượng");
			System.out.printf("%-5d %-25s %-12.2f %-5d\n", 
					product.getId(), product.getName(), product.getPrice(), product.getQuantity());
		} else {
			System.out.println("Không tìm thấy sản phẩm!");
		}
	}

	private static void findProductByName() {
		String keyword = getInputString("Nhập từ khóa tên sản phẩm: ");
		List<Product> products = service.findProductsByName(keyword);
		if (products.isEmpty()) {
			System.out.println("Không tìm thấy sản phẩm!");
		} else {
			System.out.printf("%-5s %-25s %-12s %-5s\n", 
					"ID", "Tên sản phẩm", "Giá", "Số lượng");
			for (Product p : products) {
				System.out.printf("%-5d %-25s %-12.2f %-5d\n", 
						p.getId(), p.getName(), p.getPrice(), p.getQuantity());
			}
		}
	}

	private static void updateProduct() {
		int id = getInputInt("Nhập ID sản phẩm cần cập nhật: ");
		Product existing = service.findProductById(id);
		if (existing == null) {
			System.out.println("Không tìm thấy sản phẩm!");
			return;
		}
		String name = getInputString("Tên sản phẩm mới: ");
		double price = getInputDouble("Giá mới: ");
		int quantity = getInputInt("Số lượng mới: ");

		existing.setName(name);
		existing.setPrice(price);
		existing.setQuantity(quantity);

		boolean updated = service.updateProduct(existing);
		if (updated) {
			System.out.println("➔ Cập nhật thành công!");
		} else {
			System.out.println("Cập nhật thất bại!");
		}
	}

	private static void deleteProduct() {
		int id = getInputInt("Nhập ID sản phẩm cần xóa: ");
		boolean deleted = service.deleteProduct(id);
		if (deleted) {
			System.out.println("➔ Xóa thành công!");
		} else {
			System.out.println("Xóa thất bại!");
		}
	}

	private static void listProductsByPage() {
		int page = getInputInt("Nhập số trang: ");
		int pageSize = getInputInt("Nhập số sản phẩm mỗi trang: ");
		List<Product> products = service.getProductsByPage(page, pageSize);
		
		if (products.isEmpty()) {
			System.out.println("Không có sản phẩm nào trong trang này!");
		} else {
			System.out.printf("%-5s %-25s %-12s %-5s\n", 
					"ID", "Tên sản phẩm", "Giá", "Số lượng");
			for (Product p : products) {
				System.out.printf("%-5d %-25s %-12.2f %-5d\n", 
						p.getId(), p.getName(), p.getPrice(), p.getQuantity());
			}
		}
	}

	private static void addMultipleProducts() {
		int n = getInputInt("Nhập số lượng sản phẩm muốn thêm: ");
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			System.out.println("== Sản phẩm thứ " + (i + 1) + " ==");
			String name = getInputString("Tên sản phẩm: ");
			double price = getInputDouble("Giá sản phẩm: ");
			int quantity = getInputInt("Số lượng: ");
			products.add(new Product(name, price, quantity));
		}
		boolean success = service.addMultipleProducts(products);
		if (success) {
			System.out.println("➔ Thêm nhiều sản phẩm thành công!");
		} else {
			System.out.println("Có lỗi xảy ra. Giao dịch đã rollback!");
		}
	}

	// Các hàm hỗ trợ nhập liệu
	private static int getInputInt(String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextInt()) {
			System.out.println("Sai định dạng! Vui lòng nhập số nguyên.");
			scanner.next();
			System.out.print(prompt);
		}
		return scanner.nextInt();
	}

	private static double getInputDouble(String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextDouble()) {
			System.out.println("Sai định dạng! Vui lòng nhập số thực.");
			scanner.next();
			System.out.print(prompt);
		}
		return scanner.nextDouble();
	}

	private static String getInputString(String prompt) {
		System.out.print(prompt);
		scanner.nextLine(); // clear buffer
		return scanner.nextLine();
	}
}
