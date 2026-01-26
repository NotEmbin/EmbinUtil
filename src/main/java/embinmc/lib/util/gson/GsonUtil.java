package embinmc.lib.util.gson;

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import embinmc.lib.util.ListUtil;
import embinmc.lib.util.annotation.NotNull;
import embinmc.lib.util.annotation.Nullable;
import embinmc.lib.util.logger.LoggerUtil;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"unused"})
public final class GsonUtil {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    public static final Gson GSON = new GsonBuilder().serializeNulls().setStrictness(Strictness.STRICT).create();
    public static final List<String> JSON_EXTENSIONS = ListUtil.mutableOf(".json");

    public static JsonElement fromInputStream(InputStream inputStream, Charset charset) {
        JsonElement jsonObject = GsonUtil.GSON.fromJson(new InputStreamReader(inputStream, charset), JsonElement.class);
        try {
            inputStream.close();
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
        return jsonObject;
    }

    public static JsonElement fromInputStream(InputStream inputStream) {
        return GsonUtil.fromInputStream(inputStream, StandardCharsets.UTF_8);
    }

    public static String formattedJsonString(FormattingStyle style, JsonElement json) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        jsonWriter.setFormattingStyle(style);
        jsonWriter.setStrictness(Strictness.LENIENT);
        try {
            Streams.write(json, jsonWriter);
        } catch (IOException e) {
            GsonUtil.LOGGER.error("Failed to format json", e);
            return json.toString();
        }
        return stringWriter.toString();
    }

    public static String formattedJsonString(JsonElement json) {
        return GsonUtil.formattedJsonString(FormattingStyle.PRETTY, json);
    }

    public static String addJsonExtension(String extension) {
        String finalExtension = extension.startsWith(".") ? extension : "." + extension;
        GsonUtil.JSON_EXTENSIONS.add(finalExtension);
        return finalExtension;
    }

    @Nullable
    public static JsonElement getJsonFile(String path, boolean required) {
        File jsonFile = new File(path);
        try {
            return GsonUtil.fromInputStream(jsonFile.toURI().toURL().openStream());
        } catch (Exception e) {
            if (required) {
                throw new IllegalArgumentException("Can't find required json file: " + jsonFile.getName());
            } else {
                return null;
            }
        }
    }

    @NotNull
    public static JsonElement getRequiredJsonFile(String path) {
        return Objects.requireNonNull(GsonUtil.getJsonFile(path, true));
    }

    public static Optional<JsonElement> getOptionalJsonFile(String path) {
        return Optional.ofNullable(GsonUtil.getJsonFile(path, false));
    }
}
