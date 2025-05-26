package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Cadastro {
    private WebDriver driver;

    public Cadastro(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherPrimeiroNome(String nome){
        driver.findElement(By.name("customer.firstName")).sendKeys(nome);
    }
    public void preencherSegundoNome(String segundoNome){
        driver.findElement(By.name("customer.lastName")).sendKeys(segundoNome);
    }
    public void preencherEndereco(String endereco){
        driver.findElement(By.name("customer.address.street")).sendKeys(endereco);
    }
    public void preencherCidade(String cidade){
        driver.findElement(By.name("customer.address.city")).sendKeys(cidade);
    }
    public void preencherEstado(String estado){
        driver.findElement(By.name("customer.address.state")).sendKeys(estado);
    }
    public void preencherCep(String cep){
        driver.findElement(By.name("customer.address.zipCode")).sendKeys(cep);
    }
    public void preencherFone(String fone){
        driver.findElement(By.name("customer.phoneNumber")).sendKeys(fone);
    }
    public void preencherSSN(String ssn){
        driver.findElement(By.name("customer.ssn")).sendKeys(ssn);
    }
    public void  preencherUsuario(String usuario){
        driver.findElement(By.name("customer.username")).sendKeys(usuario);
    }
    public void preecherSenha(String senha){
        driver.findElement(By.name("customer.password")).sendKeys(senha);
    }
    public void preencherRepetSenha(String repetSenha){
        driver.findElement(By.name("repeatedPassword")).sendKeys(repetSenha);
    }
    public void enviarCadastro() {
        driver.findElement(By.cssSelector("input.button[value='Register']")).click();
    }
    public ResultadoCadastro verificarResultado() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            WebElement welcomeMessage = driver.findElement(By.cssSelector("#rightPanel h1"));
            if (welcomeMessage.isDisplayed() &&
                    (welcomeMessage.getText().contains("Welcome") ||
                            welcomeMessage.getText().contains("successfully"))) {
                return new ResultadoCadastro(true, "Cadastro realizado com sucesso");
            }
        } catch (Exception e) {
        }
            try {
                List<WebElement> errors = driver.findElements(By.cssSelector(".error"));
                if (!errors.isEmpty()) {
                    StringBuilder errorMessage = new StringBuilder();
                    for (WebElement error : errors) {
                        if (error.isDisplayed()) {
                            errorMessage.append(error.getText()).append(", ");
                        }
                    }
                    String message = errorMessage.length() > 0 ?
                            errorMessage.substring(0, errorMessage.length() - 2) :
                            "Erro desconhecido";

                    return new ResultadoCadastro(false, message);
                }
            } catch (Exception e) {

            }

            return new ResultadoCadastro(false, "Resultado desconhecido");
        }

        }



