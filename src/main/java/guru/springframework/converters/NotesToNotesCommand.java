package guru.springframework.converters;

import guru.springframework.commands.NoteCommands;
import guru.springframework.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NoteCommands> {
    @Override
    public NoteCommands convert(Notes source) {
        if (source == null) {
            return null;
        }
        final NoteCommands commands = new NoteCommands();
        commands.setId(source.getId());
        commands.setRecipeNotes(source.getRecipeNotes());
        return commands;
    }
}
