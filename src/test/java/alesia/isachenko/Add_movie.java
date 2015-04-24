package alesia.isachenko;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

public class Add_movie extends alesia.isachenko.se.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void Add_movie() throws Exception {
	login();
	//подсчитываем коичество элементов
	int k1 = driver.findElements(By.className("movie_box")).size();
   
	//добавляем фильм только с обязательными данными
	add_element ("lion", "1984");
	int k2 = driver.findElements(By.className("movie_box")).size();
	if (k2 != k1 + 1){
    	System.out.println("Ошибка добавления элемента.");
    }
    k1 = k2;
    
  //добавляем фильм со всеми данными
    add_element_all_fields ("cat", "2015");
    k2 = driver.findElements(By.className("movie_box")).size();
    if (k2 != k1 + 1){
    	System.out.println("Ошибка добавления элемента.");
    }
    
    driver.findElement(By.linkText("Log out")).click();
    Thread.sleep(500);
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
  }
  
  
  @Test
  public void Add_movie_neg() throws Exception {
	//этот метод описан в TestBase
	login();
	//пытаемся создать описание фильма без года
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("3333" + Keys.RETURN);
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
    driver.findElement(By.id("submit")).click();
   
  //пытаемся создать описание фильма без имени
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("666");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.linkText("Home")).click();

    driver.findElement(By.linkText("Log out")).click();
    Thread.sleep(500);
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
  }
  
  @Test
  public void Delete_movie() throws Exception {
	//этот метод описан в TestBase
	login();
	//подсчитываем коичество элементов
	int k1 = driver.findElements(By.className("movie_box")).size();
	
	//находим любой элемент и его удаляем
	driver.findElement(By.cssSelector("div.nocover")).click();
	driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
	Thread.sleep(500);
	assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
	Thread.sleep(500);

	
	int k2 = driver.findElements(By.className("movie_box")).size();
	if (k2 != k1 - 1){
   	System.out.println("Ошибка удаления элемента.");
    }
   
	driver.findElement(By.linkText("Log out")).click();
	Thread.sleep(500);
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
    
  }


  private void add_element (String name, String year){
	  driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
	  driver.findElement(By.name("name")).clear();
	  driver.findElement(By.name("name")).sendKeys(name);
	  driver.findElement(By.name("year")).clear();
	  driver.findElement(By.name("year")).sendKeys(year);
	  driver.findElement(By.id("submit")).click();
	  driver.findElement(By.linkText("Home")).click(); 
  }
  
  private void add_element_all_fields (String name, String year){
	  	driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("aka")).clear();
		driver.findElement(By.name("aka")).sendKeys("film2");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys(year);
		driver.findElement(By.name("duration")).clear();
		driver.findElement(By.name("duration")).sendKeys("60");
		driver.findElement(By.name("rating")).clear();
		driver.findElement(By.name("rating")).sendKeys("5");
		driver.findElement(By.name("notes")).clear();
		driver.findElement(By.name("notes")).sendKeys("5");
		driver.findElement(By.name("taglines")).clear();
		driver.findElement(By.name("taglines")).sendKeys("taglines");
		driver.findElement(By.name("plotoutline")).clear();
		driver.findElement(By.name("plotoutline")).sendKeys("plot outline");
		driver.findElement(By.name("plots")).clear();
		driver.findElement(By.name("plots")).sendKeys("plots");
		driver.findElement(By.id("text_languages_0")).clear();
		driver.findElement(By.id("text_languages_0")).sendKeys("english");
		driver.findElement(By.name("subtitles")).clear();
		driver.findElement(By.name("subtitles")).sendKeys("english");
		driver.findElement(By.name("audio")).clear();
		driver.findElement(By.name("audio")).sendKeys("english");
		driver.findElement(By.name("video")).clear();
		driver.findElement(By.name("video")).sendKeys("video");
		driver.findElement(By.name("country")).clear();
		driver.findElement(By.name("country")).sendKeys("england");
		driver.findElement(By.name("genres")).clear();
		driver.findElement(By.name("genres")).sendKeys("genres");
		driver.findElement(By.name("director")).clear();
		driver.findElement(By.name("director")).sendKeys("director");
		driver.findElement(By.name("writer")).clear();
		driver.findElement(By.name("writer")).sendKeys("writer");
		driver.findElement(By.name("producer")).clear();
		driver.findElement(By.name("producer")).sendKeys("producer");
		driver.findElement(By.name("music")).clear();
		driver.findElement(By.name("music")).sendKeys("music");
		driver.findElement(By.name("cast")).clear();
		driver.findElement(By.name("cast")).sendKeys("5");
		driver.findElement(By.id("submit")).click();
		driver.findElement(By.linkText("Home")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
