package data;

public enum URI {
    POST("/posts"),
    USER("/users");

    private final String URI;

    URI(String uri) {
        this.URI = uri;
    }

    public String getURI(){
        return URI;
    }
}
