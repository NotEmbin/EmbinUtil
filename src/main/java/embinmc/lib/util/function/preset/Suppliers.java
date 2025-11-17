package embinmc.lib.util.function.preset;

import java.util.function.Supplier;

public interface Suppliers {
    Supplier<Boolean> ALWAYS = () -> true;
    Supplier<Boolean> NEVER = () -> false;
}
