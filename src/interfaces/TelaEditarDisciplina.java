package interfaces;

import conexao.ConexaoMysql;
import util.JTextFieldTamanhoMaximo;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela edição dos dados da disciplina.<br>
 *
 * @author Marcilio-Pc
 */
public class TelaEditarDisciplina extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    TelaPesquisar telapesquisar = new TelaPesquisar();
    Boolean cadastrado = false;
    String nomedadisciplina = "";

    /**
     * Este construtor inicializa os componentes de interface GUI, gerados
     * automaticamente, é inicializado também um método preencheCamposEditar que
     * é responsável em preencher cada componente de acordo com a opcao
     * selecionada na jtable do formulário de cadastro de disciplinas.
     *
     * @param coddisciplina 
     */
    public TelaEditarDisciplina(String coddisciplina) {
        initComponents();
        telapesquisar.preencheComboBox("select * from tbcurso", "nomecurso", jcomboboxCursos);
        setLimitTextFields();
        preencheCamposEditar(coddisciplina);
    }

     /** 
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields(){
        jtextfieldNomeDisciplina.setDocument(new JTextFieldTamanhoMaximo(35,true));
    }
    
     /**
     * O Método é responsável por preencher os componentes do formulário, com
     * base ao codigo da disciplina que foi informado via argumento no construtor
     *
     * @param coddisciplina
     */
    public void preencheCamposEditar(String coddisciplina) {

        String sql = "SELECT * FROM tbdisciplina INNER JOIN tbcurso ON tbdisciplina.codcurso = tbcurso.codcurso AND tbdisciplina.coddisciplina = " + coddisciplina;
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL(sql);
            conectmysql.resultset.first();
            nomedadisciplina = conectmysql.resultset.getString("nomedisciplina");
            jtextfieldNomeDisciplina.setText(nomedadisciplina);
            labelCodDisciplinaResultado.setText(coddisciplina);
            labelCodCursoResultado.setText(conectmysql.resultset.getString("codcurso"));
            jcomboboxCursos.setSelectedItem(conectmysql.resultset.getString("nomecurso"));
            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Campos Editar: " + erro);

        }
    }

     /**
     * Este método é responsável por pegar as informações dos componentes do
     * formulário e atualiza-las no banco de dados.
     */
    public void editarDisciplina() {
        String sql = "UPDATE tbdisciplina set codcurso = ?, nomedisciplina = ? where coddisciplina = ?";
        try {
            cadastrado = false;
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbdisciplina");
            if (!jtextfieldNomeDisciplina.getText().equals(nomedadisciplina)) {
                while (conectmysql.resultset.next()) {
                    if (conectmysql.resultset.getString("nomedisciplina").equals(jtextfieldNomeDisciplina.getText())) {
                        JOptionPane.showMessageDialog(null, "A disciplina informada já está cadastrada", "Disciplina Existente!", JOptionPane.ERROR_MESSAGE);
                        cadastrado = true;
                    }
                }
            }

            if (cadastrado == false) {
                conectmysql.createStatement();
                conectmysql.executaSQL("SELECT * FROM tbcurso WHERE nomecurso LIKE " + "'" + jcomboboxCursos.getSelectedItem() + "'");
                conectmysql.resultset.first();
                String codcurso = conectmysql.resultset.getString("codcurso");
                conectmysql.createStatement();
                conectmysql.prepareStatement(sql);
                conectmysql.preparestatement.setString(1, codcurso);
                conectmysql.preparestatement.setString(2, jtextfieldNomeDisciplina.getText());
                conectmysql.preparestatement.setString(3, labelCodDisciplinaResultado.getText());
                conectmysql.preparestatement.executeUpdate();
                dispose();

                JOptionPane.showMessageDialog(null, "Disciplina editada com sucesso, clique no botão ATUALIZAR", "Disciplina Editada", JOptionPane.INFORMATION_MESSAGE);
            }
            conectmysql.fecharConexao();
        } catch (Exception erro) {
            System.out.println("Erro Editar Disciplina: " + erro);
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
        jtextfieldNomeDisciplina = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelCodCursoResultado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelCodDisciplinaResultado = new javax.swing.JLabel();
        labelCursos = new javax.swing.JLabel();
        jcomboboxCursos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

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
        labelCodCurso.setText("Cod. Disciplina");

        labelNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeCurso.setText("Nome da disciplina:");

        jtextfieldNomeDisciplina.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        labelHeader.setText("EDITAR DISCIPLINA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelHeader)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelHeader)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelCodCursoResultado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodCursoResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCodCursoResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        labelCodDisciplinaResultado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodDisciplinaResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCodDisciplinaResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        labelCursos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCursos.setText("Cursos");

        jcomboboxCursos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcomboboxCursos.setModel(new javax.swing.DefaultComboBoxModel());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Cod. Curso");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jcomboboxCursos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCodCurso)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelCursos))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(labelNomeCurso)
                        .addComponent(jtextfieldNomeDisciplina))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jbuttonCancelar)))
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodCurso)
                    .addComponent(jLabel1))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNomeCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtextfieldNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        setBounds(400, 40, 557, 526);
    }// </editor-fold>//GEN-END:initComponents

    // Evento button Cancelar
    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    // Evento button Editar
    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        if (!jtextfieldNomeDisciplina.getText().equals("")) {
            editarDisciplina();
        } else {
            JOptionPane.showMessageDialog(null, "Campo nome disciplina não pode ser vazio!", "Nome disciplina em Branco", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JComboBox jcomboboxCursos;
    private javax.swing.JTextField jtextfieldNomeDisciplina;
    private javax.swing.JLabel labelCodCurso;
    private javax.swing.JLabel labelCodCursoResultado;
    private javax.swing.JLabel labelCodDisciplinaResultado;
    private javax.swing.JLabel labelCursos;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelNomeCurso;
    // End of variables declaration//GEN-END:variables

    private void jtextfieldNomeDisciplinasetDocument(JTextFieldTamanhoMaximo jTextFieldTamanhoMaximo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
