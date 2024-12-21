package com.gama.whiv.onboarding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gama.whiv.onboarding.api.APIKeyService;
import com.gama.whiv.onboarding.api.usage.APIUsageService;
import com.gama.whiv.onboarding.fi.FIManagementController;
import com.gama.whiv.onboarding.fi.FinancialInstitutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FIManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinancialInstitutionService financialInstitutionService;

    @MockBean
    private APIKeyService apiKeyService;

    @MockBean
    private APIUsageService apiUsageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void bulkCreateAndVerifyEntities_ShouldReturnSuccess() throws Exception {
     var  f11=    new FIManagementController.OnboardFIRequest(
                "FI1", "Description1", "123", "License1", "Contact1", "Designation1",
                "email1@example.com", "1111111111", "Secondary1", "http://callback1.url",
                List.of("192.168.1.1"), "API_ACCESS", "PublicKey1", "Daily",
                List.of("JSON"), "2024-12-15", "Region1", "AML Officer1", "KYC Policy1",
                "Compliance Cert1", "ACTIVE", "Remarks1", "Business Type1",
                "Business Category1", "Business Sub Category1", "Business Description1",
                "http://website1.com", "Social Media1", "support1@example.com", "1111111111"
        );
     var f12 =
             new FIManagementController.OnboardFIRequest(
                     "FI2", "Description2", "456", "License2", "Contact2", "Designation2",
                     "email2@example.com", "2222222222", "Secondary2", "http://callback2.url",
                     List.of("192.168.1.2"), "API_ACCESS", "PublicKey2", "Weekly",
                     List.of("XML"), "2024-12-16", "Region2", "AML Officer2", "KYC Policy2",
                     "Compliance Cert2", "ACTIVE", "Remarks2", "Business Type2",
                     "Business Category2", "Business Sub Category2", "Business Description2",
                     "http://website2.com", "Social Media2", "support2@example.com", "2222222222"
             );
        List<FIManagementController.OnboardFIRequest> bulkRequest = List.of(
              f11  , f12
        );

//        doNothing().when(financialInstitutionService).onboardFI(any());

        for (FIManagementController.OnboardFIRequest request : bulkRequest) {
            mockMvc.perform(post("/api/fi-management/onboard")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Financial Institution onboarded successfully!"));
        }


        mockMvc.perform(get("/api/fi-management/list-fi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void deleteAndVerifyEntity_ShouldReturnSuccess() throws Exception {
        long fiIdToDelete = 1L;
        doNothing().when(financialInstitutionService).deleteFinancialInstitution(fiIdToDelete);
        when(financialInstitutionService.getInstitutionById(fiIdToDelete)).thenReturn(null);

        mockMvc.perform(delete("/api/fi-management/delete/{fiId}", fiIdToDelete))
                .andExpect(status().isOk())
                .andExpect(content().string("Financial Institution deleted successfully!"));

        mockMvc.perform(get("/api/fi-management/get/{fiId}", fiIdToDelete))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAndVerifyEntity_ShouldReturnSuccess() throws Exception {
        long fiIdToUpdate = 1L;
        FIManagementController.OnboardFIRequest updateRequest = new FIManagementController.OnboardFIRequest(
                "FI1", "Description1", "123", "License1", "Contact1", "Designation1",
                "email1@example.com", "1111111111", "Secondary1", "http://callback1.url",
                List.of("192.168.1.1"), "API_ACCESS", "PublicKey1", "Daily",
                List.of("JSON"), "2024-12-15", "Region1", "AML Officer1", "KYC Policy1",
                "Compliance Cert1", "ACTIVE", "Remarks1", "Business Type1",
                "Business Category1", "Business Sub Category1", "Business Description1",
                "http://website1.com", "Social Media1", "support1@example.com", "1111111111"
        );


        mockMvc.perform(put("/api/fi-management/update/{fiId}", fiIdToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Financial Institution updated successfully!"));

        mockMvc.perform(get("/api/fi-management/get/{fiId}", fiIdToUpdate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated FI"));
    }
}
