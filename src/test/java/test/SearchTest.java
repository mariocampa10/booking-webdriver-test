package test;

import data.Language;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.LandingPage;
import pageobject.ResultsPage;
import utils.DriverFactory;

public class SearchTest extends BaseTest {

    //Pages
    private LandingPage landingPage;
    private ResultsPage resultsPage;

    //Test data
    private static final String CITY = "Porto";
    private static final String CHECKIN_DAY = "1";
    private static final String CHECKOUT_DAY = "7";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        landingPage = new LandingPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @DataProvider(name = "language")
    public Object[][] languageList() {
        return new Object[][]{{Language.EN}, {Language.ES}, {Language.NL}};
    }

    @Test(dataProvider = "language")
    public void testSearchNextMonth(Language language) {
        landingPage.navigateToLandingPage(language.value());
        landingPage.setCity(CITY);
        landingPage.clickCalendar();

        //Get current month
        String nextMonth = landingPage.getNextMonth();

        landingPage.clickDay(CHECKIN_DAY);
        landingPage.clickDay(CHECKOUT_DAY);
        landingPage.clickSearch();

        resultsPage.printNumberOfProperties();
        resultsPage.hasCheckinDate(CHECKIN_DAY, nextMonth, language);
        resultsPage.hasCheckoutDate(CHECKOUT_DAY, nextMonth, language);
        resultsPage.printNameOfProperties();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
