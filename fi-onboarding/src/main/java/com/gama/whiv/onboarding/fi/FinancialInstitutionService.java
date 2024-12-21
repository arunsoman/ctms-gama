package com.gama.whiv.onboarding.fi;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FinancialInstitutionService {

    @Autowired
    private FinancialInstitutionRepository financialInstitutionRepository;

    // Retrieve all institutions
    public List<FinancialInstitution> getAllInstitutions() {
        return financialInstitutionRepository.findAll();
    }

    // Retrieve a single institution by ID
    public FinancialInstitution getInstitutionById(Long id) {
        return financialInstitutionRepository.findById(id).orElse(null);
    }

    // Save a new institution
    public List<FinancialInstitution> saveInstitution(List<FinancialInstitution> institutions) {
        return financialInstitutionRepository.saveAll(institutions);
    }

    // Update an existing institution
// Update an existing institution
    public FinancialInstitution updateInstitution(Long id, FinancialInstitution updatedInstitution) {
        return financialInstitutionRepository.findById(id).map(existingInstitution -> {
            existingInstitution.setFiName(updatedInstitution.getFiName());
            existingInstitution.setFiDescription(updatedInstitution.getFiDescription());
            existingInstitution.setRegistrationNumber(updatedInstitution.getRegistrationNumber());
            existingInstitution.setLicenseDetails(updatedInstitution.getLicenseDetails());
            existingInstitution.setContactPersonName(updatedInstitution.getContactPersonName());
            existingInstitution.setContactDesignation(updatedInstitution.getContactDesignation());
            existingInstitution.setContactEmail(updatedInstitution.getContactEmail());
            existingInstitution.setContactPhone(updatedInstitution.getContactPhone());
            existingInstitution.setSecondaryContactDetails(updatedInstitution.getSecondaryContactDetails());
            existingInstitution.setCallbackURL(updatedInstitution.getCallbackURL());
            existingInstitution.setIpWhitelist(updatedInstitution.getIpWhitelist());
            existingInstitution.setApiAccessType(updatedInstitution.getApiAccessType());
            existingInstitution.setEncryptionPublicKey(updatedInstitution.getEncryptionPublicKey());
            existingInstitution.setDataSubmissionFrequency(updatedInstitution.getDataSubmissionFrequency());
            existingInstitution.setSupportedDataFormats(updatedInstitution.getSupportedDataFormats());
            existingInstitution.setOnboardingDate(updatedInstitution.getOnboardingDate());
            existingInstitution.setRegionOfOperation(updatedInstitution.getRegionOfOperation());
            existingInstitution.setAmlComplianceOfficer(updatedInstitution.getAmlComplianceOfficer());
            existingInstitution.setKycPolicyAdherence(updatedInstitution.getKycPolicyAdherence());
            existingInstitution.setComplianceCertification(updatedInstitution.getComplianceCertification());
            existingInstitution.setOnboardingStatus(updatedInstitution.getOnboardingStatus());
            existingInstitution.setRemarks(updatedInstitution.getRemarks());
            existingInstitution.setBusinessType(updatedInstitution.getBusinessType());
            existingInstitution.setBusinessCategory(updatedInstitution.getBusinessCategory());
            existingInstitution.setBusinessSubCategory(updatedInstitution.getBusinessSubCategory());
            existingInstitution.setBusinessDescription(updatedInstitution.getBusinessDescription());
            existingInstitution.setWebsite(updatedInstitution.getWebsite());
            existingInstitution.setSocialMediaHandles(updatedInstitution.getSocialMediaHandles());
            existingInstitution.setCustomerSupportEmail(updatedInstitution.getCustomerSupportEmail());
            existingInstitution.setCustomerSupportPhone(updatedInstitution.getCustomerSupportPhone());
            existingInstitution.setUpdatedDate(new Timestamp(System.currentTimeMillis())); // Set updated timestamp
            existingInstitution.setUpdatedBy(updatedInstitution.getUpdatedBy()); // Track who updated the entity
            return financialInstitutionRepository.save(existingInstitution);
        }).orElseThrow(() -> new EntityNotFoundException("Financial Institution not found with ID: " + id));
    }

    // Delete an institution by ID
    public Long deleteInstitution(Long id) {
        if (financialInstitutionRepository.existsById(id)) {
            financialInstitutionRepository.deleteById(id);
            return id;
        } else {
            throw new EntityNotFoundException("Financial Institution not found with ID: " + id);
        }
    }

    // Validate existence by ID
    public boolean existsById(Long id) {
        return financialInstitutionRepository.existsById(id);
    }

    public void deleteFinancialInstitution(Long fiIdToDelete) {
        financialInstitutionRepository.deleteById(fiIdToDelete);
    }

}
