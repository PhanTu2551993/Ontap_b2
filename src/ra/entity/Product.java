package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private Float price ;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, Float price, String description, Date created, int catalogId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
    public void inputData(Scanner scanner, Product[] arrProduct,  Categories[] arrCategories, int lengthCategory){
        System.out.println("Nhập mã sản phẩm (4 ký tự, bắt đầu bằng 'C', 'S' hoặc 'A'): ");
        while (true){
            productId = scanner.nextLine();
            boolean isDuplicateProductId = false;
            for (Product product : arrProduct) {
                if (product != null && product.getProductId().equals(productId)) {
                    isDuplicateProductId = true;
                    break;
                }
            }
            if (productId.matches("^[CSA].{3}$") && !isDuplicateProductId) {
                break;
            }
            System.out.println("Mã sản phẩm không hợp lệ. Hãy nhập lại.");
        }

        System.out.println("Nhập tên sản phẩm (từ 10-50 ký tự): ");
        while (true) {
            productName = scanner.nextLine();
            boolean isDuplicateProductName = false;
            for (Product product : arrProduct) {
                if (product != null && product.getProductName().equals(productName)) {
                    isDuplicateProductName = true;
                    break;
                }
            }
            if (productName.length() >= 10 && productName.length() <= 50 && !isDuplicateProductName) {
                break;
            }
            System.out.println("Tên sản phẩm không hợp lệ. Hãy nhập lại.");
        }

        System.out.println("Nhập giá sản phẩm (lớn hơn 0): ");
        while (true) {
            price = Float.parseFloat(scanner.nextLine());
            if (price > 0) {
                break;
            }
            System.out.println("Giá sản phẩm không hợp lệ. Hãy nhập lại.");
        }

        System.out.println("Nhập mô tả sản phẩm: ");
        description = scanner.nextLine();

        System.out.println("Nhập ngày nhập sản phẩm (định dạng dd/MM/yyyy): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date createdDate = null;
        do {
            try {
                createdDate = sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Ngày nhập không hợp lệ. Hãy nhập lại.");
            }
        } while (createdDate == null);
        created = createdDate;

        System.out.println("Danh sách danh mục: ");
        for (int i = 0; i < lengthCategory; i++) {
            arrCategories[i].displayData();
        }

        System.out.println("Nhập mã danh mục sản phẩm: ");
        int cateId;
        while (true) {
            cateId = Integer.parseInt(scanner.nextLine());
            boolean exists = false;
            for (Categories categories : arrCategories) {
                if (categories != null && categories.getCategoryId() == cateId) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                break;
            } else {
                System.out.println("Mã danh mục không tồn tại. Hãy nhập lại.");
            }
        }
        this.catalogId = cateId;




        System.out.println("Nhập trạng thái sản phẩm (0: Đang bán – 1: Hết hàng – 2: Không bán): ");
        productStatus = Integer.parseInt(scanner.nextLine());
    }

    private boolean isDuplicate(Product[] arrProduct, String input) {
        for (Product product : arrProduct) {
            if (product != null && (product.getProductId().equals(input) || product.getProductName().equals(input))) {
                return true;
            }
        }
        return false;
    }

    public void display() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá sản phẩm: " + price);
        System.out.println("Mô tả sản phẩm: " + description);
        System.out.println("Ngày nhập sản phẩm: " + sdf.format(created));
        System.out.println("Mã danh mục sản phẩm: " + catalogId);
        String status = "";
        switch (productStatus) {
            case 0:
                status = "Đang bán";
                break;
            case 1:
                status = "Hết hàng";
                break;
            case 2:
                status = "Không bán";
                break;
        }
        System.out.println("Trạng thái sản phẩm: " + status);
        System.out.println("-----------------------------------");
    }

}
