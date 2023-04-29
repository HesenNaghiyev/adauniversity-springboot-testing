package az.adauniversity.demo;


import az.adauniversity.demo.entity.Product;
import az.adauniversity.demo.repository.ProductRepository;
import az.adauniversity.demo.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1, "Iphone", 1, 2000);
    }

    @Test
    @DisplayName("saveProduct_WhenCalled_ShouldReturnExpectedEntity")
    void saveProduct_WhenCalled_ShouldReturnExpectedEntity() {
        //Given


        //Mock Product Storage Repository
        when(productRepository.save(product)).thenReturn(product);


        //When
        Product actual = productService.saveProduct(product);


        //Then
        assertNotNull(actual);
        assertEquals(product, actual);
        verify(productRepository, times(1)).save(product);
    }
    //Uncle Bob Clean Code

    @Test
    @DisplayName("getProducts_WhenCalled_ShouldReturnExpectedList")
    void getProducts_WhenCalled_ShouldReturnExpectedList() {
        //Given
        List<Product> expectedProducts = List.of(
                product = new Product(1, "Iphone", 1, 2000),
                product = new Product(2, "Samsung", 1, 1999)
        );

        //Mock Product Storage Repository
        when(productRepository.findAll()).thenReturn(expectedProducts);

        //When
        List<Product> actualProducts = productService.getProducts();

        //Then
        assertNotNull(actualProducts);
        assertEquals(expectedProducts, actualProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_WhenCalled_ShouldReturnExpectedResult() {
        //Given

        //Mock storage
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        //When
        Product actualProduct = productService.getProductById(product.getId());

        //Then
        assertNotNull(actualProduct);
        assertEquals(product, actualProduct);
        verify(productRepository, times(1)).findById(product.getId());

    }
}

