package ua.com.foxminded.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import ua.com.foxminded.domain.Vote;
import ua.com.foxminded.repository.VoteRepository;
import ua.com.foxminded.v1.controller.ComputeResultController;

@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ComputeResultControllerTest {

    @InjectMocks
    ComputeResultController computeResultController;
    
    @Mock
    private VoteRepository voteRepository;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(computeResultController).build();
    }
    
    @Test
    public void shouldComputeResults() throws Exception {
        when(voteRepository.findByPoll(1L)).thenReturn(new ArrayList<Vote>());
        mockMvc.perform(get("/v1/computeresults?pollId=1"))
                .andExpect(status().isOk());
    }
}
