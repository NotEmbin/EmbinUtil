package embinmc.lib.util.command;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import java.util.Collection;
import java.util.List;

public class CharArgumentType implements ArgumentType<Character> {
    public static final SimpleCommandExceptionType EXCEPTION = new SimpleCommandExceptionType(new LiteralMessage("Expected character"));

    private CharArgumentType() {}

    public static CharArgumentType character() {
        return new CharArgumentType();
    }

    @Override
    public Character parse(StringReader reader) throws CommandSyntaxException {
        final int start = reader.getCursor();
        final String result = reader.readString();
        if (result.length() == 1) {
            return result.charAt(0);
        }
        reader.setCursor(start);
        throw CharArgumentType.EXCEPTION.createWithContext(reader);
    }

    @Override
    public Collection<String> getExamples() {
        return List.of("a", "b", "x");
    }

    public static char getChar(final CommandContext<?> context, final String name) {
        return context.getArgument(name, char.class);
    }
}
