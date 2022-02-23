package pageobject;

import data.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResultsPage extends BasePage {

    @FindBy(xpath = "//*[@id='right']/div[1]/div/div/div/h1")
    private WebElement labelNumberProperties;

    @FindBy(xpath = "//div[@class='sb-dates__col --checkin-field xp__date-time']//div[@class='sb-date-field__display']")
    private WebElement inputCheckinDate;

    @FindBy(xpath = "//div[@class='sb-dates__col --checkout-field xp__date-time']//div[@class='sb-date-field__display']")
    private WebElement inputCheckoutDate;

    public ResultsPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void printNumberOfProperties() {
        waitForVisibility(labelNumberProperties);
        System.out.println(labelNumberProperties.getText());
    }

    public void printNameOfProperties() {
        waitForPageLoaded();
        List<WebElement> labelProperties = driver.findElements(By.xpath("//div[@data-testid='title']"));
        labelProperties.forEach(property -> System.out.println(property.getText()));
    }

    public void hasCheckinDate(String expectedDay, String expectedMonth, Language language) {
        waitForVisibility(inputCheckinDate);
        compareDate(inputCheckinDate.getText(), language, expectedDay, expectedMonth);
    }

    public void hasCheckoutDate(String expectedDay, String expectedMonth, Language language) {
        waitForVisibility(inputCheckoutDate);
        compareDate(inputCheckoutDate.getText(), language, expectedDay, expectedMonth);
    }

    private void compareDate(String inputDate, Language language, String expectedDay, String expectedMonth) {
        List<String> dayAndMonth = parseDate(inputDate, language);
        assertThat(dayAndMonth.get(0), equalTo(expectedDay));
        assertThat(dayAndMonth.get(1), equalTo(expectedMonth));
    }

    private List<String> parseDate(String date, Language language) {
        List<String> dayAndMonth = new ArrayList<>();
        switch (language) {
            case ES:
                dayAndMonth.add(date.split(" ")[1]);
                dayAndMonth.add(date.split(" ")[3]);
            default:
                dayAndMonth.add(date.split(" ")[1]);
                dayAndMonth.add(date.split(" ")[2]);
        }
        return dayAndMonth;
    }
}
