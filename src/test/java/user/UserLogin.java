package user;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Login;

public class UserLogin {
    private WebDriver driver;
    private Login login;

   public UserLogin(){
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       login = new  Login(driver);
   }

   public void acessarSite(String url) {
       driver.get(url);
   }
}
