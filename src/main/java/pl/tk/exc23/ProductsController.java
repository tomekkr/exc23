package pl.tk.exc23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("categories", Category.values());
        model.addAttribute("newProduct", new Product());
        return "home";
    }

    @GetMapping("/lista")
    public String showProducts(@RequestParam(name = "kategoria") Category category, Model model) {
        List<Product> filteredProducts = productRepository.getProductsFromCategory(category);
        double pricesSum = productRepository.sumPrices(filteredProducts);
        model.addAttribute("productsList", filteredProducts);
        model.addAttribute("sum", pricesSum);
        return "product-list";
    }

    @PostMapping("/add")
    public String addProduct(Product newProduct) {
        productRepository.addProduct(newProduct);
        return "redirect:/";
    }
}