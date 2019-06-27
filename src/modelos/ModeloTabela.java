package modelos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Classe implementa o modelo de tabela que é usado no programa como output dos
 * dados extraidos da consulta do banco de dados.
 *
 * @author Marcilio-Pc
 */
public class ModeloTabela extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;

    /**
     * Construtor seta a quantidade de linhas e colunas.
     * @param linhas ArrayList 
     * @param colunas String[]
     */
    public ModeloTabela(ArrayList linhas, String[] colunas) {
        setLinhas(linhas);
        setColunas(colunas);
    }
    
    /**
     * Método retorna a contagem do número de linhas
     * @return linhas.size int
     */
    public int getRowCount() {
        return linhas.size();
    }
    /**
     * Método retorna a contagem do número de colunas
     * @return colunas.lenght int
     */
    public int getColumnCount() {
        return colunas.length;
    }
    /**
     * Método retorna o nome na posição da coluna
     * @param numColuna int - Numero da coluna
     * @return colunas[numColuna] String
     */
    public String getColumnName(int numColuna) {
        return colunas[numColuna];
    }
    /**
     * Método retorna um objeto 
     * @param numLinha int
     * @param numColuna int
     * @return clinha[numColuna] Object
     */
    public Object getValueAt(int numLinha, int numColuna) {
        Object[] linha = (Object[]) getLinhas().get(numLinha);
        return linha[numColuna];
    }
    
    /**
     * Método retorna uma arrayList com as linhas
     * @return colunas[numColuna] ArrayList
     */
    public ArrayList getLinhas() {
        return linhas;
    }

    /**
     * Método seta a ArrayList 
     * @param dados ArrayList
     */
    public void setLinhas(ArrayList dados) {
        linhas = dados;
    }
    /**
     * Método retorna um vetorString[] com as colunas
     * @return String[]
     */
    public String[] getColunas() {
        return colunas;
    }
    /**
     * Método seta as colunas passando como parametro uma arrayString[]
     * @param colunas String[]
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

}
