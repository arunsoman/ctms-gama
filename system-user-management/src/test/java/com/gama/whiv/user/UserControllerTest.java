    package com.gama.whiv.user;


    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.gama.whiv.user.entity.*;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;

    import java.util.Arrays;
    import java.util.HashSet;

    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.doNothing;
    import static org.mockito.Mockito.when;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class UserControllerTest {


        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void createUser_ShouldReturnSuccess() throws Exception {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
    //        user.setEmail("test@example.com");

            doNothing().when(userService).createUser(any(User.class));

                mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("User created successfully."));
        }

        @Test
        public void updateUser_ShouldReturnSuccess() throws Exception {
            User user = new User();
            user.setUsername("updateduser");
    //        user.setEmail("updated@example.com");

            doNothing().when(userService).updateUser(any(Long.class), any(User.class));

            mockMvc.perform(put("/users/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("User updated successfully."));
        }


        @Test
        public void createRole_ShouldReturnSuccess() throws Exception {
            Role role = new Role();
            role.setName("ROLE_TEST");

            Permission permission1 = new Permission();
            permission1.setName("READ");
            permission1.setDescription("Read Permission");
            permission1.setPermissionGroup("Default");

            Permission permission2 = new Permission();
            permission2.setName("WRITE");
            permission2.setDescription("Write Permission");
            permission2.setPermissionGroup("Default");

            role.setPermissions(new HashSet<>(Arrays.asList(permission1, permission2)));

            doNothing().when(userService).createRole(any(Role.class));

            mockMvc.perform(post("/roles")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(role)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Role created successfully."));
        }

        @Test
        public void updateRole_ShouldReturnSuccess() throws Exception {
            Role role = new Role();
            role.setName("UPDATED_ROLE");

            Permission permission1 = new Permission();
            permission1.setName("READ");
            permission1.setDescription("Read Permission");
            permission1.setPermissionGroup("Default");

            Permission permission2 = new Permission();
            permission2.setName("WRITE");
            permission2.setDescription("Write Permission");
            permission2.setPermissionGroup("Default");

            role.setPermissions(new HashSet<>(Arrays.asList(permission1, permission2)));

            doNothing().when(userService).updateRole(any(Long.class), any(Role.class));

            mockMvc.perform(put("/roles/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(role)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Role updated successfully."));
        }
        @Test
        public void getAllRoles_ShouldReturnRolesList() throws Exception {
            Role role1 = new Role();
            role1.setName("ROLE_1");
            Role role2 = new Role();
            role2.setName("ROLE_2");

            when(userService.getAllRoles()).thenReturn(Arrays.asList(role1, role2));

            mockMvc.perform(get("/roles"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].name").value("ROLE_1"))
                    .andExpect(jsonPath("$[1].name").value("ROLE_2"));
        }

        public static void main(String[] args) {
            SpringApplication.run(UserControllerTest.class, args);
        }
    }