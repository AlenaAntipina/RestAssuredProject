package internet;

import lombok.experimental.UtilityClass;
import utils.JsonUtils;
import utils.PathUtils;

@UtilityClass
public class ConfigUtils {
    private static final String PATH = PathUtils.getAbsolutePath("config.json");

    public static String getMainUrl() {
        return JsonUtils.getStringData(FieldInJsonConfig.MAIN_URL.getUrl(), PATH);
    }
}
