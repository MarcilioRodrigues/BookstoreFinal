package interfaces;

import conexao.ConexaoMysql;
import util.JTextFieldTamanhoMaximo;
import javax.swing.JOptionPane;

/**
 * Classe da tela de alterar senha do usuário logado no sistema,
 * essa classe estende da classe JInternalFrame que é nossa frame interna.
 */
public class TelaAlterarSenha extends javax.swing.JInternalFrame {

    TelaLogin telalogin = new TelaLogin();

    /**
     * Contrutor da classe que inicia os componentes de tela.
     */
    public TelaAlterarSenha() {
        initComponents();
        setLimitTextFields();
    }
    
    /** 
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields(){
        jpasswordSenhaAntiga.setDocument(new JTextFieldTamanhoMaximo(20));
        jpasswordNovaSenha.setDocument(new JTextFieldTamanhoMaximo(20));
        jpasswordConfirmNovaSenha.setDocument(new JTextFieldTamanhoMaximo(20));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbuttonAlterar = new javax.swing.JButton();
        jbuttonCancelar = new javax.swing.JButton();
        labelNomeUsuario = new javax.swing.JLabel();
        jbuttonLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jpasswordSenhaAntiga = new javax.swing.JPasswordField();
        labelSenhaAntiga = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jpasswordConfirmNovaSenha = new javax.swing.JPasswordField();
        jpasswordNovaSenha = new javax.swing.JPasswordField();
        labelNovaSenha = new javax.swing.JLabel();
        labelConfirmNovaSenha = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jlabelTitulo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(585, 508));

        jbuttonAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/accept.png"))); // NOI18N
        jbuttonAlterar.setText("Alterar");
        jbuttonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonAlterarActionPerformed(evt);
            }
        });

        jbuttonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jbuttonCancelar.setText("Cancelar");
        jbuttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarActionPerformed(evt);
            }
        });

        labelNomeUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeUsuario.setText("Nome Usuário / Matrícula:");

        jbuttonLimpar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar.png"))); // NOI18N
        jbuttonLimpar.setText("Limpar");
        jbuttonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonLimparActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText(telalogin.nomelogado+" - "+telalogin.matriculalogado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jpasswordSenhaAntiga.setBackground(new java.awt.Color(255, 255, 204));

        labelSenhaAntiga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSenhaAntiga.setText("Senha Antiga:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelSenhaAntiga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpasswordSenhaAntiga, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpasswordSenhaAntiga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenhaAntiga))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jpasswordConfirmNovaSenha.setBackground(new java.awt.Color(255, 255, 204));

        jpasswordNovaSenha.setBackground(new java.awt.Color(255, 255, 204));

        labelNovaSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNovaSenha.setText("Nova Senha:");

        labelConfirmNovaSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelConfirmNovaSenha.setText("Confirmar Nova Senha:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelConfirmNovaSenha)
                    .addComponent(labelNovaSenha))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jpasswordConfirmNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jpasswordNovaSenha))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelNovaSenha)
                .addGap(18, 18, 18)
                .addComponent(labelConfirmNovaSenha)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpasswordNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpasswordConfirmNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpasswordNovaSenha.getAccessibleContext().setAccessibleName("");

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlabelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/chave.png"))); // NOI18N
        jlabelTitulo.setText("Alteração de Senha");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlabelTitulo)
                .addGap(155, 155, 155))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jlabelTitulo)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbuttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbuttonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNomeUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNomeUsuario)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        setBounds(300, 20, 639, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    private void jbuttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonLimparActionPerformed
        jpasswordConfirmNovaSenha.setText(null);
        jpasswordNovaSenha.setText(null);
        jpasswordSenhaAntiga.setText(null);
    }//GEN-LAST:event_jbuttonLimparActionPerformed

    private void jbuttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonAlterarActionPerformed
        if (jpasswordSenhaAntiga.getText().equals(TelaLogin.senhalogado)) {
            if (!jpasswordNovaSenha.getText().equals(jpasswordConfirmNovaSenha.getText())) {
                JOptionPane.showMessageDialog(null, "As senhas informadas não são iguais!", "Erro ao Alterar Senha", JOptionPane.ERROR_MESSAGE);
                jpasswordConfirmNovaSenha.setText(null);
                jpasswordNovaSenha.setText(null);
            } else if (jpasswordNovaSenha.getText().equals("") && jpasswordConfirmNovaSenha.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "As senhas não podem ser em branco", "Erro ao Alterar Senha", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    ConexaoMysql conectmysql = new ConexaoMysql();
                    conectmysql.abrirConexao();
                    conectmysql.createStatement();

                    conectmysql.statement.executeUpdate("UPDATE tbuser SET senhauser = " + "'" + jpasswordConfirmNovaSenha.getText() + "'" + " WHERE nomeuser = " + "'" + telalogin.nomelogado + "'");

                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!", "Senha Alterada", JOptionPane.INFORMATION_MESSAGE);
                    telalogin.senhalogado = jpasswordConfirmNovaSenha.getText();
                    dispose();

                } catch (Exception erro) {
                    System.out.println("Exceção ao Alterar Senha: " + erro);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senha antiga Inválida!");
        }
    }//GEN-LAST:event_jbuttonAlterarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbuttonAlterar;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonLimpar;
    private javax.swing.JLabel jlabelTitulo;
    private javax.swing.JPasswordField jpasswordConfirmNovaSenha;
    private javax.swing.JPasswordField jpasswordNovaSenha;
    private javax.swing.JPasswordField jpasswordSenhaAntiga;
    private javax.swing.JLabel labelConfirmNovaSenha;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelNovaSenha;
    private javax.swing.JLabel labelSenhaAntiga;
    // End of variables declaration//GEN-END:variables
}
