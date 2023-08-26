package dev.sumantakumar.loanstore.web;

import dev.sumantakumar.loanstore.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebLayerTest extends BaseTest {


    @Test
    public void getLoansWithData() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andDo(System.out::println);
    }

    @Test
    public void getLoanLoans() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk());

    }

    @Disabled
    @Test
    public void getLoanLoansNotFound() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isNotFound());

    }
}
