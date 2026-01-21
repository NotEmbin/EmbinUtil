package embinmc.lib.util;

import embinmc.lib.util.annotation.NotNull;
import embinmc.lib.util.logger.LoggerUtil;
import org.slf4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DataFlowIssue")
public final class FileUtil {
    public static final Logger LOGGER = LoggerUtil.getBasicLogger();

    @SuppressWarnings("StringConcatenationArgumentToLogCall")
    public static boolean createAndWriteFile(String path, String content) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(content);
            fileWriter.close();
            FileUtil.LOGGER.info("Wrote to file \"{}\"", path);
            return true;
        } catch (IOException e) {
            FileUtil.LOGGER.error("Failed to write to file %s".formatted(path), e);
            return false;
        }
    }

    @NotNull
    public static List<String> getFileContents(String path, final boolean required) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> lines = new ArrayList<>();
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String formattedLine = currentLine.trim();
                if (!formattedLine.isEmpty()) lines.add(formattedLine);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            return lines;
        } catch (IOException e) {
            if (required) throw new UncheckedIOException("Couldn't get file " + path, e);
            FileUtil.LOGGER.warn("Couldn't find file: {}", path);
            return List.of();
        }
    }

    @NotNull
    public static List<String> getPathsInFolder(String folder) {
        File folder2 = new File(folder);
        List<String> paths = new ArrayList<>(32);
        if (folder2.isDirectory()) {
            for (File file : folder2.listFiles()) {
                if (file.isDirectory()) {
                    paths.addAll(getPathsInFolder(file.getPath().replace('\\', '/')));
                } else {
                    paths.add(file.getPath().replace('\\', '/'));
                }
            }
            return paths;
        } else {
            return List.of();
        }
    }

    @NotNull
    public static List<String> getFoldersInFolder(String folder) {
        File folder2 = new File(folder);
        List<String> paths = new ArrayList<>(32);
        if (folder2.isDirectory()) {
            for (File file : folder2.listFiles()) {
                if (file.isDirectory()) paths.add(file.getPath().replace('\\', '/'));
            }
            return paths;
        } else {
            return List.of();
        }
    }
}
