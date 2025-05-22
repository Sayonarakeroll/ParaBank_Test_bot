package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherUsuario(String username) {
        driver.findElement(By.name("username")).sendKeys(username);
    }
    public void preencherSenha(String senha) {
        driver.findElement(By.name("password")).sendKeys(senha);
    }
    public void enviarLogin(){
        driver.findElement(By.cssSelector("")).click();
    }
}
