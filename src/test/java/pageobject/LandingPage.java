package pageobject;

import data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    @FindBy(id = "ss")
    private WebElement inputCity;

    @FindBy(xpath = "//div[@class='xp__dates xp__group']")
    private WebElement calendarCheckinCheckout;

    @FindBy(xpath = "//div[@class='bui-calendar']//div[@class='bui-calendar__wrapper'][2]/div")
    private WebElement labelNextMonth;

    @FindBy(className = "sb-searchbox__button")
    private WebElement buttonSearch;

    public LandingPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLandingPage() {
        driver.get(Constants.URL_BASE);
    }

    public void navigateToLandingPage(String language) {
        driver.get(Constants.URL_BASE + "/index." + language + ".html");
    }

    public void setCity(String city) {
        waitForVisibility(inputCity);
        inputCity.sendKeys(city);
    }

    public void clickCalendar() {
        waitForVisibility(calendarCheckinCheckout);
        calendarCheckinCheckout.click();
    }

    public void clickDay(String day) {
        waitForPageLoaded();
        driver.findElement(By.xpath("//div[@class='bui-calendar']//div[@class='bui-calendar__wrapper'][2]/table//*[text() = '" + day + "']")).click();
    }

    public void clickSearch() {
        waitForElementClickable(buttonSearch);
        buttonSearch.click();
    }

    public String getNextMonth() {
        waitForVisibility(labelNextMonth);
        String month = labelNextMonth.getText().split(" ")[0];
        return month;
    }
}
