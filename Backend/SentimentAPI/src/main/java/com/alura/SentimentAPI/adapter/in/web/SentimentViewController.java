package com.alura.SentimentAPI.adapter.in.web;

import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SentimentViewController {

    private final AnalyzeSentimentUseCase analyzeUseCase;

    public SentimentViewController(AnalyzeSentimentUseCase analyzeUseCase) {
        this.analyzeUseCase = analyzeUseCase;
    }


    @GetMapping("/analisis")
    public String home(Model model) {
        model.addAttribute("text", "");
        return "index";
    }

    @PostMapping("/analyze")
    public String analyze(
            @RequestParam String text,
            Model model
    ) {

        try {
            SentimentResult result = analyzeUseCase.analyze(text);

            model.addAttribute("text", text);
            model.addAttribute("sentiment", result.label());
            model.addAttribute("probability", result.probability());

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "index";
    }
}
