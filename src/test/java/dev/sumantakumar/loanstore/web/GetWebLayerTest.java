package dev.sumantakumar.loanstore.web;

import dev.sumantakumar.loanstore.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class GetWebLayerTest extends BaseTest {

    /**
     * @implNote Is used to test after creation of post endpoint
     * @throws Exception
     */
    @Test
    public void getLoanRemainingByCustomer() throws Exception {
        mockMvc.perform(get("/remaining/customer"))
                .andExpect(status().isOk())
                .andDo(System.out::println);

    }
    /**
     * @implNote Is used to test before creation of get endpoint
     * @throws Exception
     */
    @Disabled
    @Test
    public void getLoanRemainingByCustomerNotFound() throws Exception {
        mockMvc.perform(get("/remaining/customer"))
                .andExpect(status().isNotFound());

    }

    /**
     * @implNote Is used to test after creation of post endpoint
     * @throws Exception
     */
    @Test
    public void getLoanRemainingByInterest() throws Exception {
        mockMvc.perform(get("/remaining/interest"))
                .andExpect(status().isOk()).andDo(System.out::println);

    }
    /**
     * @implNote Is used to test before creation of get endpoint
     * @throws Exception
     */
    @Disabled
    @Test
    public void getLoanRemainingByInterestNotFound() throws Exception {
        mockMvc.perform(get("/remaining/interest"))
                .andExpect(status().isNotFound());

    }

    /**
     * @implNote Is used to test after creation of post endpoint
     * @throws Exception
     */
    @Test
    public void getLoanRemainingByLenders() throws Exception {
        mockMvc.perform(get("/remaining/lenders"))
                .andExpect(status().isOk()).andDo(System.out::println);

    }
    /**
     * @implNote Is used to test before creation of get endpoint
     * @throws Exception
     */
    @Disabled
    @Test
    public void getLoanRemainingByLendersNotFound() throws Exception {
        mockMvc.perform(get("/remaining/lenders"))
                .andExpect(status().isNotFound());

    }



    /**
     * @implNote Is used to test after creation of post endpoint
     * @throws Exception
     */
    @Test
    public void getLoanLoans() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk()).andDo(System.out::println);

    }
    /**
     * @implNote Is used to test before creation of get endpoint
     * @throws Exception
     */
    @Disabled
    @Test
    public void getLoanLoansNotFound() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isNotFound());

    }


}
