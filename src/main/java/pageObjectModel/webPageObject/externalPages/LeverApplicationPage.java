package pageObjectModel.webPageObject.externalPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjectModel.webPageObject.WebPageBase;

public class LeverApplicationPage extends WebPageBase {
    public LeverApplicationPage(WebDriver driver) {
        super(driver);
    }
    private final By pageHeader = By.cssSelector("h2");
    public String getPageHeader() throws InterruptedException {
        return seleniumHelper.getText(pageHeader);
    }
}
