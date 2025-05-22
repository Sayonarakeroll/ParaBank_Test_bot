package user;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import java.time.Duration;

public class UserLogin {
    private WebDriver driver;
    private Login login;

   public UserLogin(){
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       login = new  Login(driver);
   }

   public void acessarSite(String url) {
       driver.get(url);

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(By.linkText("")));

       driver.findElement(By.linkText("")).click();
   }
   public void  fazerLogin(String usuario, String senha){
       login.preencherUsuario(usuario);
       login.preencherSenha(senha);

       System.out.println("Logando usuario");

       login.enviarLogin();
   }
}
