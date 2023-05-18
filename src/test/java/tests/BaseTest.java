package tests;

import internet.ConfigUtils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigUtils.getMainUrl();
    }

}
