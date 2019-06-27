package util;

import conexao.ConexaoMysql;
import interfaces.TelaLogin;

/** Esta é a classe principal do projeto que inicializa a janela de Login, contendo 
 * apenas a classe main.
 * @author Marcilio-Pc
 */
public class Principal {
    public static void main(String[] args){
        TelaLogin telalogin = new TelaLogin();
        telalogin.setVisible(true);
        
    }
}
