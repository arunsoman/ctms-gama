package com.gama.whiv.apigateway;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gateway")
public class APIGatewayController {

    @PostMapping("/register-endpoint")
    public String registerEndpoint(@RequestBody RegisterEndpointRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/endpoints")
    public String listEndpoints() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/remove-endpoint/{endpointId}")
    public String removeEndpoint(@PathVariable String endpointId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/validate-request")
    public String validateRequest(@RequestBody ValidateRequestRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/monitor-traffic")
    public String monitorTraffic(@RequestParam(required = false) String filterBy) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record RegisterEndpointRequest(
            String endpointUrl,
            String method, // e.g., "GET", "POST"
            String serviceName,
            String description
    ) {}

    public record ValidateRequestRequest(
            String endpointUrl,
            String requestPayload
    ) {}
}
