package dev.sumantakumar.loanstore.web;

import com.google.gson.Gson;
import dev.sumantakumar.loanstore.BaseTest;
import dev.sumantakumar.loanstore.dto.LoanDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PostWebLayer extends BaseTest {
    private static LoanDto loanDto;

    @BeforeAll
    public static void init(){
        loanDto = new LoanDto();
        loanDto.setCustomerId("C1");
        loanDto.setLenderId("LEN1");
        loanDto.setAmount(10000);
        loanDto.setRemainingAmount(1000);
        loanDto.setPaymentDate(String.valueOf(LocalDate.now()));
        loanDto.setInterestPerDay(1);
        loanDto.setDueDate(String.valueOf(LocalDate.now().plusMonths(1)));
        loanDto.setPenaltyPerDay(0.01);
    }

    /**
     * @implNote Is used to test after creation of post endpoint
     * Save Record
     * @throws Exception
     */
    @Test
    public void postLoansSave() throws Exception {
        String requestString = new Gson().toJson(loanDto);
        System.out.println(requestString);
        mockMvc.perform(post("/loan")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(requestString)).andExpect(status().isOk());



    }

    /**
     * @implNote Is used to test after creation of post endpoint
     * Invalid Customer Id Given
     * @throws Exception
     */
    @Test
    public void postLoansFoundAndInsert() throws Exception {
        loanDto.setCustomerId("CUSTOMER1");
        mockMvc.perform(post("/loan")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(loanDto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * @implNote Is used to test after creation of post endpoint
     * Invalid Data Given
     * @throws Exception
     */
    @Test
    public void postLoansFoundException() throws Exception {
        loanDto.setPaymentDate(String.valueOf(LocalDate.now().plusMonths(2)));
        mockMvc.perform(post("/loan")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(loanDto))).andExpect(status().isBadRequest());
    }

    /**
     * @implNote Is used to test after creation of post endpoint
     * Initial
     * @throws Exception
     */
    @Test
    public void postLoansFound() throws Exception {
        mockMvc.perform(post("/loan")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(loanDto))).andExpect(status().isCreated());



    }
    /**
     * @implNote Is used to test before creation of post endpoint
     * @throws Exception
     */
    @Disabled
    @Test
    public void postLoansNotFound() throws Exception {
        System.out.println(loanDto.toString());
        mockMvc.perform(post("/loan")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(loanDto)))
        .andExpect(status().isNotFound());

    }
}
