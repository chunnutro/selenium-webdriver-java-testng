package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Jquery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Faster");

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Slower");

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Fast");

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Slow");

		selectItemDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Dr.");
		sleepInSecond(2);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Dr.");

	}

	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

		selectItemDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

		selectItemDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");

	}

	// @Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

	}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterAndselectItemDropdown("input.search", "span.text", "Albania");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

		enterAndselectItemDropdown("input.search", "span.text", "Belgium");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

		enterAndselectItemDropdown("input.search", "span.text", "Benin");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");

	}

	// H??m : tr??nh l???p l???i code nhi???u l???n, ??i k??m v???i tham s???
	// Defind bi???n ????? d??ng ???????c nhi???u l???n
	public void selectItemDropdown(String parentCss, String allItemCss, String expectedTextItem) {

		// 1 - Click v??o th??? b???t k?? ????? show ra h???t items
		driver.findElement(By.cssSelector(parentCss)).click();

		// 2 - Ch??? item ???????c load ra th??nh c??ng
		// L???y locator ?????i di???n cho t???t c??? item
		// L??u ??: ph???i l???y ?????n th??? ch???a text c???a item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		// ????a h???t t???t c??? items v??o m???t list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

		// 3 - T??m item xem ????ng c??i ??ang c???n hay kh??ng (d??ng v??ng l???p for duy???t qua
		// t???ng ph???n t???)
		for (WebElement tempItem : speedDropdownItems) {

			// 4 - Ki???m tra text c???a item ????ng v???i c??i mong mu???n ch??a
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				// 5 - Click v??o item ????
				tempItem.click();
				// Th???a m??n r???i th?? tho??t ra kh???i v??ng l???p, kh??ng x??t c??c ph???n t??? ti???p theo n???a
				break;
			}
		}

	}

	public void enterAndselectItemDropdown(String textboxCss, String allItemCss, String expectedTextItem) {

		// Nh???p expected item v??o, x??? ra c??c item matching
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		// 2 - Ch??? item ???????c load ra th??nh c??ng
		// L???y locator ?????i di???n cho t???t c??? item
		// L??u ??: ph???i l???y ?????n th??? ch???a text c???a item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		// ????a h???t t???t c??? items v??o m???t list
		List<WebElement> enterAndselectItemDropdown = driver.findElements(By.cssSelector(allItemCss));

		// 3 - T??m item xem ????ng c??i ??ang c???n hay kh??ng (d??ng v??ng l???p for duy???t qua
		// t???ng ph???n t???)
		for (WebElement tempItem : enterAndselectItemDropdown) {

			// 4 - Ki???m tra text c???a item ????ng v???i c??i mong mu???n ch??a
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				// 5 - Click v??o item ????
				tempItem.click();
				// Th???a m??n r???i th?? tho??t ra kh???i v??ng l???p, kh??ng x??t c??c ph???n t??? ti???p theo n???a
				break;
			}
		}

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
