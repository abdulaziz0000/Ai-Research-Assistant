package com.research_assistant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Service
public class ResearchService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public ResearchService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String processContent(ResearchRequest request) {

        String prompt = buildPrompt(request);

        Map<String, Object> body = Map.of(
                "model", "gemini-2.5-flash",
                "input", prompt
        );

        try {

            GeminiResponse response = webClient.post()
                    .uri(geminiApiUrl)
                    .header("x-goog-api-key", geminiApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(GeminiResponse.class)
                    .block();

            return extractTextFromResponse(response);

        } catch (WebClientResponseException.TooManyRequests e) {

            return "Gemini API rate limit exceeded. Please try again in a few minutes.";

        } catch (WebClientResponseException e) {

            return "Gemini API Error: " + e.getStatusCode() +
                    "\n\nResponse:\n" + e.getResponseBodyAsString();

        } catch (Exception e) {

            return "Unexpected error: " + e.getMessage();
        }
    }

    private String extractTextFromResponse(GeminiResponse response) {

        if (response == null ||
                response.getSteps() == null ||
                response.getSteps().isEmpty()) {
            return "No response received.";
        }

        for (Step step : response.getSteps()) {

            if (step.getContent() == null || step.getContent().isEmpty()) {
                continue;
            }

            StringBuilder result = new StringBuilder();

            for (Content content : step.getContent()) {
                if (content.getText() != null) {
                    result.append(content.getText());
                }
            }

            return result.toString();
        }

        return "No text found.";
    }

    private String buildPrompt(ResearchRequest request) {

        StringBuilder prompt = new StringBuilder();

        switch (request.getOperation()) {

            case "summarize":
                prompt.append("""
                        Provide a clear and concise summary of the following text.
                        Also include a section called "Important Notes" highlighting the key points.

                        """);
                break;

            case "suggest":
                prompt.append("""
                        Based on the following content, suggest related topics and further reading.
                        Format the response using headings and bullet points.

                        """);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown operation: " + request.getOperation());
        }

        prompt.append(request.getContent());

        return prompt.toString();
    }
}
