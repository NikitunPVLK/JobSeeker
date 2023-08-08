package com.example.jobserver.api_key;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiKeyManagerTest {

    @Test
    void generateApiKey() {
        ApiKeyManager.generateApiKey();
    }

    @Test
    void isValidApiKeyTrue() {
        ApiKeyManager.generateApiKey();
        assertTrue(ApiKeyManager.isValidApiKey(ApiKeyManager.readApiKey()));
    }

    @Test
    void isValidApiKeyFalse() {
        ApiKeyManager.generateApiKey();
        assertFalse(ApiKeyManager.isValidApiKey("not valid api key"));
    }
}