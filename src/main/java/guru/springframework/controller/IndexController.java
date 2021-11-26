package guru.springframework.controller;

import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping({"/index", "/"})
    public String getIndexPage(Model model) {
        log.debug("Getting a index page ");
        model.addAttribute("recipes", recipeService.getRecipe());
        return "index";
    }
}
