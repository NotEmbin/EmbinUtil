package embinmc.lib.util.function.preset;

import embinmc.lib.util.function.QuadConsumer;
import embinmc.lib.util.function.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Consumers {
    Consumer<?> IGNORE = _ -> {};
    BiConsumer<?, ?> BI_IGNORE = (_, _) -> {};
    TriConsumer<?, ?, ?> TRI_IGNORE = (_, _, _) -> {};
    QuadConsumer<?, ?, ?, ?> QUAD_IGNORE = (_, _, _, _) -> {};
}
