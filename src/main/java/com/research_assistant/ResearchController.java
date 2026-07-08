package com.research_assistant;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/research")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ResearchController {

    private final ResearchService researchService;

      @GetMapping("/")
      public ResponseEntity<String> greetings() {
          String result = "Welcome to Research Ai Assistant";
        return ResponseEntity.ok(result);
    }

    @PostMapping("/process")
    public ResponseEntity<String> ProcessContent(@RequestBody ResearchRequest request) {
        String result = researchService.processContent(request);
        return ResponseEntity.ok(result);
    }

}
