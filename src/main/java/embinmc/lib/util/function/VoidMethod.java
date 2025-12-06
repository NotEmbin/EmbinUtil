package embinmc.lib.util.function;

/**
 * Functionally identical to {@link Runnable}, but with a more clear name.
 * <p> {@link VoidMethod#execute()} is called instead of {@link Runnable#run()}.
 */
@FunctionalInterface
public interface VoidMethod {
    void execute();
}
