package data;

import com.github.javafaker.Faker;

public class GenerateData {
    private GenerateData(){
    }

    public static String getTitle(){
        return new Faker().book().title();
    }

    public static String getBody(){
        return new Faker().yoda().quote();
    }

}
