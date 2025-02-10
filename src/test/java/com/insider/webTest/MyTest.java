package com.insider.webTest;

import com.insider.BaseTest;
import org.testng.annotations.Test;

public class MyTest extends BaseTest {

  @Test
  public void verifyThatUserAbleToNavigateToSite() {
    log.get().info("Test info ");
    driver.get("https://useinsider.com/");
  }
}
