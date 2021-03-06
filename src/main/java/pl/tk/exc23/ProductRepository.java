package pl.tk.exc23;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product("Jabłko", 3.5, Category.GROCERIES));
        products.add(new Product("Masło", 4.5, Category.GROCERIES));
        products.add(new Product("Chleb", 4, Category.GROCERIES));
        products.add(new Product("Jogurt", 2.5, Category.GROCERIES));
        products.add(new Product("Worki na śmieci", 5.5, Category.HOUSEHOLD));
        products.add(new Product("Mop", 29, Category.HOUSEHOLD));
        products.add(new Product("Płyn do naczyń", 7.5, Category.HOUSEHOLD));
        products.add(new Product("Odkurzacz", 399, Category.HOUSEHOLD));
        products.add(new Product("Zestaw do szycia", 14.5, Category.OTHER));
        products.add(new Product("Olej silnikowy", 149, Category.OTHER));
        products.add(new Product("Piła spalinowa", 3699, Category.OTHER));
        products.add(new Product("Guzik", 0.5, Category.OTHER));
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProductsFromCategory(Category category) {
        if (category == null) {
            return getProducts();
        } else {
            return products.stream()
                    .filter(product -> product.getCategory().equals(category))
                    .collect(Collectors.toList());
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double sumPrices(List<Product> productsList) {
        double sum = 0;
        for (Product product : productsList) {
            sum += product.getPrice();
        }
        return sum;
    }
}