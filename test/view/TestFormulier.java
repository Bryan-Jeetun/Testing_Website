package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestFormulier {

	private WebDriver driver;
	String url = "http://localhost:8080/web_war_exploded/";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\bryan\\Desktop\\Schooljaar 2019-2020\\Trimester2\\WEB2\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url + "Controller?command=formulier");
	}

	@Test
	public void test_formulier_ingevuld_naam_leeg_liter(){
		WebElement idInput = driver.findElement(By.id("naam"));
		idInput.clear();
		idInput.sendKeys("Detroit test");

		WebElement berichtInput = driver.findElement(By.id("hoeveelheid"));
		berichtInput.clear();
		berichtInput.sendKeys("");

		WebElement button = driver.findElement(By.id("berekenKost"));
		button.click();

		assertEquals("Bereken Kost", driver.getTitle());
	}

	@After
	public void clean() {
		driver.quit();
	}


}

