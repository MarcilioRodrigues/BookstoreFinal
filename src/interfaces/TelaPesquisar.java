/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */

package interfaces;

import conexao.ConexaoMysql;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * Classe responsável por montar tela de pesquisa dos livros sugeridos
 * da ementa, essa tela disponibiliza quatro combobox que são eles:<br>
 *  - Selecionar Curso*<br>
 *  - Selecionar Disciplina*<br>
 *  - Selecionar Disciplina(Opcional)<br>
 *  - Selecionar Disciplina(Opcional)<br>
 * Essa tela tem por objetivo receber os dados da pesquisa para chamar
 * a telaLivrosSugeridos onde de fato será mostrado o resultado.
 * @author Kélvin Santiago
 */
public class TelaPesquisar extends javax.swing.JInternalFrame {

    TelaLogin telalogin = new TelaLogin();

    public static String resNomecurso = null;
    public static String resNomedisciplina = null;
    public static String resNomedisciplina02 = null;
    public static String resNomedisciplina03 = null;
 
    /**
     * Construtor inicia componentes básicos da interface gráfica.
    */ 
    public TelaPesquisar() {
        initComponents();
    }
    
    /**
     * Método implementa o preenchimento de um combobox passado como argumento,
     * com os dados da consulta SQL.
     * @param selectQuery String - Código sql,ex: SELECT * FROM nometabela
     * @param nomecoluna String - Nome da coluna da tabela
     * @param itemcombobox jcombobox - Componente Jcombobox a ser preenchido com as informações
     */
    public void preencheComboBox(String selectQuery, String nomecoluna, JComboBox itemcombobox) {
      
        try {
            ConexaoMysql conectmysql = new ConexaoMysql();
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL(selectQuery);

            itemcombobox.removeAllItems();

            while (conectmysql.resultset.next()) {
                itemcombobox.addItem(conectmysql.resultset.getString(nomecoluna));
            }

        } catch (Exception erro) {
            System.out.println("Erro na Tela Pesquisar: " + erro);
        }
    }

