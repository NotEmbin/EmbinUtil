package embinmc.lib.util.function.preset;

import java.util.function.Predicate;

public interface Predicates {
    Predicate<?> ALWAYS = _ -> true;
    Predicate<?> NEVER = _ -> false;
}
