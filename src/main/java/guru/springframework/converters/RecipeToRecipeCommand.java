package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final IngredientToIngredientCommand ingredient;
    private final CategoryToCategoryCommand category;
    private final NotesToNotesCommand notes;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredient, CategoryToCategoryCommand category, NotesToNotesCommand notes) {
        this.ingredient = ingredient;
        this.category = category;
        this.notes = notes;
    }

    @Override
    @Nullable
    @Synchronized
    public RecipeCommand convert(Recipe source) {
        RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setDifficulty(source.getDifficulty());
        command.setDescription(source.getDescription());
        command.setDirections(source.getDirections());
        command.setPrepTime(source.getPrepTime());
        command.setPrepTime(source.getPrepTime());
        command.setServings(source.getServings());
        command.setUrl(source.getUrl());
        command.setSource(source.getSource());
        command.setNotes(notes.convert(source.getNotes()));
        command.setImage(source.getImage());
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(s -> command.getIngredients().add(ingredient.convert(s)));
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(s -> command.getCategories().add(category.convert(s)));
        }
        return command;
    }
}
