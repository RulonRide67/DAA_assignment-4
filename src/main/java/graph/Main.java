package graph;

import graph.util.JsonLoader;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = args.length > 0 ? args[0] : "data/tasks.json";
        JsonObject obj = JsonLoader.load(path);
        System.out.println("Loaded graph: " + obj.get("n").getAsInt() + " nodes");
    }
}
