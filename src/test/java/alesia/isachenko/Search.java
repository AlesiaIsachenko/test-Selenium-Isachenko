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

public class Search extends alesia.isachenko.se.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void Search() throws Exception {
	//этот метод описан в TestBase
	login();
    String [][] list = {{"video1", "2025"},{"film2", "2004"}, {"film3", "1990"}};
    add_elements (list);
	
	//находим все добавленные элементы по имени
	search ("i");
    driver.findElement(By.id("results")).click();
    WebDriverWait wait = new WebDriverWait (driver, 30);
    int k = driver.findElements(By.className("movie_box")).size();
    if (k < 3 ){
    	System.out.println ("Ошибка поиска1.");
    }
    
  //находим все добавленные элементы по году
  	search ("0");
    driver.findElement(By.id("results")).click();
    wait = new WebDriverWait (driver, 30);
    k = driver.findElements(By.className("movie_box")).size();
    if (k < 3 ){
      System.out.println ("Ошибка поиска2.");
     }
    
    //находим один добавленный элемент по имени
  	search ("video1");
    driver.findElement(By.id("results")).click();
    wait = new WebDriverWait (driver, 30);
    k = driver.findElements(By.className("movie_box")).size();
    if (k < 1 ){
      System.out.println ("Ошибка поиска.");
     }
    
    //находим один добавленный элемент по году
  	search ("1990");
    driver.findElement(By.id("results")).click();
    wait = new WebDriverWait (driver, 30);
    k = driver.findElements(By.className("movie_box")).size();
    if (k < 1 ){
      System.out.println ("Ошибка поиска.");
     }
    
    //находим два добавленных элемента по году и по имени
  	search ("1");
    driver.findElement(By.id("results")).click();
    wait = new WebDriverWait (driver, 30);
    k = driver.findElements(By.className("movie_box")).size();
    if (k < 2 ){
      System.out.println ("Ошибка поиска.");
     }
    
  //не находим ни один элемент
  	search ("1997");
    wait = new WebDriverWait (driver, 30);
    /* вот тут у меня один большой вопрос: искомых элементов на странице нет (мы их не видим), 
    но получаем результат что они есть. В чем причина? Как лучше проверить, что поиск ничего не нашел?*/
    boolean resalt = isElementPresent (By.className("movie_box"));
    if (resalt == true){
        System.out.println ("Ошибка поиска.");
       }

	Thread.sleep(500);
    wait = new WebDriverWait (driver, 30);
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
  
  private void add_elements (String [][] list){
	 for (int i =0; i<list.length; i ++){
			add_element (list[i][0], list [i][1]);
	 }
  }
  
  private void search (String keys){
	    driver.findElement(By.id("q")).clear();
	    driver.findElement(By.id("q")).sendKeys(keys + Keys.RETURN);
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
