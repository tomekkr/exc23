package pl.tk.exc23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {

    private ProductRepository productRepository;

    @Autowired
    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<String> categories = getCategoriesList();
        model.addAttribute("productCategories", categories);
        model.addAttribute("newProduct", new Product());
        return "home";
    }

    @GetMapping("/lista")
    public String showProducts(@RequestParam(name = "kategoria") String category, Model model) {
        List<Product> filteredProducts = getProductsList(category);
        double pricesSum = productRepository.sumPrices(category);
        model.addAttribute("productsList", filteredProducts);
        model.addAttribute("sum", pricesSum);
        return "product-list";
    }

    private List<Product> getProductsList(String category) {
        List<Product> products = productRepository.getProducts();
        if (category.isEmpty()) {
            return productRepository.getProducts();
        } else {
            return productRepository.getProductsFromCategory(category, products);
        }
    }

    private List<String> getCategoriesList() {
        Category[] categoriesArray = Category.values();
        List<String> categories = new ArrayList<>();
        for (Category category : categoriesArray) {
            categories.add(category.getDescription());
        }
        return categories;
    }
}