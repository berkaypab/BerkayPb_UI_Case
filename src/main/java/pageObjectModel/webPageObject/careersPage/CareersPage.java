package pageObjectModel.webPageObject.careersPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjectModel.webPageObject.WebPageBase;

public class CareersPage extends WebPageBase {
    public CareersPage(WebDriver driver) {
        super(driver);
    }

    private final By sections= By.xpath("//section[starts-with(@id, 'career')]");
    private final By pageHeader= By.cssSelector("h1");//"Ready to disrupt? "

    public boolean isPageHeaderDisplayed() {
        return seleniumHelper.isElementDisplayed(pageHeader);
    }

    public String getPageHeader() throws InterruptedException {
        return seleniumHelper.getText(pageHeader);
    }
    public boolean isAllSectionsPresence() {
        return seleniumHelper.checkPresenceOfAllElements(sections);
    }
}
