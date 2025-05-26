package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherUsuario(String usuario) {
        driver.findElement(By.name("username")).sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        driver.findElement(By.name("password")).sendKeys(senha);
    }

    public void enviarLogin() {
        driver.findElement(By.cssSelector("input.button[value='Log In']")).click();
    }

    public ResultadoLogin verificarResultado() {
        try {
            Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch(Exception e){
        }
            try{
                WebElement accountOverview = driver.findElement(By.cssSelector("#rightPanel h1"));
                if (accountOverview.isDisplayed() &&
                        (accountOverview.getText().contains("Accounts Overview")||
                                accountOverview.getText().contains("welcome"))) {
                            return new ResultadoLogin(true, " Login realizado com sucesso");
                }
            } catch(Exception e){
            }
            try{
                List<WebElement> erros = driver.findElements(By.cssSelector(".error"));
                if (!erros.isEmpty()) {
                    StringBuilder errorMessage = new StringBuilder();
                    for (WebElement error : erros) {
                        if (error.isDisplayed()) {
                            errorMessage.append(error.getText()).append(", ");
                        }
                    }
                    String message = errorMessage.length() > 0 ?
                            errorMessage.substring(0, errorMessage.length() - 2) : "Erro de login desconhecido";

                    return new ResultadoLogin(false, message);
                    }
            } catch(Exception e){
            }
                try{
                    WebElement errorMessage = driver.findElement(By.cssSelector("#rightPanel p.error"));
                    if (errorMessage.isDisplayed()) {
                        return new ResultadoLogin(false, errorMessage.getText());
                    }
                } catch(Exception e){
                }
        return new ResultadoLogin(false, "Resultado de login desconhecido");
        }
    }