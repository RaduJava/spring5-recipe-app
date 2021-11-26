package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting object with id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handledNotFound(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handledNumberFormatException(Exception exception) {
        log.error("Handling number format exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
