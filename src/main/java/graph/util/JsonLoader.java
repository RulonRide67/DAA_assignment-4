package graph.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import graph.core.Graph;

import java.nio.file.*;

public class JsonLoader {
    public static JsonObject load(String path) throws Exception {
        String s = Files.readString(Path.of(path));
        return JsonParser.parseString(s).getAsJsonObject();
    }
    public static java.util.List<Graph> loadArray(Path path) throws Exception {
        String s = Files.readString(path);
        var arr = JsonParser.parseString(s).getAsJsonArray();
        java.util.List<Graph> list = new java.util.ArrayList<>();
        for (var el : arr) {
            list.add(Graph.fromJson(el.getAsJsonObject()));
        }
        return list;
    }

}