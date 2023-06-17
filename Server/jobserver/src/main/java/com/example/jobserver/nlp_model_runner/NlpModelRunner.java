package com.example.jobserver.nlp_model_runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class NlpModelRunner {

    public String getCategoryFromModel(List<String> skills) {
        String arguments = getArguments(skills);
        StringBuilder result = new StringBuilder();
        try {
            // Command to execute the Python script with arguments
            String command = "python C:\\Users\\nikit\\Diploma\\Server\\NLP_model\\run_skills_analyze_model.py" + arguments;

            // Create the ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private String getArguments(List<String> skills) {
        StringBuilder arguments = new StringBuilder();
        for (String skill : skills) {
            arguments.append(" ").append(skill);
        }
        return arguments.toString();
    }
}
