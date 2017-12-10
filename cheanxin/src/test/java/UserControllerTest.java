import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import cheanxin.config.DatabaseConfig;
import cheanxin.config.RootConfig;
import cheanxin.config.WebConfig;
import cheanxin.web.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Jawinton on 16/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, DatabaseConfig.class})
@ActiveProfiles(value = "dev")
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void testRegister() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController)
                .build();
        ResultActions result = mockMvc.perform(post("/users")
                .param("realName", "Jack Tang")
                .param("username", "jack")
                .param("password", "24hours")
                .param("mobileNo", "13345678902"));
        System.out.println(result.andReturn().getResponse().getContentAsString());;
    }

    @Test
    public void testProfile() throws Exception {
        MockMvc mockMvc = standaloneSetup(userController)
                .build();
        ResultActions result = mockMvc.perform(get("/users/jack"));
        System.out.println(result.andReturn().getResponse().getContentAsString());
    }
}
