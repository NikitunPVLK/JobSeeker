package com.example.jobserver.api_key;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {
    private static final String API_KEY_FILE_PATH = "api_key.txt"; // Path to the .txt file
    private static final int API_KEY_LENGTH = 32; // Length of the API key in bytes

    public static void generateApiKey() {
        byte[] apiKeyBytes = new byte[API_KEY_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(apiKeyBytes);
        Path filePath = Path.of(API_KEY_FILE_PATH);
        try {
            Files.write(filePath, apiKeyBytes, StandardOpenOption.CREATE);
//            Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);
            System.out.println("API key stored successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readApiKey() {
        Path filePath = Path.of(API_KEY_FILE_PATH);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
