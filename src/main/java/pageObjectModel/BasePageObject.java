package pageObjectModel;

import elementHelper.WaitHelper;
import org.openqa.selenium.WebDriver;

public class BasePageObject {
  public WaitHelper wait;

  public BasePageObject(WebDriver driver) {
    wait = new WaitHelper(driver);
  }


}
