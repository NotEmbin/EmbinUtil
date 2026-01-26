package embinmc.lib.util.command;

import com.mojang.brigadier.StringReader;
import embinmc.lib.util.Util;
import embinmc.lib.util.function.CharPredicate;

public final class ArgumentUtil {
    private ArgumentUtil() {
        Util.enforceNoInstance(ArgumentUtil.class);
    }

    public static String greedyRead(final StringReader reader, final CharPredicate charPredicate) {
        final int start = reader.getCursor();
        while (reader.canRead() && charPredicate.test(reader.peek())) {
            reader.skip();
        }
        return reader.getString().substring(start, reader.getCursor());
    }

    /**
     * @deprecated Use {@link ArgumentUtil#greedyReadNoSpaces(StringReader)} instead.
     */
    @Deprecated
    public static String greedyRead(final StringReader reader) {
        return ArgumentUtil.greedyRead(reader, c -> c != ' ');
    }

    public static String greedyReadNoSpaces(final StringReader reader) {
        return ArgumentUtil.greedyRead(reader, c -> c != ' ');
    }
}
