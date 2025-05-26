package user;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;
import pages.ResultadoLogin;

import java.time.Duration;

public class UserLogin {
    private WebDriver driver;
    private Login login;

   public UserLogin(){
       WebDriverManager.chromedriver().setup();
       this.driver = new ChromeDriver();
       this.driver.manage().window().maximize();
       this.login = new Login(driver);
   }

   public void acessarSite(String url) {
       driver.get(url);

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));

   }
   public void acessarPaginaLogin(String url) {
       driver.get(url);

       try{
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
           wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log In")));
           driver.findElement(By.linkText("Log In")).click();
       } catch (Exception e){
       }
   }
   public ResultadoLogin realizarLogin(String usuario, String senha){
       System.out.println("Realizando login com usuario: " +usuario);

       login.preencherUsuario(usuario);
       login.preencherSenha(senha);
       login.enviarLogin();

       return login.verificarResultado();
   }
   public void logout() {
       try{
           driver.findElement(By.linkText("Log Out")).click();
           System.out.println("Logout realizado com sucesso");
       } catch (Exception e) {
           System.out.println("Erro ao realizar logout; "+ e.getMessage());
       }
   }
   public  WebDriver getDriver(){
       return driver;
   }
   public void fecharNavegador() {
       if(driver != null) {
           driver.quit();
       }
   }
}
