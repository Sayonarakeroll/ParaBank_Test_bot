package pages;

public class ResultadoLogin {
    private boolean sucesso;
    private String mensagem;

    public ResultadoLogin(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }
    @Override
    public String toString() {
        return "Resultadologin {" +
                "sucesso=" +sucesso +", mensagem='" + mensagem + '\'' +
                '}';
    }
}