    // Método gerado automaticamente GUI 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdesktoppanelPesquisar = new javax.swing.JDesktopPane();
        buttonRemoveDisc02 = new javax.swing.JButton();
        buttonRemoveDisc03 = new javax.swing.JButton();
        jcomboboxCurso = new javax.swing.JComboBox();
        buttonAddDisc03 = new javax.swing.JButton();
        jcomboboxDisciplina03 = new javax.swing.JComboBox();
        labelDisciplina = new javax.swing.JLabel();
        labelCurso = new javax.swing.JLabel();
        jcomboboxDisciplina02 = new javax.swing.JComboBox();
        buttonAddDisc02 = new javax.swing.JButton();
        jbuttonCancelar = new javax.swing.JButton();
        jcomboboxDisciplina = new javax.swing.JComboBox();
        jbuttonAcessar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1277, 611));

        jdesktoppanelPesquisar.setBackground(new java.awt.Color(204, 204, 204));
        jdesktoppanelPesquisar.setPreferredSize(new java.awt.Dimension(1289, 577));

        buttonRemoveDisc02.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRemoveDisc02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/block.png"))); // NOI18N
        buttonRemoveDisc02.setText("Remover");
        buttonRemoveDisc02.setEnabled(false);
        buttonRemoveDisc02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveDisc02ActionPerformed(evt);
            }
        });

        buttonRemoveDisc03.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRemoveDisc03.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/block.png"))); // NOI18N
        buttonRemoveDisc03.setText("Remover");
        buttonRemoveDisc03.setEnabled(false);
        buttonRemoveDisc03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveDisc03ActionPerformed(evt);
            }
        });

        jcomboboxCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        preencheComboBox("select * from tbcurso", "nomecurso", jcomboboxCurso);
        jcomboboxCurso.setSelectedIndex(-1);
        jcomboboxCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboboxCursoActionPerformed(evt);
            }
        });

        buttonAddDisc03.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddDisc03.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        buttonAddDisc03.setText("Adicionar");
        buttonAddDisc03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddDisc03ActionPerformed(evt);
            }
        });

        jcomboboxDisciplina03.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jcomboboxDisciplina03.setModel(new javax.swing.DefaultComboBoxModel());
        jcomboboxDisciplina03.setSelectedIndex(-1);
        jcomboboxDisciplina03.setEnabled(false);

        labelDisciplina.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelDisciplina.setText("Selecione a Disciplina(s)");

        labelCurso.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelCurso.setText("Selecione o Curso");

        jcomboboxDisciplina02.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jcomboboxDisciplina02.setModel(new javax.swing.DefaultComboBoxModel());
        jcomboboxDisciplina02.setSelectedIndex(-1);
        jcomboboxDisciplina02.setEnabled(false);

        buttonAddDisc02.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddDisc02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        buttonAddDisc02.setText("Adicionar");
        buttonAddDisc02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddDisc02ActionPerformed(evt);
            }
        });

        jbuttonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jbuttonCancelar.setText("Sair");
        jbuttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarActionPerformed(evt);
            }
        });

        jcomboboxDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcomboboxDisciplina.setModel(new javax.swing.DefaultComboBoxModel());
        jcomboboxDisciplina.setEnabled(false);

        jbuttonAcessar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonAcessar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
        jbuttonAcessar.setText("Pesquisar");
        jbuttonAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonAcessarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setText("Pesquisa");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(labelTitulo)
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTitulo))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jdesktoppanelPesquisar.setLayer(buttonRemoveDisc02, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(buttonRemoveDisc03, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jcomboboxCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(buttonAddDisc03, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jcomboboxDisciplina03, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(labelDisciplina, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(labelCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jcomboboxDisciplina02, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(buttonAddDisc02, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jbuttonCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jcomboboxDisciplina, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jbuttonAcessar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdesktoppanelPesquisar.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdesktoppanelPesquisarLayout = new javax.swing.GroupLayout(jdesktoppanelPesquisar);
        jdesktoppanelPesquisar.setLayout(jdesktoppanelPesquisarLayout);
        jdesktoppanelPesquisarLayout.setHorizontalGroup(
            jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdesktoppanelPesquisarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCurso)
                    .addComponent(jcomboboxDisciplina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcomboboxDisciplina02, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDisciplina)
                    .addGroup(jdesktoppanelPesquisarLayout.createSequentialGroup()
                        .addComponent(jbuttonAcessar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcomboboxDisciplina03, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcomboboxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonAddDisc02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddDisc03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRemoveDisc02)
                    .addComponent(buttonRemoveDisc03))
                .addGap(447, 447, 447))
            .addGroup(jdesktoppanelPesquisarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jdesktoppanelPesquisarLayout.setVerticalGroup(
            jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdesktoppanelPesquisarLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdesktoppanelPesquisarLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(labelCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcomboboxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(labelDisciplina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcomboboxDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcomboboxDisciplina02, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAddDisc02, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcomboboxDisciplina03, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAddDisc03, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(jdesktoppanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonAcessar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(jdesktoppanelPesquisarLayout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(buttonRemoveDisc02, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRemoveDisc03, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdesktoppanelPesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdesktoppanelPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        setBounds(0, 0, 690, 611);
    }// </editor-fold>//GEN-END:initComponents

    private void jcomboboxCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxCursoActionPerformed
        // Ao clicar ele seta como editavel
        jcomboboxDisciplina.setEnabled(true);
        // Setando conteudo comboboxindex na variavel
        resNomecurso = ((String) jcomboboxCurso.getSelectedItem());
        resNomedisciplina = ((String) jcomboboxDisciplina.getSelectedItem());

        // chamando metodo pra preencher comboboxDisciplina
        preencheComboBox("SELECT * FROM tbdisciplina INNER JOIN tbcurso ON tbdisciplina.codcurso = tbcurso.codcurso"
            + " AND tbcurso.nomecurso LIKE '" + resNomecurso + "'", "nomedisciplina", jcomboboxDisciplina);
        jcomboboxDisciplina.setSelectedIndex(-1);

        // chamando metodo pra peencher comboboxDisciplina02
        preencheComboBox("SELECT * FROM tbdisciplina INNER JOIN tbcurso ON tbdisciplina.codcurso = tbcurso.codcurso"
            + " AND tbcurso.nomecurso LIKE '" + resNomecurso + "'", "nomedisciplina", jcomboboxDisciplina02);
        jcomboboxDisciplina02.setSelectedIndex(-1);

        // chamando metodo pra peencher comboboxDisciplina03
        preencheComboBox("SELECT * FROM tbdisciplina INNER JOIN tbcurso ON tbdisciplina.codcurso = tbcurso.codcurso"
            + " AND tbcurso.nomecurso LIKE '" + resNomecurso + "'", "nomedisciplina", jcomboboxDisciplina03);
        jcomboboxDisciplina03.setSelectedIndex(-1);
    }//GEN-LAST:event_jcomboboxCursoActionPerformed

    private void buttonAddDisc02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDisc02ActionPerformed
        jcomboboxDisciplina02.setEnabled(true);
        buttonRemoveDisc02.setEnabled(true);
        buttonAddDisc02.setEnabled(false);

    }//GEN-LAST:event_buttonAddDisc02ActionPerformed

    private void buttonRemoveDisc02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveDisc02ActionPerformed
        jcomboboxDisciplina02.setEnabled(false);
        jcomboboxDisciplina02.setSelectedIndex(-1);
        resNomedisciplina02 = null;
        buttonAddDisc02.setEnabled(true);
        buttonRemoveDisc02.setEnabled(false);
    }//GEN-LAST:event_buttonRemoveDisc02ActionPerformed

    private void buttonRemoveDisc03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveDisc03ActionPerformed
        jcomboboxDisciplina03.setEnabled(false);
        jcomboboxDisciplina03.setSelectedIndex(-1);
        resNomedisciplina03 = null;
        buttonAddDisc03.setEnabled(true);
        buttonRemoveDisc03.setEnabled(false);
    }//GEN-LAST:event_buttonRemoveDisc03ActionPerformed

    private void buttonAddDisc03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDisc03ActionPerformed
        jcomboboxDisciplina03.setEnabled(true);
        buttonRemoveDisc03.setEnabled(true);
        buttonAddDisc03.setEnabled(false);
    }//GEN-LAST:event_buttonAddDisc03ActionPerformed

    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
       dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    private void jbuttonAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonAcessarActionPerformed
        resNomecurso = ((String) jcomboboxCurso.getSelectedItem());
        resNomedisciplina = ((String) jcomboboxDisciplina.getSelectedItem());
        resNomedisciplina02 = ((String) jcomboboxDisciplina02.getSelectedItem());
        resNomedisciplina03 = ((String) jcomboboxDisciplina03.getSelectedItem());
        if (resNomecurso == null || resNomedisciplina == null && resNomedisciplina02 == null && resNomedisciplina03 == null) {
            JOptionPane.showMessageDialog(null, "O Curso deve ser selecionado, juntamente com a disciplina!", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            TelaLivrosSugeridos telalivrossugeridos = new TelaLivrosSugeridos();
            jdesktoppanelPesquisar.add(telalivrossugeridos);
            telalivrossugeridos.setVisible(true);
        }
    }//GEN-LAST:event_jbuttonAcessarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddDisc02;
    private javax.swing.JButton buttonAddDisc03;
    private javax.swing.JButton buttonRemoveDisc02;
    private javax.swing.JButton buttonRemoveDisc03;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbuttonAcessar;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JComboBox jcomboboxCurso;
    private javax.swing.JComboBox jcomboboxDisciplina;
    private javax.swing.JComboBox jcomboboxDisciplina02;
    private javax.swing.JComboBox jcomboboxDisciplina03;
    private javax.swing.JDesktopPane jdesktoppanelPesquisar;
    private javax.swing.JLabel labelCurso;
    private javax.swing.JLabel labelDisciplina;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables

}
