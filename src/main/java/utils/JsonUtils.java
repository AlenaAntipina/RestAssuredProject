package utils;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@UtilityClass
public class JsonUtils {
    private static String jsonString;

    public static String getJsonString() {
        return jsonString;
    }

    public static String getStringData(String field, String path) {
        return (String) getJsonObject(path).get(field);
    }

    private static JSONObject getJsonObject(String path) {
        return (JSONObject) getTheReadJson(path);
    }

    public static Object getTheReadJson(String path){
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
        } catch (ParseException | IOException e) {
            Logger.getInstance().error("Runtime exception " + e);
            return null;
        }
    }

    public static void writeInJsonFromMap(Map<String, String> dataInJson, String path){
        try {
            JSONObject json = (JSONObject) getTheReadJson(path);
            json.putAll(dataInJson);
            jsonString = json.toString();
            Files.write(Paths.get(path), json.toJSONString().getBytes());
        } catch (IOException e) {
            Logger.getInstance().error("Runtime exception " + e);
        }
    }

    public static <T> T getObjectFromLocalFile(String path, TypeReference<T> typeReference){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(path), typeReference);
        } catch (IOException e) {
            Logger.getInstance().error("Runtime exception " + e);
            return null;
        }
    }
}
