package pageObjectModel.webPageObject.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjectModel.webPageObject.WebPageBase;


public class HomePage extends WebPageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By pageHeader = By.cssSelector("h1");
    private final By logo = By.xpath("(//a[@aria-label='Home']/img)[1]");
    private final By categoryCompany = By.xpath("(//a[contains(text(), 'Company')])");
    private final By subCategoryCareers = By.xpath("(//a[contains(text(), 'Careers')])");
    private final By rejectCookies = By.cssSelector("a#wt-cli-reject-btn");

    public boolean IsLogoDisplayed() throws InterruptedException {

        return seleniumHelper.isElementDisplayed(logo);
    }

    public boolean IsPageHeaderDisplayed() {
        return seleniumHelper.isElementDisplayed(pageHeader);
    }
    public String getPageHeader() throws InterruptedException {
        return seleniumHelper.getText(pageHeader);
    }
    public void clickOnCategoryCompany() throws InterruptedException {
        seleniumHelper.scrollAndClickOn(categoryCompany);
    }
    public void clickOnRejectCookies() throws InterruptedException {
        seleniumHelper.clickOn(rejectCookies);

    }

    public void clickOnSubCategoryCareers() throws InterruptedException {
        seleniumHelper.scrollAndClickOn(subCategoryCareers);
        seleniumHelper.waitTillPageLoadedProperly();
    }



}
