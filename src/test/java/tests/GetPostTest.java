package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import data.DataForPost;
import data.ID;
import data.PathToFiles;
import models.Post;
import models.User;
import org.testng.annotations.Test;
import APISteps.APISteps;
import utils.JsonUtils;
import java.util.List;

public class GetPostTest extends BaseTest{

    @Deprecated
    @Test
    public void getPostTest(){

        List<Post> allPosts = APISteps.getAllPosts();
        APISteps.checkGetAllPostsSortedById(allPosts);

        List<Post> post99 = APISteps.getSpecificPost(ID.POST_ID_99.getStringId());
        APISteps.checkPostWithId99IsGot(allPosts, post99, ID.POST_ID_99.getStringId());

        JsonUtils.writeInJsonFromMap(DataForPost.getDataForNewPost(ID.USER_ID_1.getStringId()), PathToFiles.PATH_TO_POST.getPath());
        Post postFromPostRequest = APISteps.getPostedRequest(JsonUtils.getJsonString());
        APISteps.checkPostRequestPosted(postFromPostRequest);

        User myUser = JsonUtils.getObjectFromLocalFile(PathToFiles.PATH_TO_USER.getPath(), new TypeReference<User>(){});
        List<User> users = APISteps.getAllUsers();
        APISteps.checkGetAllUsersRight(users.get(Integer.parseInt(ID.USER_ID_5.getStringId()) - 1), myUser);

        List<User> user5 = APISteps.getSpecificUser(ID.USER_ID_5.getStringId());
        APISteps.checkUserWithId5IsGot(user5, myUser);
    }
}
