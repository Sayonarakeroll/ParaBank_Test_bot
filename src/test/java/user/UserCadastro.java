package user;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Cadastro;

import java.time.Duration;

public class UserCadastro {
    private WebDriver driver;
    private Cadastro cadastro;

    public UserCadastro() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        cadastro = new Cadastro(driver);
    }

    public void acessarSite(String url) {
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));

        driver.findElement(By.linkText("Register")).click();
    }

    public void cadastaUsuario(String nome, String sobrenome, String endereco, String cidade,
                               String estado, String cep, String telefone, String ssn,
                               String usuario, String senha) {

        cadastro.preencherPrimeiroNome(nome);
        cadastro.preencherSeggundoNome(sobrenome);
        cadastro.preencherEndereco(endereco);
        cadastro.preencherCidade(cidade);
        cadastro.preencherEstado(estado);
        cadastro.preencherCep(cep);
        cadastro.preencherFone(telefone);
        cadastro.preencherSSN(ssn);
        cadastro.preencherUsuario(usuario);
        cadastro.preecherSenha(senha);
        cadastro.preencherRepetSenha(senha);

        System.out.println("Cadastrando Usuario");

        cadastro.enviarCadastro();

    }

}