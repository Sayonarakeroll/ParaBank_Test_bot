package executar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Cadastro;
import pages.ResultadoCadastro;
import pages.ResultadoLogin;
import user.UserCadastro;
import user.UserLogin;

public class Parabank {
    private static final String URL = "https://parabank.parasoft.com/parabank/index.htm";

    public static void main(String[] args) {
        executarTesteLogin();
        executarTesteCadastro();
    }
    public static void executarTesteCadastro(){
        testeCadastroUsuarioValido();
        testeCadastroUsuarioInvalidos();
        testeCadastroUsuarioTodosCampoEmBranco();
        testeCadastroCampoNomeEmBranco();
        testeCadastroCampoSenhaApenasNumeros();
        testeCampoSenhaApenasLetras();
        testeComNomeEspacoEmBranco();
    }

    private static void testeCadastroUsuarioValido() {
        System.out.println("\n=== CENARIO 1: Cadastro usuario com dados Validos ===");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);
            String username = "user" + System.currentTimeMillis();

            ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
                    "Ana", "Souza", "Rua Felicidade", "São Paulo", "SP", "01234-567", "11999999999", "123-45-6789", username, "12345678");

            System.out.println("Resultado: " + (resultado.isSucesso() ? "PASSOU" : "FALHOU"));
            System.out.println("Mensagem: " + resultado.getMensagem());
        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            userCadastro.fecharNavegador();
        }
    }

    private static void testeCadastroUsuarioInvalidos() {
        System.out.println("\n=== CENÁRIO 2: Cadastro com usuário inválido (já existente) ===");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);

            String username = "john";

            ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
                    "John", "Doe", "Rua do amor", "São Paulo", "SP", "01234-567",
                    "11999999999", "123-45-6789", username, "12345678"
            );
            boolean resultadoEsperado = !resultado.isSucesso() &&
                    resultado.getMensagem().contains("already exists");

            System.out.println("Resultado: " + (resultadoEsperado ? "PASSOU " : "FALHOU "));
            System.out.println("Mensagem: " + resultado.getMensagem());

        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            userCadastro.fecharNavegador();
        }
    }

    private static void testeCadastroUsuarioTodosCampoEmBranco() {
        System.out.println("\n=== CENÁRIO 3: Cadastro com todos os campos em branco ===");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);

            ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
                    "", "", "", "", "", "", "", "", "", ""
            );

            boolean resultadoEsperado = !resultado.isSucesso();

            System.out.println("Resultado: " + (resultadoEsperado ? "PASSOU " : "FALHOU "));
            System.out.println("Mensagem: " + resultado.getMensagem());

        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            userCadastro.fecharNavegador();
        }
    }

    private static void testeCadastroCampoNomeEmBranco() {
        System.out.println("\n=== CENÁRIO 4: Cadastro com campo firstName em branco ===");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);

            String username = "user" + System.currentTimeMillis();

            ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
                    "", "Souza", "Rua Gozaga", "São Paulo", "SP", "01234-567",
                    "11999999999", "123-45-6789", username, "12345678"
            );

            boolean resultadoEsperado = !resultado.isSucesso() &&
                    resultado.getMensagem().contains("First name is required.");

            System.out.println("Resultado: " + (resultadoEsperado ? "PASSOU " : "FALHOU "));
            System.out.println("Mensagem: " + resultado.getMensagem());

        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            userCadastro.fecharNavegador();
        }
    }
    private static void testeCadastroCampoSenhaApenasNumeros(){
        System.out.println("\n ==== CENÁRIO 5: Cadastro usuário campo senha apenas com numeros ===");
        UserCadastro userCadastro = new UserCadastro();

        try{
            userCadastro.acessarSite(URL);

            String username = "user" + System.currentTimeMillis();
            String senhaFraca = "123456";
            ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
            "Eros", "Santos", " Rua Madalena", "são Paulo","SP", "01234-589",
                    "11888888888","123-45-6867", username, senhaFraca
            );

            boolean resultadoEsperado = !resultado.isSucesso() &&
                    (resultado.getMensagem().toLowerCase().contains("password") ||
                    resultado.getMensagem().toLowerCase().contains("senha"));
            System.out.println("Resultado: " +(resultadoEsperado ? "PASSOU" : "FALHOU"));
            System.out.println("Mensagem: " + resultado.getMensagem());
        } catch (Exception e){
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally{
            userCadastro.fecharNavegador();
        }
    }
    private static  void testeCampoSenhaApenasLetras(){
        System.out.println("\n === CENÁRIO 6: Campo senha apenas com letras");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);

            String username = " user" + System.currentTimeMillis();
            String senhaFraca1 = "abacaxi";
            ResultadoCadastro resultado = userCadastro.cadastrarUsuario("Marlon", "Silva", "Rua 05", "São Paulo", "SP",
            "01234-589", "11888888888","123-45-6867", username, senhaFraca1);

            boolean resultadoEsperado = !resultado.isSucesso() &&
                    (resultado.getMensagem().toLowerCase().contains("password") ||
                            resultado.getMensagem().toLowerCase().contains("senha"));

            System.out.println("Resultado: "+(resultadoEsperado ? "PASSOU" : " FALHOU"));
            System.out.println("Mensagem: " + resultado.getMensagem());
        } catch (Exception e){
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            userCadastro.fecharNavegador();
        }
    }
    private  static void testeComNomeEspacoEmBranco(){
        System.out.println("\n === CENÁRIO 7: Cadastro com Nome e espaços em brancos");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);

            String username = "user" + System.currentTimeMillis();
            String senha = "abacaxi";

            ResultadoCadastro resultado = userCadastro.cadastrarUsuario("Maria", " Santos ", "Rua Sinceridade", "São Paulo", "SP",
                    "01234-589", "11999999999", "01234-589", username, senha);
            boolean resultadoEsperado = !resultado.isSucesso() &&
                    (resultado.getMensagem().toLowerCase().contains("password") ||
                            resultado.getMensagem().toLowerCase().contains(senha));

            System.out.println("Resultado: " + (resultadoEsperado ? "PASSOU" : "FALHOU"));
            System.out.println("Mensagem " + resultado.getMensagem());

        } catch (Exception e){
            System.out.println("Erro durante a execução" + e.getMessage());
            e.printStackTrace();
        }finally {
            userCadastro.fecharNavegador();
        }
    }

    private  static void executarTesteLogin(){
        testeLoginDadosInexistente();
        testeLoginDadosValidos();
        testeLoginCampoUserBranco();
        testeCampoNomeEspacoEmBranco();
    }
    private static void testeLoginCampoUserBranco(){
        System.out.println("\n === CENÁRIO LOGIN 1: Login com dados validos ===");
        UserLogin userLogin = null;

        try {
            userLogin= new UserLogin();
            userLogin.acessarSite(URL);

           String username = "";
           String senha = "123456";

            ResultadoLogin resultado = userLogin.realizarLogin(username, senha);

            if (resultado.isSucesso()) {
              System.out.println("PASSOU " +resultado.getMensagem());
              Thread.sleep(1000);
              userLogin.logout();

              System.out.println("logout realizado para limpar sessão");
            } else {
                System.out.println("FALHOU " +resultado.getMensagem());
            }
        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (userLogin != null){
                userLogin.fecharNavegador();
            }

        }
    }
    private static void testeLoginDadosInexistente(){
        System.out.println("\n === CENÁRIO LOGIN 2: Login com dados inexistente ===");
        UserLogin userLogin = null;

        try {
            userLogin= new UserLogin();
            userLogin.acessarSite(URL);

            String username = "John"+ System.currentTimeMillis();
            String senha = "123456";

            ResultadoLogin resultado = userLogin.realizarLogin(username, senha);

            if (resultado.isSucesso()) {
                System.out.println("PASSOU " +resultado.getMensagem());
                Thread.sleep(1000);
                userLogin.logout();

                System.out.println("logout realizado para limpar sessão");
            } else {
                System.out.println("FALHOU " +resultado.getMensagem());
            }
        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (userLogin != null){
                userLogin.fecharNavegador();
            }

        }
    }
   private static  void testeLoginDadosValidos(){
        System.out.println("\n=== CENÁRIO 3: Login com dados validos===");
        UserCadastro userCadastro = null;
        UserLogin userLogin = null;

        try{
           System.out.println("\n=== Cadastrando Usuário===");
           userCadastro = new UserCadastro();
           userCadastro.acessarSite(URL);

           String usuario = "Love" + System.currentTimeMillis();
           String senha = "1234567";

           ResultadoCadastro cadastro = userCadastro.cadastrarUsuario("Love","Silva",
                   "Rua Brasil", "Rio de Janeiro", "RJ",
                   "12345-678","21999999999", "123456789", usuario, senha);
           if (cadastro.isSucesso()) {
               System.out.println("Cadastro realizado: " + cadastro.getMensagem());

               userLogin = new UserLogin();
               userLogin.acessarSite(URL);

               ResultadoLogin login = userLogin.realizarLogin(usuario, senha);

               if(login.isSucesso()) {
                   System.out.println(" Login realizado "+ login.getMensagem());

                   Thread.sleep(2000);
                   userLogin.logout();
                   System.out.println("Logout realizado");
               }else {
                   System.out.println("Falha no login: " + login.getMensagem());
               }
           }else {
               System.out.println(" Falha no cadastro: " + cadastro.getMensagem());
           }
        } catch (Exception e){
            System.out.println("erro" + e.getMessage());
            e.printStackTrace();
        }finally {
            if (userCadastro != null){
                userCadastro.fecharNavegador();
            }
        }
   }
   public static void testeCampoNomeEspacoEmBranco(){
        System.out.println("\n=== CENÁRIO LOGIN: ");
        UserLogin userLogin = null;

        try{
            userLogin = new UserLogin();
            userLogin.acessarSite(URL);

            String username = "user  ";
            String senha = "1234567";

            ResultadoLogin resultado = userLogin.realizarLogin(username,senha);

            if (resultado.isSucesso()){
                System.out.println("PASSOU " +resultado.getMensagem());
                Thread.sleep(1000);
                userLogin.logout();

                System.out.println(" Logout realizado");
                } else{
                    System.out.println("FALHOU " + resultado.getMensagem());
                }

        } catch (Exception e){
            System.out.println("Erro durante a execução " + e.getMessage());
            e.printStackTrace();
        }finally {
            if (userLogin != null){
                userLogin.fecharNavegador();
            }
        }
   }
}

