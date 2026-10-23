
package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService() {
        products.add(new DigitalProduct(
                nextId++, "Clean Code (E-book)", 29.99,
                "/downloads/clean-code.pdf", 4.2
        ));

        products.add(new PhysicalProduct(
                nextId++, "27-inch Monitor", 249.99,
                5.4, "61cm x 41cm x 18cm"
        ));
    }

    // 1. Get all products
    public List<Product> getAllProducts() {
        return products;
    }

    // 2. Add a product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    // 3. Find a product by ID
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // 4. Delete a product by ID
    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    // 5. Search products by name
    public List<Product> findByName(String name) {
        return products.stream()
                .filter(p -> p.getName() != null
                        && p.getName().contains(name))
                .toList();
    }
}
