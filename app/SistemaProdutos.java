package app;

import algorithm.AVLTree;
import algorithm.Tree;
import algorithm.MergeSort;
import algorithm.SortingAlgorithm;
import exception.BusinessException;
import model.InfoProd;
import model.Product;
import model.FoodProduct;
import model.HandmadeProduct;
import repository.HashRepository;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Entry point for the Natural Products Management System.
 * Demonstrates all functionalities and integration of components.
 */
public class ProductSystem {

    // Hash repository for products indexed by ID (HashMap)
    private final HashRepository<Product> productRepository = new HashRepository<>(Product::getId);

    // AVL Tree indexed by Name (for fast search and inorder sorting)
    private final Tree<String, Product> nameIndex = new AVLTree<>();

    // Sorting algorithm (MergeSort)
    private final SortingAlgorithm<Product> sortingAlgorithm = new MergeSort<>();

    public static void main(String[] args) {
        ProductSystem system = new ProductSystem();

        // 1. Demonstrate annotations and business rules
        system.demonstrateAnnotations();
        system.demonstrateBusinessRules();

        // 2. Register Valid Products
        system.registerTestProducts();

        // 3. Demonstrate CRUD (Hash)
        system.demonstrateCrud();

        // 4. Demonstrate AVL Tree (Search and Inorder Listing)
        system.demonstrateAvlTree();

        // 5. Demonstrate and Profile MergeSort
        system.demonstrateSortingAndProfiling();
    }

    /**
     * Registers a product in both the Hash repository and AVL tree.
     */
    private void registerProduct(Product product) {
        productRepository.register(product);
        nameIndex.insert(product.getName(), product);
    }

