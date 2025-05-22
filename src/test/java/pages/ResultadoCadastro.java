package pages;

public class ResultadoCadastro {
        private boolean sucesso;
        private String mensagem;

        public ResultadoCadastro(boolean sucesso, String mensagem) {
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
            return "ResultadoCadastro{" +
                    "sucesso=" + sucesso +
                    ", mensagem='" + mensagem + '\'' +
                    '}';
        }
    }
