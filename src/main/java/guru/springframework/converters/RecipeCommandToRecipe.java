package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory category;
    private final IngredientCommandToIngredient ingredient;
    private final NotesCommandToNotes notes;

    public RecipeCommandToRecipe(CategoryCommandToCategory category, IngredientCommandToIngredient ingredient, NotesCommandToNotes notes) {
        this.category = category;
        this.ingredient = ingredient;
        this.notes = notes;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDescription(source.getDescription());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setNotes(notes.convert(source.getNotes()));
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(categor -> recipe.getCategories().add(category.convert(categor)));
        }
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(s -> recipe.getIngredients().add(ingredient.convert(s)));
        }
        return recipe;
    }
}
