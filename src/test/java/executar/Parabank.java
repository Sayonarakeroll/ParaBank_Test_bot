package executar;

import pages.Cadastro;
import user.UserCadastro;
import user.UserLogin;

public class Parabank {
    public static void main(String []args) {
        try {
            UserCadastro userCadastro = new UserCadastro();

            userCadastro.acessarSite("https://parabank.parasoft.com/parabank/index.htm");

            userCadastro.cadastaUsuario("Ana", "Souza", "Rua XPTO", "São Paulo", "SP", "01234-567",
                    "11999999999", "123-45-6789", "ana", "12345678");

        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
