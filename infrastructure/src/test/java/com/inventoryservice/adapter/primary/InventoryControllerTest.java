package com.inventoryservice.adapter.primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.primary.ManageInventoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class InventoryControllerTest {

    MockMvc mockMvc;
    @Autowired
    @InjectMocks
    private InventoryController inventoryController;
    @Mock
    private ManageInventoryUseCase manageInventoryUseCase;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
        objectMapper = new ObjectMapper();

        List<Product> products = Arrays.asList(new Product("1", "PC", 10, 2, 2000),
                new Product("2", "Monitor", 5, 3, 1500));
        when(manageInventoryUseCase.getAllProducts()).thenReturn(products);
    }

    @Test
    void shouldAddProduct() throws Exception {
        Product product = new Product("3", "Teclado", 10, 2, 100);

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/inventories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

    }

    @Test
    void shouldReturnEmptyListWhenGetLowStockProducts() throws Exception {
        mockMvc.perform(get("/inventories/low-stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    void shouldReturnAllProductsWhenGetAllProducts() throws Exception {
        mockMvc.perform(get("/inventories/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("PC")));
    }

    @Test
    void shouldReturnProductByIdWhenGetProductById() throws Exception {
        mockMvc.perform(get("/inventories/{productId}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("PC")));
    }

    @Test
    void shouldDeleteProductByIdWhenDeleteProduct() throws Exception {
        mockMvc.perform(delete("/inventories/remove/{productId}", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateProductByIdWhenUpdateProduct() throws Exception {
        Product product = new Product("1", null, 0, -1, 2000);
        String productJson = objectMapper.writeValueAsString(product);
        mockMvc.perform(put("/inventories/update/{productId}?stock=1", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());
    }
}