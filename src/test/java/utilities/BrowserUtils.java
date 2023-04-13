package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {
	
	
	Actions action;
	WebDriverWait wait;
	
	public void waitUntilElementVisible(WebElement element) {
		wait = new WebDriverWait(Driver.getDriver(), 5);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void actionsSendKeys(WebElement element, String text) {
		action = new Actions(Driver.getDriver());
		action.sendKeys(element, text).build().perform();
	}

}
