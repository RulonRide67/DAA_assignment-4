package graph.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.file.*;

public class JsonLoader {
    public static JsonObject load(String path) throws Exception {
        String s = Files.readString(Path.of(path));
        return JsonParser.parseString(s).getAsJsonObject();
    }
}
