package APISteps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Ordering;
import data.PathToFiles;
import data.URI;
import lombok.experimental.UtilityClass;
import models.Post;
import models.User;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.asserts.SoftAssert;
import utils.APIUtils;
import utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class APISteps {
    private static SoftAssert softAssert;

    public static List<Post> getAllPosts() {
        return APIUtils.getAllRequests(URI.POST.getURI(), HttpStatus.SC_OK, ContentType.APPLICATION_JSON.toString(), Post.class);
    }

    public static void checkGetAllPostsSortedById(List<Post> posts){
        softAssert = new SoftAssert();
        List<Integer> allId = posts.stream().map(Post::getId).map(Integer::parseInt).collect(Collectors.toList());
        softAssert.assertTrue(Ordering.natural().isOrdered(allId),
                "Messages are not sorted in ascending order (by id).");
        softAssert.assertAll();
    }

    public static List<Post> getSpecificPost(String postId){
        String param = "id";
        return APIUtils.getSpecificRequest(URI.POST.getURI(), param, postId, HttpStatus.SC_OK, Post.class);
    }

    public static void checkPostWithId99IsGot(List<Post> posts, List<Post> post, String id){
        softAssert = new SoftAssert();
        Post postToCheck = post.get(0);
        int index = posts.indexOf(postToCheck);
        softAssert.assertEquals(postToCheck.getUserId(), posts.get(index).getUserId(),
                "userId is not the same (10).");
        softAssert.assertEquals(postToCheck.getId(), id,
                "id is not 99.");
        softAssert.assertFalse(postToCheck.getTitle().isEmpty(),
                "The title of post with id = 99 is empty.");
        softAssert.assertFalse(postToCheck.getBody().isEmpty(),
                "The body of post with id = 99 is empty.");
        softAssert.assertAll();
    }

    public static Post getPostedRequest(String requestBody){
        String contentType = "application/json";
        return APIUtils.getPostRequest(requestBody, contentType, URI.POST.getURI(), HttpStatus.SC_CREATED, Post.class);
    }

    public static void checkPostRequestPosted(Post postFromResponse){
        Post postFromLocalFile = JsonUtils.getObjectFromLocalFile(PathToFiles.PATH_TO_POST.getPath(), new TypeReference<Post>(){});
        softAssert = new SoftAssert();
        softAssert.assertEquals(postFromResponse.getTitle(), postFromLocalFile.getTitle(),
                "The title doesn't match.");
        softAssert.assertEquals(postFromResponse.getBody(), postFromLocalFile.getBody(),
                "The body doesn't match.");
        softAssert.assertEquals(postFromResponse.getUserId(), postFromLocalFile.getUserId(),
                "The userId doesn't match.");
        softAssert.assertFalse(postFromResponse.getId() == null,
                "The id is not present in the response body");
        softAssert.assertAll();
    }

    public static List<User> getAllUsers(){
        return APIUtils.getAllRequests(URI.USER.getURI(), HttpStatus.SC_OK, ContentType.APPLICATION_JSON.toString(), User.class);
    }

    public static void checkGetAllUsersRight(User userToCheck, User user){
        softAssert = new SoftAssert();
        softAssert.assertEquals(userToCheck.getName(), user.getName(),
                "The name of user with id = 5 is not Chelsey Dietrich");
        softAssert.assertEquals(userToCheck.getUsername(), user.getUsername(),
                "The username of user with id = 5 is not Kamren");
        softAssert.assertEquals(userToCheck.getEmail(), user.getEmail(),
                "The email of user with id = 5 is not Lucio_Hettinger@annie.ca");

        softAssert.assertEquals(userToCheck.getAddress().getStreet(), user.getAddress().getStreet(),
                "The street of user with id = 5 is not Skiles Walks");
        softAssert.assertEquals(userToCheck.getAddress().getSuite(), user.getAddress().getSuite(),
                "The suite of user with id = 5 is not Suite 351");
        softAssert.assertEquals(userToCheck.getAddress().getCity(), user.getAddress().getCity(),
                "The city of user with id = 5 is not Roscoeview");
        softAssert.assertEquals(userToCheck.getAddress().getZipcode(), user.getAddress().getZipcode(),
                "The zipcode of user with id = 5 is not 33263");

        softAssert.assertEquals(userToCheck.getAddress().getGeo().getLat(),
                user.getAddress().getGeo().getLat(),
                "The lat of user with id = 5 is not -31.8129");
        softAssert.assertEquals(userToCheck.getAddress().getGeo().getLng(),
                user.getAddress().getGeo().getLng(),
                "The lng of user with id = 5 is not 62.5342");

        softAssert.assertEquals(userToCheck.getPhone(), user.getPhone(),
                "The phone of user with id = 5 is not (254)954-1289");
        softAssert.assertEquals(userToCheck.getWebsite(), user.getWebsite(),
                "The website of user with id = 5 is not demarco.info");

        softAssert.assertEquals(userToCheck.getCompany().getName(), user.getCompany().getName(),
                "The company (name) of user with id = 5 is not Keebler LLC");
        softAssert.assertEquals(userToCheck.getCompany().getCatchPhrase(), user.getCompany().getCatchPhrase(),
                "The company (catchPhrase) of user with id = 5 is not User-centric fault-tolerant solution");
        softAssert.assertEquals(userToCheck.getCompany().getBs(), user.getCompany().getBs(),
                "The company (bs) of user with id = 5 is not revolutionize end-to-end systems");

        softAssert.assertAll();
    }

    public static List<User> getSpecificUser(String userId){
        String param = "id";
        return APIUtils.getSpecificRequest(URI.USER.getURI(), param, userId, HttpStatus.SC_OK, User.class);
    }

    public static void checkUserWithId5IsGot(List<User> users, User myUser){
        softAssert = new SoftAssert();
        User userToCheck = users.get(0);
        softAssert.assertTrue(userToCheck.equals(myUser),
                "The user with id = 5 is other (data is not correct)");
        softAssert.assertAll();
    }
}
