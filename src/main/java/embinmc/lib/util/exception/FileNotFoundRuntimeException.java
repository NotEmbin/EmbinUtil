package embinmc.lib.util.exception;

import java.io.IOException;

@Deprecated
public class FileNotFoundRuntimeException extends IllegalStateException {
    public FileNotFoundRuntimeException(String message) {
        super(message);
    }

    public FileNotFoundRuntimeException(IOException exception) {
        super(exception);
    }

    public FileNotFoundRuntimeException(String message, IOException exception) {
        super(message, exception);
    }
}
