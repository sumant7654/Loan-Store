package dev.sumantakumar.loanstore;

import dev.sumantakumar.loanstore.repository.LoanRepository;
import dev.sumantakumar.loanstore.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @implNote This class is used to create a mockMvc Object.
 * We are going to use this in other test classes.
 * This is an abstract class, so we can't make any object for this class.
 * @author Sumanta Kumar Sahoo
 *
 */

@SpringBootTest
public abstract class BaseTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    /*@MockBean
    LoanRepository loanRepository;*/

   /* @MockBean
    LoanService loanService;*/


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


}
