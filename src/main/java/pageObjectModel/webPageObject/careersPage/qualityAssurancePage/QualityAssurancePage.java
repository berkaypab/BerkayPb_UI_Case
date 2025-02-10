package pageObjectModel.webPageObject.careersPage.qualityAssurancePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjectModel.webPageObject.WebPageBase;

import java.util.List;

public class QualityAssurancePage extends WebPageBase {
    public QualityAssurancePage(WebDriver driver) {
        super(driver);
    }

    private final By seeAllQAJobsButton = By.xpath("(//a[contains(text(), 'See all QA jobs')])");
    private final By selectLocation = By.cssSelector("select#filter-by-location");
    private final By selectOptionChildLocation = By.cssSelector("option:nth-child(2)");
    private final By selectDepartment = By.cssSelector("select#filter-by-department");
    private final By jobs = By.xpath("//a[text()='View Role']/ancestor::div[1]");//#jobs-list
    private final By jobPositionTitle = By.xpath("//p[starts-with(@class,'position-title')]");
    private final By jobPositionDepartment = By.xpath("//span[starts-with(@class,'position-department')]");
    private final By jobPositionLocation = By.xpath("//div[starts-with(@class,'position-location')]");
    private final By firstJobsTextOnTheListBeforeFilter = By.xpath("(//div[@id='jobs-list']/div//p)[1]");
    private final By firstJobOnTheList = By.xpath("//a[text()='View Role']");


    public void clickOnQAJobs() throws InterruptedException {
        seleniumHelper.scrollAndClickOn(seeAllQAJobsButton);
        seleniumHelper.waitTillPageLoadedProperly();
    }

    public void selectLocation() throws InterruptedException {
        seleniumHelper.waitForRefreshedElement(firstJobsTextOnTheListBeforeFilter);
        seleniumHelper.waitForPresenceOfNestedElementLocatedBy(selectLocation, selectOptionChildLocation);
        // Istanbul, Turkiye / Istanbul, Turkey
        List<String> allOptions = seleniumHelper.getAllOptionText(selectLocation);
        for (String option : allOptions) {
            if (option.contains("Istanbul")) {
                seleniumHelper.selectOptionByText(selectLocation, option);
                break;
            }
        }

    }

    public void selectDepartment() throws InterruptedException {
        seleniumHelper.waitForPresenceOfNestedElementLocatedBy(selectDepartment, selectOptionChildLocation);
        seleniumHelper.waitForRefreshedElement(firstJobsTextOnTheListBeforeFilter);
        seleniumHelper.selectOptionByText(selectDepartment, "Quality Assurance");
        seleniumHelper.waitForAjaxLoad();
    }

    public boolean isAllJobsPresence() {
        return seleniumHelper.checkPresenceOfAllElements(jobs);
    }

    public List<String> getAllJobsTitle() {
        seleniumHelper.waitForAjaxLoad();
        return seleniumHelper.getAllElementsText(jobPositionTitle);
    }

    public List<String> getAllJobsDepartment() {
        return seleniumHelper.getAllElementsText(jobPositionDepartment);
    }

    public List<String> getAllJobsLocation() {
        return seleniumHelper.getAllElementsText(jobPositionLocation);
    }

    public void clickOnFirstJob() throws InterruptedException {

        seleniumHelper.checkPresenceOfAllElements(firstJobOnTheList);
        seleniumHelper.hoverElement(firstJobOnTheList);
        seleniumHelper.clickOn(firstJobOnTheList);
        waitForLeverApplicationPageLandingAndSwitchTo();
    }

    public void closeLeverTabAndSwitchToMain() {
        seleniumHelper.closeCurrentTab();
        seleniumHelper.switchToWindowByIndex(0);
    }

    private void waitForLeverApplicationPageLandingAndSwitchTo() throws InterruptedException {
        seleniumHelper.waitForNumberOfWindowsToBe(2);
        seleniumHelper.switchToWindowByIndex(1);
        seleniumHelper.waitTillPageLoadedProperly();
    }
    private String getFirstJobsNameOnTheList() throws InterruptedException {
        return seleniumHelper.getText(firstJobsTextOnTheListBeforeFilter);
    }

}