    private void registerTestProducts() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 2. REGISTRATION OF VALID PRODUCTS (Hash and AVL) ");
        System.out.println("##########################################################");
        try {
            // Sample products for demonstration
            Product p1 = new FoodProduct("A001", "Organic Honey", 25.50, "Food", 50, "Pure Farm", "2026-01-01");
            Product p2 = new HandmadeProduct("B002", "Natural Lavender Soap", 12.00, "Hygiene", 120, "Earth Atelier", "Essential Oils");
            Product p3 = new FoodProduct("C003", "Gourmet Ground Coffee", 45.90, "Food", 80, "Grandpa's Ranch", "2025-12-31");
            Product p4 = new FoodProduct("A004", "Strawberry Jam", 18.75, "Food", 60, "Pure Farm", "2026-03-01");
            Product p5 = new HandmadeProduct("B005", "Ceramic Vase", 60.00, "Decoration", 15, "Art Clay", "Clay");

            registerProduct(p1);
            registerProduct(p2);
            registerProduct(p3);
            registerProduct(p4);
            registerProduct(p5);

            System.out.println("\n5 Products successfully registered in the Repository (Hash) and Index (AVL).");

        } catch (BusinessException e) {
            System.err.println("Unrecoverable error registering demo products: " + e.getMessage());
        }
    }

    private void demonstrateBusinessRules() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 1. BUSINESS RULE DEMONSTRATION (BusinessException) ");
        System.out.println("##########################################################");

        // Test for invalid Price (Price must be > 0)
        try {
            new FoodProduct("X001", "Test Price", -5.00, "Test", 10, "Local", "2025-01-01");
        } catch (BusinessException e) {
            System.err.println("EXPECTED ERROR (Negative Price): " + e.getMessage());
        }

        // Test for invalid Stock (Stock must be >= 0)
        try {
            new FoodProduct("X002", "Test Stock", 10.00, "Test", -1, "Local", "2025-01-01");
        } catch (BusinessException e) {
            System.err.println("EXPECTED ERROR (Negative Stock): " + e.getMessage());
        }
    }

    private void demonstrateAnnotations() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 1. READING @InfoProd ANNOTATION (Reflection) ");
        System.out.println("##########################################################");

        // Read annotation on Product class
        InfoProd productInfo = Product.class.getAnnotation(InfoProd.class);
        if (productInfo != null) {
            System.out.println("Annotation on Product class:");
            System.out.println("  Version: " + productInfo.version());
            System.out.println("  Default Category: " + productInfo.category());
        }

        // Read annotation on HashRepository class
        InfoProd repoInfo = HashRepository.class.getAnnotation(InfoProd.class);
        if (repoInfo != null) {
            System.out.println("\nAnnotation on HashRepository class:");
            System.out.println("  Version: " + repoInfo.version());
            System.out.println("  Default Category: " + repoInfo.category());
        }

        // Example of searching an existing method (listAll) via Reflection to demonstrate Type Introspection
        try {
            Method listAllMethod = HashRepository.class.getMethod("listAll");
            System.out.println("\nMethod 'listAll' found successfully in HashRepository via Reflection.");
        } catch (NoSuchMethodException e) {
            System.err.println("Error: Expected method 'listAll' was not found in HashRepository.");
        }
    }

    private void demonstrateCrud() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 3. CRUD DEMONSTRATION (HASH REPOSITORY) ");
        System.out.println("##########################################################");

        // 3a. Search
        Product foundProduct = productRepository.search("C003");
        System.out.println("\n[SEARCH] ID C003: " + (foundProduct != null ? foundProduct.getName() : "Not found"));

        // 3b. List All
        System.out.println("\n[LIST ALL (Unordered - HashMap)]: ");
        productRepository.listAll().forEach(System.out::println);

        // 3c. Remove
        productRepository.remove("A004");
        System.out.println("\n[REMOVE] Product with ID A004 removed.");

        // 3d. List All After Removal
        System.out.println("\n[LIST AFTER REMOVAL]:");
        productRepository.listAll().forEach(System.out::println);
    }

    private void demonstrateAvlTree() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 4. AVL TREE DEMONSTRATION (Name Indexing) ");
        System.out.println("##########################################################");

        // 4a. Search in Tree
        Product foundInTree = nameIndex.search("Organic Honey");
        System.out.println("\n[AVL SEARCH] Name 'Organic Honey': " + (foundInTree != null ? foundInTree.getId() : "Not found"));

        // 4b. Inorder Listing (In-Order Traversal; returns items ordered by key (Name))
        System.out.println("\n[INORDER PRINT (Sorted by Name - AVL)]: ");
        List<Product> orderedList = nameIndex.inOrderList();
        orderedList.forEach(p -> System.out.println(" - " + p.getName() + " (ID: " + p.getId() + ")"));
    }

    private void demonstrateSortingAndProfiling() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 5. SORTING AND PROFILING DEMONSTRATION (MergeSort) ");
        System.out.println("##########################################################");

        // Get current list from repository (items remaining after removal)
        List<Product> productList = productRepository.listAll();

        // --- 5a. Sort by Name (Comparator) ---
        Comparator<Product> byName = Comparator.comparing(Product::getName);
        long sortTimeByName = sortingAlgorithm.sort(productList, byName);

        System.out.println("\n[SORTED BY NAME (MergeSort)]: ");
        productList.forEach(p -> System.out.println(" - " + p.getName()));

        // Log in milliseconds
        long sortTimeByNameMs = TimeUnit.NANOSECONDS.toMillis(sortTimeByName);
        System.out.println("\n[LOG PROFILING] Sort Time by Name: " + sortTimeByNameMs + " ms.");

        // --- 5b. Sort by Price (Comparator) ---
        productList = productRepository.listAll(); // Reload unordered list
        Comparator<Product> byPrice = Comparator.comparingDouble(Product::getPrice);
        long sortTimeByPrice = sortingAlgorithm.sort(productList, byPrice);

        System.out.println("\n[SORTED BY PRICE (MergeSort)]: ");
        productList.forEach(p -> System.out.println(" - R$" + String.format("%.2f", p.getPrice()) + " - " + p.getName()));

        long sortTimeByPriceMs = TimeUnit.NANOSECONDS.toMillis(sortTimeByPrice);
        System.out.println("\n[LOG PROFILING] Sort Time by Price: " + sortTimeByPriceMs + " ms.");

        // --- 5c. Sort by Category (Comparator) ---
        productList = productRepository.listAll(); // Reload unordered list
        Comparator<Product> byCategory = Comparator.comparing(Product::getCategory);
        long sortTimeByCategory = sortingAlgorithm.sort(productList, byCategory);

        System.out.println("\n[SORTED BY CATEGORY (MergeSort)]: ");
        productList.forEach(p -> System.out.println(" - Category: " + p.getCategory() + " - " + p.getName()));

        long sortTimeByCategoryMs = TimeUnit.NANOSECONDS.toMillis(sortTimeByCategory);
        System.out.println("\n[LOG PROFILING] Sort Time by Category: " + sortTimeByCategoryMs + " ms.");

        // --- 5d. Comparison with In-Order (Algorithm Integration) ---
        System.out.println("\n[ORDER COMPARISON - ALPHABETICAL ORDER]:");
        System.out.println("1. External ordering (MergeSort) by Name took " + sortTimeByNameMs + " ms.");
        System.out.println("2. Internal ordering of the AVL (In-Order Listing by Name) is inherently quick (O(n)), as order is maintained during insertions.");
    }
}
