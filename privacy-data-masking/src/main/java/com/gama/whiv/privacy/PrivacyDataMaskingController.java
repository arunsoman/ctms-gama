package com.gama.whiv.privacy;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/privacy")
public class PrivacyDataMaskingController {

    @PostMapping("/mask-data")
    public String maskData(@RequestBody MaskDataRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/unmask-data")
    public String unmaskData(@RequestBody UnmaskDataRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/configure-masking")
    public String configureMaskingRules(@RequestBody ConfigureMaskingRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/masking-logs")
    public String getMaskingLogs(@RequestParam(required = false) String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record MaskDataRequest(
            String dataId,
            String maskingRuleId
    ) {}

    public record UnmaskDataRequest(
            String dataId,
            String requestorId
    ) {}

    public record ConfigureMaskingRequest(
            String ruleName,
            String entityType,
            String maskingPattern
    ) {}
}
