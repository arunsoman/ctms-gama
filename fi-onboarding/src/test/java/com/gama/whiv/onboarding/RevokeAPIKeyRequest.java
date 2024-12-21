package com.gama.whiv.onboarding;

public record RevokeAPIKeyRequest(String number, String admin, String noLongerNeeded) {
}
