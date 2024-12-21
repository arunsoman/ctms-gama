package com.gama.whiv.onboarding.fi;

import com.gama.whiv.onboarding.api.APIKey;
import com.gama.whiv.onboarding.api.APIKeyService;
import com.gama.whiv.onboarding.api.usage.APIUsage;
import com.gama.whiv.onboarding.api.usage.APIUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/fi-management")
public class FIManagementController {

    @Autowired
    private FinancialInstitutionService financialInstitutionService;
    @Autowired
    private APIKeyService apiKeyService;
    @Autowired
    private APIUsageService apiUsageService;

    @PostMapping("/onboard")
    public String onboardFI(@RequestBody OnboardFIRequest request) {
        FinancialInstitution fi = new FinancialInstitution();
        fi.setFiName(request.fiName());
        fi.setFiDescription(request.fiDescription());
        fi.setRegistrationNumber(request.registrationNumber());
        fi.setLicenseDetails(request.licenseDetails());
        fi.setContactPersonName(request.contactPersonName());
        fi.setContactDesignation(request.contactDesignation());
        fi.setContactEmail(request.contactEmail());
        fi.setContactPhone(request.contactPhone());
        fi.setSecondaryContactDetails(request.secondaryContactDetails());
        fi.setCallbackURL(request.callbackURL());
        fi.setIpWhitelist(new HashSet<>(request.ipWhitelist()));
        fi.setApiAccessType(request.apiAccessType());
        fi.setEncryptionPublicKey(request.encryptionPublicKey());
        fi.setDataSubmissionFrequency(request.dataSubmissionFrequency());
        fi.setSupportedDataFormats(String.valueOf(request.supportedDataFormats()));
        fi.setOnboardingDate(String.valueOf(new Timestamp(System.currentTimeMillis())));
        financialInstitutionService.saveInstitution(List.of(fi));
        return "Financial Institution onboarded successfully!";
    }

    @PostMapping("/generate-api-key")
    public String generateAPIKey(@RequestBody GenerateAPIKeyRequest request) {
        APIKey apiKey = new APIKey();
        apiKey.setFiId(request.fiId());
        apiKey.setGeneratedBy(request.generatedBy());
        apiKey.setKeyValue("generated-key-value"); // Replace with actual key generation logic
        apiKey.setStatus("ACTIVE");
        apiKeyService.saveKey(apiKey);
        return "API Key generated successfully!";
    }

    @PostMapping("/revoke-api-key")
    public String revokeAPIKey(@RequestBody RevokeAPIKeyRequest request) {
        APIKey apiKey = apiKeyService.getKeyById(Long.valueOf(request.apiKeyId()));
        if (apiKey != null) {
            apiKey.setStatus("REVOKED");
            apiKey.setRevokedBy(request.revokedBy());
            apiKey.setRevocationReason(request.reason());
            apiKeyService.saveKey(apiKey);
            return "API Key revoked successfully!";
        }
        return "API Key not found!";
    }

    @GetMapping("/list-fi")
    public List<FinancialInstitution> listFIs(@RequestParam(required = false) String status) {
        return financialInstitutionService.getAllInstitutions();
    }

    @GetMapping("/get-fi/{fiId}")
    public ResponseEntity<FinancialInstitution> getFIById(@PathVariable String fiId) {
        FinancialInstitution fi = financialInstitutionService.getInstitutionById(Long.valueOf(fiId));
        if (fi != null) {
            return ResponseEntity.ok(fi);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/update-fi")
    public ResponseEntity<String> updateFI(@RequestBody FinancialInstitution updatedFI) {
        FinancialInstitution existingFI = financialInstitutionService.getInstitutionById(updatedFI.getId());
        if (existingFI != null) {
            existingFI.setFiName(updatedFI.getFiName());
            existingFI.setFiDescription(updatedFI.getFiDescription());
            existingFI.setRegistrationNumber(updatedFI.getRegistrationNumber());
            existingFI.setLicenseDetails(updatedFI.getLicenseDetails());
            existingFI.setContactPersonName(updatedFI.getContactPersonName());
            existingFI.setContactDesignation(updatedFI.getContactDesignation());
            existingFI.setContactEmail(updatedFI.getContactEmail());
            existingFI.setContactPhone(updatedFI.getContactPhone());
            existingFI.setSecondaryContactDetails(updatedFI.getSecondaryContactDetails());
            existingFI.setCallbackURL(updatedFI.getCallbackURL());
            existingFI.setIpWhitelist(updatedFI.getIpWhitelist());
            existingFI.setApiAccessType(updatedFI.getApiAccessType());
            existingFI.setEncryptionPublicKey(updatedFI.getEncryptionPublicKey());
            existingFI.setDataSubmissionFrequency(updatedFI.getDataSubmissionFrequency());
            existingFI.setSupportedDataFormats(updatedFI.getSupportedDataFormats());
            financialInstitutionService.saveInstitution(List.of(existingFI));
            return ResponseEntity.ok("Financial Institution updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Financial Institution not found!");
    }

    @GetMapping("/list-api-keys/{fiId}")
    public ResponseEntity<List<APIKey>> listAPIKeys(@PathVariable String fiId) {
        List<APIKey> apiKeys = apiKeyService.getAllKeysByFIId(fiId);
        return ResponseEntity.ok(apiKeys);
    }

    @GetMapping("/list-api-usage/{fiId}")
    public ResponseEntity<List<APIUsage>> listAPIUsage(@PathVariable String fiId) {
        List<APIUsage> apiUsages = apiUsageService.getAllUsagesByFIId(fiId);
        return ResponseEntity.ok(apiUsages);
    }

    public record OnboardFIRequest(
        String fiName,
        String fiDescription,
        String registrationNumber,
        String licenseDetails,
        String contactPersonName,
        String contactDesignation,
        String contactEmail,
        String contactPhone,
        String secondaryContactDetails,
        String callbackURL,
        List<String> ipWhitelist,
        String apiAccessType,
        String encryptionPublicKey,
        String dataSubmissionFrequency,
        List<String> supportedDataFormats,
        String onboardingDate,
        String regionOfOperation,
        String amlComplianceOfficer,
        String kycPolicyAdherence,
        String complianceCertification,
        String onboardingStatus,
        String remarks,
        String businessType,
        String businessCategory,
        String businessSubCategory,
        String businessDescription,
        String website,
        String socialMediaHandles,
        String customerSupportEmail,
        String customerSupportPhone
    ) {
    }

    public record GenerateAPIKeyRequest(
            String fiId,
            String generatedBy
    ) {}

    public record RevokeAPIKeyRequest(
            String apiKeyId,
            String revokedBy,
            String reason
    ) {}

    public record UpdateFIRequest(String updatedFi, String updatedDescription, String updatedLicense,
                                  String updatedContact, String updatedDesignation, String mail, String number,
                                  String updatedSecondary, String url, String updatedRemarks,
                                  String updatedBusinessType, String updatedBusinessCategory,
                                  String updatedBusinessSubCategory, String updatedBusinessDescription,
                                  String updatedRegion) {
    }
}
