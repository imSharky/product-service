package dev.naman.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.naman.productservice.repositories.jpa.CategoryRepository;
import dev.naman.productservice.repositories.jpa.ProductRepository;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {
//	private MentorRepository mentorRepository;
//	private UserRepository userRepository; //inheritancedemo
	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;

	public ProductserviceApplication(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	//	public ProductserviceApplication(@Qualifier("tpc_ur") UserRepository userRepository, @Qualifier("tpc_mr") MentorRepository mentorRepository) {
//		this.userRepository = userRepository;
//		this.mentorRepository = mentorRepository;
//	} //inheritancedemo

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Mentor mentor = new Mentor(); //this is for inheritancedemo
//		mentor.setName("naman");
//		mentor.setEmail("naman@scaler.com");
//		mentor.setAverageRating(4.99);
//		mentorRepository.save(mentor);
//
//		User user = new User();
//		user.setName("sharik");
//		user.setEmail("sharik@scaler.com");
//		userRepository.save(user);

//		Category category = new Category();
//		category.setName("Apple Devices");
//		Category savedCategory = categoryRepository.save(category);
//
//		Product product = new Product();
//		product.setTitle("iphone 15 pro");
//		product.setDescription("the best iphone ever");
//
//		product.setCategory(savedCategory);
//
//		productRepository.save(product);
//
//		UUID id = UUID.fromString("b579cdef-cb36-40ee-b23b-b33a2a8e5137");
//		Category category1 = categoryRepository.findById(id).get();
//		System.out.println("Category name is "+category1.getName());
//		System.out.println("printing all the product");
//		Thread.sleep(1000);
//
//		for(Product product1: category1.getProducts()){
//			System.out.println(product1.getTitle());
//		}
	}
}