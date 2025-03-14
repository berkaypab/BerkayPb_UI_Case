package com.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

  public static WebDriver getDriver(EnvironmentConfig environmentConfig) throws Exception {

    WebDriver driver = null;


    switch (environmentConfig.getBrowser()) {
      case CHROME -> {
        var options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        if (environmentConfig.isHeadless()) options.addArguments("--headless");
        driver = new ChromeDriver(options);
      }
      case FIREFOX -> {
        var options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        if (environmentConfig.isHeadless()) options.addArguments("--headless");
        driver = new FirefoxDriver(options);
      }
      case EDGE -> {
        var options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        if (environmentConfig.isHeadless()) options.addArguments("--headless");
        driver = new EdgeDriver(options);
      }
      case SAFARI -> driver = new SafariDriver();
      default -> throw new Exception("Please select valid browser");
    }
    driver.manage().window().maximize();

    return driver;
  }
}
