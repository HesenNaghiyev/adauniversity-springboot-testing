package az.adauniversity.demo;


import az.adauniversity.demo.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductAppIntegrationTest {


    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static TestRestTemplate testRestTemplate;


    @Autowired
    private TestH2Repository testH2Repository;

    @BeforeAll
    public static void init() {
        testRestTemplate = new TestRestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/products");
    }

    @AfterEach
    public void tearDown() {
        testH2Repository.deleteAll();
    }

    @Test
    public void addProduct() {
        Product expectedProduct = new Product(1, "Iphone", 1, 2000);
        Product actualProduct = testRestTemplate.postForObject(baseUrl, expectedProduct, Product.class);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    @Sql(statements = "INSERT INTO PRODUCT_TBL(id,name, quantity, price) VALUES (1, 'Iphone',1,1000)",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getTestProduct() {
        List<Product> actualProducts = testRestTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, actualProducts.size());
        assertEquals(1, testH2Repository.findAll().size());
    }
}

