package data;

import utils.PathUtils;

public enum PathToFiles {
    PATH_TO_USER("user.json"),
    PATH_TO_POST("post.json");

    private final String path;

    private PathToFiles(String path) {
        this.path = path;
    }

    public String getPath() {
        return PathUtils.getAbsolutePath(path);
    }

}
