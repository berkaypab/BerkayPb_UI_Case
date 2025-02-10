package com.utils;

import com.insider.enums.BrowserName;
import com.insider.enums.EnvironmentType;
import com.insider.utils.Constant;
import com.insider.utils.PathHelper;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Locale;
import lombok.Getter;

@Getter
public class EnvironmentConfig {

  private final BrowserName browser;

  private final boolean headless;
  private final EnvironmentType environment;

  public EnvironmentConfig() {

    // Global config
    var globalDotenv = Dotenv.configure().ignoreIfMissing().load();
    browser = BrowserName.valueOf(globalDotenv.get("BROWSER").toUpperCase(Locale.ROOT));

    environment = EnvironmentType.valueOf(globalDotenv.get("ENV").toUpperCase(Locale.ROOT));

    // read data according to environment
    var environmentBasedConfig =
        Dotenv.configure()
            .directory(PathHelper.getConfigFilePath())
            .filename(environment.toString().toLowerCase() + ".env")
            .load();

    // Setting up constants variable according to environment
    Constant.loadConstants(environmentBasedConfig);

    headless = Boolean.parseBoolean(globalDotenv.get("HEADLESS"));
  }
}
