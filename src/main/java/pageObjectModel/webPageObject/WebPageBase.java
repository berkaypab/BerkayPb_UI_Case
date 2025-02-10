package pageObjectModel.webPageObject;

import elementHelper.web.SeleniumHelper;
import pageObjectModel.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebPageBase extends BasePageObject {

  public SeleniumHelper seleniumHelper;


  public WebPageBase(WebDriver driver) {
    super(driver);
    seleniumHelper = new SeleniumHelper(driver);
  }

  private final By logoLink = By.cssSelector("(//a[@aria-label='Home'])[1]");

  public String getPageTitle() throws InterruptedException {
    return seleniumHelper.getPageTitle();
  }

  public void clickOnLogoLink() throws InterruptedException {
    seleniumHelper.clickOnElementUsingJavaScript(logoLink);
    seleniumHelper.waitTillPageLoadedProperly();
  }
  public void navigateToCareersQA() throws InterruptedException {
    seleniumHelper.navigateTo("https://useinsider.com/careers/quality-assurance/");
    seleniumHelper.waitTillPageLoadedProperly();
    seleniumHelper.waitForAjaxLoad();

  }
}
