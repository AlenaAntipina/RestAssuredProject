package data;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class DataForPost {
    public static Map<String, String> getDataForNewPost(String newPostUserId){
        String title = GenerateData.getTitle();
        String body = GenerateData.getBody();
        Map<String, String> postData = new HashMap<>();
        postData.put("userId", newPostUserId);
        postData.put("title", title);
        postData.put("body", body);
        return postData;
    }
}
