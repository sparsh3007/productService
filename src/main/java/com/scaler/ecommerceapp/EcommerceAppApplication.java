package com.scaler.ecommerceapp;



import com.scaler.ecommerceapp.InheritanceExample.joined.MentorRespository;
import com.scaler.ecommerceapp.InheritanceExample.joined.StudentRepository;
import com.scaler.ecommerceapp.Repositories.CategoryRepository;
import com.scaler.ecommerceapp.Repositories.PriceRespository;
import com.scaler.ecommerceapp.Repositories.ProductRepository;
import com.scaler.ecommerceapp.models.Category;
import com.scaler.ecommerceapp.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class EcommerceAppApplication implements CommandLineRunner {
    private MentorRespository mentorRepository;
    private StudentRepository studentRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private PriceRespository priceRespository;
    public EcommerceAppApplication(MentorRespository mentorRepository,
                                   StudentRepository studentRepository,
                                   CategoryRepository categoryRepository,
                                   ProductRepository productRepository,
                                   PriceRespository priceRespository) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRespository = priceRespository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceAppApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setAvgRating(4.5f);
//        mentor.setName("Gurpreet Singh");
//        mentor.setEmail("gs@gmail.com");
//        mentorRepository.save(mentor);
//
//        Student student = new Student();
//        student.setBatch("Sept2022");
//        student.setPsp(95);
//        student.setName("Rahul");
//        student.setEmail("rahul@gmail.com");
//        studentRepository.save(student);
//
//        User user = new User();
//        user.setName("Sparsh");
//        user.setEmail("sparsh@gmail.com");
//        userRepository.save(user);

//        List<User> users = userRepository.findAll();
//        users.forEach(user2 -> System.out.println(user2.getName()));
        /*
        Mentor mentor = new Mentor();
        mentor.setAvgRating(4.9f);
        mentor.setName("Saket Ghosh");
        mentor.setEmail("sakets@gmail.com");
        mentorRepository.save(mentor);

        Student student = new Student();
        student.setName("Ankit");
        student.setEmail("ankit1@gmail.com");
        student.setBatch("Oct2022");
        student.setPsp(90);
        studentRepository.save(student);
         */
    /*
        // Create a category and a product
        Price price = new Price(100000, "INR");
//        Price savedPrice = priceRespository.save(price);


        Category category = new Category();
        category.setName("Electronics");
        Category savedCategory =categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("iPhone 15");
        product.setDescription("Apple iPhone 15");
        product.setPrice(price);
        product.setCategory(savedCategory);
        product.setImage("https://www.apple.com/newsroom/images/product/iphone/standard/Apple_new-iphone15-pro-family_09142021_big.jpg.large.jpg");
        productRepository.save(product);
        */

        /*
        Product product1 = productRepository.findByTitleAndPrice_Currency("iPhone 15", "INR");
        System.out.println(product1);
    */
        /*

        List<Product> products = productRepository.findByTitle("iPhone 13");
        System.out.println(products);

        Product product3 = productRepository.findByTitle2("iPhone 15");
        System.out.println(product3);
    */
        // Fetch the category and print the products
//        productRepository.deleteById(UUID.fromString("323a1948-49fc-4716-8b70-f3b24a187139"));

//        // Fetch the category and print the products
//        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString("55ad9a5a-575b-4789-bd02-6ebb9c97a100"));
//        if(categoryOptional.isPresent()){
//            Category category1 = categoryOp   tional.get();
//            List<Product> products = category1.getProducts();
//            products.forEach(product1 -> System.out.println(product1.getTitle()));
//            System.out.println("DEBUG");
//        }
//        else{
//            System.out.println("Category not found");
//        }

//        Optional<Category> categoryOptional = categoryRepository
//                .findById(UUID.fromString("848aee07-c9ca-4719-9d2e-6921154cbf4f"));
//
//        Category category=categoryOptional.get();
//        System.out.println(category);
//
//        List<Product> products = category.getProducts();
//        System.out.println(products);
    }
}
