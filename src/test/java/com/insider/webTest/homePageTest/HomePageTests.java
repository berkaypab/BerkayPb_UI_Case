package com.insider.webTest.homePageTest;

import com.insider.BaseTest;
import org.testng.annotations.Test;
import pageObjectModel.webPageObject.careersPage.CareersPage;
import pageObjectModel.webPageObject.careersPage.qualityAssurancePage.QualityAssurancePage;
import pageObjectModel.webPageObject.externalPages.LeverApplicationPage;
import pageObjectModel.webPageObject.homePage.HomePage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageTests extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void verifyInsiderHomepageAccessible()
            throws InterruptedException {
        var homePage = new HomePage(driver);
        final String expectedPageTitle = "#1 Leader in Individualized";
        final String expectedHeader = "and Customer Engagement Platform";
        log.get().info("Test started: Verifying accessibility of the Insider homepage.");
        log.get().info("Checking if the homepage logo is displayed...");
        assertThat(homePage.IsLogoDisplayed()).isTrue();
        assertThat(homePage.IsPageHeaderDisplayed()).isTrue();
        log.get().info("Verifying that the page title contains the expected text...");
        assertThat(homePage.getPageTitle()).contains(expectedPageTitle);
        log.get().info("Verifying that the page header contains the expected text...");
        assertThat(homePage.getPageHeader()).contains(expectedHeader);
        log.get().info("Test successfully completed: Insider homepage is accessible.");
    }

    @Test(priority = 2, groups = "smoke")
    public void verifyCareerPageAndSectionsAccessible() throws InterruptedException {
        log.get().info("Navigating to Career Page and verifying sections");
        var homePage = new HomePage(driver);
        var careersPage = new CareersPage(driver);
        homePage.clickOnRejectCookies();
        homePage.clickOnCategoryCompany();
        homePage.clickOnSubCategoryCareers();
        assertThat(careersPage.isAllSectionsPresence()).isTrue();
    }

    @Test(priority = 3, groups = "smoke")
    public void verifyJobListFilteredByLocationAndDepartment() throws InterruptedException {
        log.get().info("Filtering job list by location and department");
        var homePage = new HomePage(driver);
        var qualityAssurancePage = new QualityAssurancePage(driver);
        homePage.navigateToCareersQA();
        qualityAssurancePage.clickOnQAJobs();
        homePage.clickOnRejectCookies();
        qualityAssurancePage.selectLocation();
        qualityAssurancePage.selectDepartment();
        assertThat(qualityAssurancePage.isAllJobsPresence()).isTrue();
    }

    @Test(priority = 4, groups = "smoke")
    public void verifyJobDetailsMatchSelectedFilters()
            throws InterruptedException {
        log.get().info("Verifying job details match selected filters");
        var homePage = new HomePage(driver);
        var qualityAssurancePage = new QualityAssurancePage(driver);
        homePage.navigateToCareersQA();
        qualityAssurancePage.clickOnQAJobs();
        qualityAssurancePage.selectLocation();
        qualityAssurancePage.selectDepartment();


        List<String> allJobTitles = qualityAssurancePage.getAllJobsTitle();
        List<String> allJobDepartments = qualityAssurancePage.getAllJobsDepartment();
        List<String> allJobLocations = qualityAssurancePage.getAllJobsLocation();
        log.get().info("Checking job titles");
        allJobTitles.forEach(x -> assertThat(x).containsAnyOf("Quality Assurance", "QA"));
        log.get().info("Checking job departments");
        allJobDepartments.forEach(x -> assertThat(x).containsAnyOf("Quality Assurance", "QA"));
        log.get().info("Checking job locations");
        allJobLocations.forEach(x -> assertThat(x).containsAnyOf("Istanbul, Turkiye", "Istanbul, Turkey"));
    }

    @Test(priority = 5, groups = {"smoke", "regression"})
    public void verifyRedirectionToLeverApplicationPage()
            throws InterruptedException {
        log.get().info("Verifying redirection to Lever application page");
        var homePage = new HomePage(driver);
        var qualityAssurancePage = new QualityAssurancePage(driver);
        var leverApplicationPage = new LeverApplicationPage(driver);
        homePage.navigateToCareersQA();
        qualityAssurancePage.clickOnQAJobs();
        homePage.clickOnRejectCookies();
        qualityAssurancePage.selectLocation();
        qualityAssurancePage.selectDepartment();
        qualityAssurancePage.clickOnFirstJob();
        final String expectedPageTitle = "Senior Software Quality Assurance Engineer";
        final String expectedHeader = "Senior Software Quality Assurance Engineer";
        log.get().info("Checking Lever application page title");
        assertThat(leverApplicationPage.getPageTitle()).contains(expectedPageTitle);
        log.get().info("Checking Lever application page header");
        assertThat(leverApplicationPage.getPageHeader()).contains(expectedHeader);
        log.get().info("Closing Lever tab and switching back to main page");
        qualityAssurancePage.closeLeverTabAndSwitchToMain();

    }

}
