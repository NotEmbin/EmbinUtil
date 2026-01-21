package embinmc.lib.util;

import embinmc.lib.util.annotation.NotNull;

import java.util.*;
import java.util.function.Function;

@SuppressWarnings({"unused", "Convert2Diamond"})
public final class ListUtil {

    public static <Z> List<Z> repeatAndListResults(int amount, Function<Integer, Z> function) {
        List<Z> results = new ArrayList<>(amount);
        for (int index = 1; index <= amount; index++) {
            results.add(function.apply(index));
        }
        return results;
    }

    public static <X> Optional<X> getRandomFromList(@NotNull List<X> list) {
        if (list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(MathUtil.randomInt(0, list.size() - 1)));
    }

    public static <A> List<A> newMutable() {
        return new ArrayList<A>();
    }

    @SafeVarargs
    public static <A> List<A> mutableOf(A... things) {
        return new ArrayList<A>(Arrays.asList(things));
    }
}
