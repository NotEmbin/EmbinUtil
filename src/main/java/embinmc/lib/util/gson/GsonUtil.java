package embinmc.lib.util.gson;

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import embinmc.lib.util.ListUtil;
import embinmc.lib.util.logger.LoggerUtil;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused"})
public final class GsonUtil {
    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOGGER = LoggerUtil.getLogger();
    public static final List<String> JSON_EXTENSIONS = ListUtil.mutableOf(".json");

    public static JsonObject fromInputStream(InputStream inputStream, Charset charset) {
        JsonObject jsonObject = GsonUtil.GSON.fromJson(new InputStreamReader(inputStream, charset), JsonObject.class);
        try {
            inputStream.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return jsonObject;
    }

    public static JsonObject fromInputStream(InputStream inputStream) {
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
    private static JsonObject getJsonFile(String path, boolean required) {
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

    public static JsonObject getRequiredJsonFile(String path) {
        return GsonUtil.getJsonFile(path, true);
    }

    public static Optional<JsonObject> getOptionalJsonFile(String path) {
        return Optional.ofNullable(GsonUtil.getJsonFile(path, false));
    }
}
