package executar;

import pages.Cadastro;
import pages.ResultadoCadastro;
import user.UserCadastro;
import user.UserLogin;

public class Parabank {
    private static final String URL = "https://parabank.parasoft.com/parabank/index.htm";

    public static void main(String[] args) {
        testeCadastroUsuarioValido();
        testeCadastroUsuarioInvalidos();
        testeCadastroUsuarioTodosCampoEmBranco();
        testeCadastroCampoNomeEmBranco();
        //testeCadastroLogin();
    }
    private static  void testeCadastroUsuarioValido() {
        System.out.println("\n=== CENARIO 1: Cadastro usuario com dados Validos ===");
        UserCadastro userCadastro = new UserCadastro();

        try {
            userCadastro.acessarSite(URL);
            String username = "user" + System.currentTimeMillis();

            ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
                    "Ana", "Souza", "Rua Felicidade", "São Paulo", "SP", "01234-567", "11999999999", "123-45-6789", username, "12345678");

            System.out.println("Resultado: "+ (resultado.isSucesso() ? "PASSOU" : "FALHOU"));
            System.out.println("Mensagem: " + resultado.getMensagem());
        } catch (Exception e) {
            System.out.println("Erro durante a execução: "+ e.getMessage());
            e.printStackTrace();
        }}
        private static void testeCadastroUsuarioInvalidos() {
            System.out.println("\n=== CENÁRIO 2: Cadastro com usuário inválido (já existente) ===");
            UserCadastro userCadastro = new UserCadastro();

            try {
                userCadastro.acessarSite(URL);

                String username = "john"; // Username comum que provavelmente já existe

                ResultadoCadastro resultado = userCadastro.cadastrarUsuario(
                        "John", "Doe", "Rua ABC", "São Paulo", "SP", "01234-567",
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
                //userCadastro.fecharNavegador();
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
                        "", "Souza", "Rua XPTO", "São Paulo", "SP", "01234-567",
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


    }

