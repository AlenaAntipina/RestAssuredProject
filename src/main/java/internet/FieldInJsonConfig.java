package internet;

enum FieldInJsonConfig {
    MAIN_URL("mainUrl");

    private final String url;

    private FieldInJsonConfig(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
