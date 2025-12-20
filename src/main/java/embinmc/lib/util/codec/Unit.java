package embinmc.lib.util.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

@SuppressWarnings({"unused"})
public enum Unit {
    INSTANCE;

    public static final Codec<Unit> CODEC = MapCodec.unitCodec(Unit.INSTANCE);

    @Override
    public String toString() {
        return "unit";
    }
}
