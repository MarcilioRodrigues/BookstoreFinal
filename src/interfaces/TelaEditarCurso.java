package interfaces;

import conexao.ConexaoMysql;
import util.JTextFieldTamanhoMaximo;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela edição dos dados do curso.<br>
 *
 * @author Marcilio-Pc
 */
public class TelaEditarCurso extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    Boolean cadastrado = false;
    String nomedocurso = "";

    /**
     * Este construtor inicializa os componentes de interface GUI, gerados
     * automaticamente, é inicializado também um método preencheCamposEditar que
     * é responsável em preencher cada componente de acordo com a opcao
     * selecionada na jtable do formulário de cadastro de cursos.
     *
     * @param codcurso
     */
    public TelaEditarCurso(String codcurso) {
        initComponents();
        setLimitTextFields();
        preencheCamposEditar(codcurso);
    }

     /** 
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields() {
        jtextfieldNomeCurso.setDocument(new JTextFieldTamanhoMaximo(30, true));
    }

    /**
     * O Método é responsável por preencher os componentes do formulário, com
     * base ao codigo do curso que foi informado via argumento no construtor
     *
     * @param codcurso String - Codigo do curso
     */
    public void preencheCamposEditar(String codcurso) {
        labelCodCursoResultado.setText(codcurso);
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbcurso where codcurso LIKE " + codcurso);
            conectmysql.resultset.first();
            nomedocurso = conectmysql.resultset.getString("nomecurso");
            jtextfieldNomeCurso.setText(nomedocurso);
            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Campos Editar: " + erro);

        }
    }

    /**
     * Este método é responsável por pegar as informações dos componentes do
     * formulário e atualiza-las no banco de dados.
     */
    public void editarCurso() {
        String sql = "UPDATE tbcurso set nomecurso = ? where codcurso = ?";
        try {
            cadastrado = false;
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbcurso");
            if (!jtextfieldNomeCurso.getText().equals(nomedocurso)) {
                while (conectmysql.resultset.next()) {
                    if (conectmysql.resultset.getString("nomecurso").equals(jtextfieldNomeCurso.getText())) {
                        JOptionPane.showMessageDialog(null, "O curso informado já está cadastrado", "Curso Existente!", JOptionPane.ERROR_MESSAGE);
                        cadastrado = true;
                    }
                }
            }

            if (cadastrado == false) {
                conectmysql.createStatement();
                conectmysql.prepareStatement(sql);
                conectmysql.preparestatement.setString(1, jtextfieldNomeCurso.getText());
                conectmysql.preparestatement.setString(2, labelCodCursoResultado.getText());
                conectmysql.preparestatement.executeUpdate();
                dispose();
                conectmysql.fecharConexao();
                JOptionPane.showMessageDialog(null, "Curso editado com sucesso, clique no botão ATUALIZAR", "Curso Editado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception erro) {
            System.out.println("Erro Editar Curso: " + erro);
        }
    }

    // Código gerado automaticamente pelo IDE
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jbuttonEditar = new javax.swing.JButton();
        jbuttonCancelar = new javax.swing.JButton();
        labelCodCurso = new javax.swing.JLabel();
        labelNomeCurso = new javax.swing.JLabel();
        jtextfieldNomeCurso = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelCodCursoResultado = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setClosable(true);

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editarok.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
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

        labelCodCurso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCodCurso.setText("Cod. Curso");

        labelNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeCurso.setText("Nome:");

        jtextfieldNomeCurso.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        labelHeader.setText(" EDITAR CURSO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelHeader)
                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelHeader)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelCodCursoResultado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCodCursoResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCodCursoResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNomeCurso)
                                    .addComponent(labelCodCurso)
                                    .addComponent(jtextfieldNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jbuttonCancelar)))
                        .addGap(0, 102, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(labelCodCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNomeCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        setBounds(400, 40, 482, 447);
    }// </editor-fold>//GEN-END:initComponents

    // Evento button Cancelar
    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    // Evento button Editar
    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        editarCurso();
    }//GEN-LAST:event_jbuttonEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JTextField jtextfieldNomeCurso;
    private javax.swing.JLabel labelCodCurso;
    private javax.swing.JLabel labelCodCursoResultado;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelNomeCurso;
    // End of variables declaration//GEN-END:variables
}
