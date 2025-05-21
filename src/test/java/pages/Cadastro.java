package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cadastro {
    private WebDriver driver;

    public Cadastro(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherPrimeiroNome(String nome){
        driver.findElement(By.name("customer.firstName")).sendKeys(nome);
    }
    public void preencherSeggundoNome(String segundoNome){
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
    public void enviarCadastro(){
        driver.findElement(By.cssSelector("input.button[value='Register']")).click();
            try {
                // Verifica o cadastro
                String pageSource = driver.getPageSource();
                if (pageSource.contains("successfully registered") || pageSource.contains("Welcome")) {
                    System.out.println("Cadastro realizado com sucesso!");
                } else if (pageSource.contains("error") || pageSource.contains("failed")) {
                    System.out.println("Erro no cadastro. Mensagem de erro na página: " +
                            driver.findElement(By.cssSelector(".error")).getText());
                }
            } catch (Exception e) {
                System.out.println("Erro ao enviar o cadastro: " + e.getMessage());
            }

        }
    }


