package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
    private Difficulty difficulty;

    @NotBlank
    private String directions;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(100)
    private Integer servings;
    private Byte[] image;
    private String source;
    @URL
    private String url;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private NoteCommands notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
