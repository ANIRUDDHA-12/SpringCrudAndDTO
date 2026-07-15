package com.firstProjectDemo.first_api;


import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionHistoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void summaryProjectionEndpoint() throws Exception{
        mockMvc.perform(get("v1/api/accounts/1/transactions/summary")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(18))
                .andExpect(jsonPath("$[0].transactionId").exists())
                .andExpect(jsonPath("$[0].counterpartyName").exists())
                .andExpect(jsonPath("$[0].fromAccount").doesNotExist());
    }
}
