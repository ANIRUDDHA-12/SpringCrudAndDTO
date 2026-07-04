package com.firstProjectDemo.first_api;


import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @Test
    public void addProduct_ValidInput_ReturnsCreated() throws Exception{
        ProductRequestDTO productRequestDTO= new ProductRequestDTO("Laptop",789.67);
        String result =  objectMapper.writeValueAsString(productRequestDTO);

        when(productService.createProduct(productRequestDTO)).thenReturn("New Product saved");

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
                .andExpect(status().isCreated())
                .andExpect(content().string("Product laptop saved"));
    }

    @Test
    public void addProduct_InvalidInput_ReturnsBadRequest(){
        ProductRequestDTO invalidDto = new ProductRequestDTO("", -50.0);
        String jsonString = objectMapper.writeValueAsString(invalidDto);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isBadRequest());
    }

}
