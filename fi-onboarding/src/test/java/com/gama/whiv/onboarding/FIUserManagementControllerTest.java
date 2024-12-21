package com.gama.whiv.onboarding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gama.whiv.onboarding.user.FIUser;
import com.gama.whiv.onboarding.user.FIUserManagementController;
import com.gama.whiv.onboarding.user.FIUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FIUserManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FIUserService fiUserService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addUser_ShouldReturnSuccess() throws Exception {
        FIUserManagementController.AddUserRequest request = new FIUserManagementController.AddUserRequest("testuser", "USER", "test@example.com");
        mockMvc.perform(post("/api/user-management/add-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully!"));
    }

    @Test
    public void updateUser_ShouldReturnSuccess() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest("1", "ADMIN", "admin");
        when(fiUserService.getUserById(1L)).thenReturn(new FIUser());
        mockMvc.perform(put("/api/user-management/update-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("User updated successfully!"));
    }

    @Test
    public void removeUser_ShouldReturnSuccess() throws Exception {
        doNothing().when(fiUserService).deleteUser(any(Long.class));
        mockMvc.perform(delete("/api/user-management/remove-user/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("User removed successfully!"));
    }

    @Test
    public void listUsers_ShouldReturnUserList() throws Exception {
        when(fiUserService.getAllUsers(PageRequest.of(0, 5))).thenReturn(List.of(new FIUser()));
        mockMvc.perform(get("/api/user-management/list-users"))
                .andExpect(status().isOk());
    }
}
