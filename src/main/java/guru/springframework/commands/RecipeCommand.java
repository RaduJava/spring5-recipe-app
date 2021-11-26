package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private Integer cookTime;
    private String description;
    private Difficulty difficulty;
    private String directions;
    private Integer prepTime;
    private Integer servings;
    private Byte[] image;
    private String source;
    private String url;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private NoteCommands notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
