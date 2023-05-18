package data;

public enum ID {
    POST_ID_99("99"),
    POST_ID_150("150"),

    USER_ID_5("5"),
    USER_ID_1("1");

    private final String STRING_ID;

    ID(String stringId){
        this.STRING_ID = stringId;
    }

    public String getStringId() {
        return STRING_ID;
    }
}
