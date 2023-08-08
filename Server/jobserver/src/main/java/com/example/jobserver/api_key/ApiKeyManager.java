package com.example.jobserver.api_key;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyManager {
    private static final String API_KEY_FILE_PATH = "api_key.txt"; // Path to the .txt file
    private static final int API_KEY_LENGTH = 64; // Length of the API key in bytes
    private static String API_KEY;

    public static void generateApiKey() {
        byte[] apiKeyBytes = new byte[API_KEY_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(apiKeyBytes);
        API_KEY = Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(API_KEY_FILE_PATH))) {
            writer.write(API_KEY);
            System.out.println("API key stored successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidApiKey(String apiKey) {
        return API_KEY.equals(apiKey);
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
