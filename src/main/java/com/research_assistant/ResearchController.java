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


    @PostMapping("/process")
    public ResponseEntity<String> ProcessContent(@RequestBody ResearchRequest request) {
        System.out.println("Controller reached");
        String result = researchService.processContent(request);
        return ResponseEntity.ok(result);
    }

}
