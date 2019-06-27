package interfaces;

import conexao.ConexaoMysql;
import util.JTextFieldTamanhoMaximo;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela edição dos dados do usuário<br>
 *
 * @author Marcilio-Pc
 */
public class TelaEditarUsuario extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    Boolean cadastrado = false;
    String tipouser = "";
    String loginunico = "";

    /**
     * Este construtor inicializa os componentes de interface GUI, gerados
     * automaticamente, é inicializado também um método preencheCamposEditar que
     * é responsável em preencher cada componente de acordo com a opcao
     * selecionada na jtable do formulário de cadastro de usuários.
     *
     * @param coduser
     */
    public TelaEditarUsuario(String coduser) {
        initComponents();
        setLimitTextFields();
        preencheCamposEditar(coduser);
    }

    /**
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields() {
        jtextfieldNome.setDocument(new JTextFieldTamanhoMaximo(40));
        jtextfieldLoginUnico.setDocument(new JTextFieldTamanhoMaximo(20));
        jpasswordfieldSenha.setDocument(new JTextFieldTamanhoMaximo(20));
    }

    /**
     * O Método é responsável por preencher os componentes do formulário, com
     * base ao codigo do usuário que foi informado via argumento no construtor
     *
     * @param coduser String - Codigo do usuário
     */
    public void preencheCamposEditar(String coduser) {
        labelMatriculaCod.setText(coduser);
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbuser where matriculauser LIKE " + coduser);
            conectmysql.resultset.first();
            jtextfieldNome.setText(conectmysql.resultset.getString("nomeuser"));
            jtextfieldLoginUnico.setText(conectmysql.resultset.getString("loginunico"));
            jpasswordfieldSenha.setText(conectmysql.resultset.getString("senhauser"));
            tipouser = conectmysql.resultset.getString("permissaouser");
            loginunico = conectmysql.resultset.getString("loginunico");
            switch (tipouser) {
                case "Aluno":
                    jradiobuttonAluno.setSelected(true);
                    jradiobuttonProfessor.setSelected(false);
                    jradiobuttonAdministrador.setSelected(false);

                    break;
                case "Professor":
                    jradiobuttonProfessor.setSelected(true);
                    jradiobuttonAluno.setSelected(false);
                    jradiobuttonAdministrador.setSelected(false);
                    break;
                default:
                    jradiobuttonAdministrador.setSelected(true);
                    jradiobuttonAluno.setSelected(false);
                    jradiobuttonProfessor.setSelected(false);
                    break;
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Campos Editar: " + erro);

        }
    }

    /**
     * Este método é responsável por pegar as informações dos componentes do
     * formulário e atualiza-las no banco de dados.
     */
    public void editarUser() {
        String sql = "UPDATE tbuser set loginunico = ?,nomeuser = ?, senhauser = ?, permissaouser = ? where matriculauser = ?";
        try {
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbuser");
            if (!jtextfieldLoginUnico.getText().equals(loginunico)) {
                while (conectmysql.resultset.next()) {
                    if (conectmysql.resultset.getString("loginunico").equals(jtextfieldLoginUnico.getText())) {
                        JOptionPane.showMessageDialog(null, "Este login único já está cadastrado", "Login Único Existente!", JOptionPane.ERROR_MESSAGE);
                        cadastrado = true;
                    }
                }
            }
            if (cadastrado == false) {
                conectmysql.prepareStatement(sql);
                conectmysql.preparestatement.setString(1, jtextfieldLoginUnico.getText());
                conectmysql.preparestatement.setString(2, jtextfieldNome.getText());
                conectmysql.preparestatement.setString(3, jpasswordfieldSenha.getText());
                conectmysql.preparestatement.setString(4, tipouser);
                conectmysql.preparestatement.setString(5, labelMatriculaCod.getText());
                conectmysql.preparestatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso, clique no botão ATUALIZAR", "Usuário Editado", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            cadastrado = false;
            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro Editar Usuario: " + erro);
        }
    }

    //Código gerado automaticamente pelo IDE
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jbuttonEditar = new javax.swing.JButton();
        jbuttonCancelar = new javax.swing.JButton();
        labelMatricula = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        jtextfieldNome = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelMatriculaCod = new javax.swing.JLabel();
        jradiobuttonAluno = new javax.swing.JRadioButton();
        jradiobuttonProfessor = new javax.swing.JRadioButton();
        jradiobuttonAdministrador = new javax.swing.JRadioButton();
        labelTipoUsuario = new javax.swing.JLabel();
        jpasswordfieldSenha = new javax.swing.JPasswordField();
        jtextfieldPassword = new javax.swing.JTextField();
        jbuttonMostrarSenha = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtextfieldLoginUnico = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setClosable(true);
        getContentPane().setLayout(null);

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editarok.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
            }
        });
        getContentPane().add(jbuttonEditar);
        jbuttonEditar.setBounds(110, 400, 120, 50);

        jbuttonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jbuttonCancelar.setText("Cancelar");
        jbuttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jbuttonCancelar);
        jbuttonCancelar.setBounds(310, 400, 130, 50);

        labelMatricula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMatricula.setText("Matrícula:");
        getContentPane().add(labelMatricula);
        labelMatricula.setBounds(90, 130, 76, 22);

        labelNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNome.setText("Nome:");
        getContentPane().add(labelNome);
        labelNome.setBounds(90, 180, 52, 22);

        jtextfieldNome.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jtextfieldNome);
        jtextfieldNome.setBounds(218, 171, 200, 35);

        labelSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSenha.setText("Senha:");
        getContentPane().add(labelSenha);
        labelSenha.setBounds(90, 280, 54, 22);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        labelHeader.setText(" EDITAR USUÁRIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
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

        getContentPane().add(jPanel1);
        jPanel1.setBounds(12, 13, 501, 78);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelMatriculaCod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMatriculaCod, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMatriculaCod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(218, 121, 198, 35);

        jradiobuttonAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonAluno.setText("Aluno");
        jradiobuttonAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(jradiobuttonAluno);
        jradiobuttonAluno.setBounds(140, 350, 59, 25);

        jradiobuttonProfessor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonProfessor.setText("Professor");
        jradiobuttonProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonProfessorActionPerformed(evt);
            }
        });
        getContentPane().add(jradiobuttonProfessor);
        jradiobuttonProfessor.setBounds(220, 350, 83, 25);

        jradiobuttonAdministrador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonAdministrador.setText("Administrador");
        jradiobuttonAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonAdministradorActionPerformed(evt);
            }
        });
        getContentPane().add(jradiobuttonAdministrador);
        jradiobuttonAdministrador.setBounds(310, 350, 109, 25);

        labelTipoUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTipoUsuario.setText("Tipo de usuário:");
        getContentPane().add(labelTipoUsuario);
        labelTipoUsuario.setBounds(220, 320, 129, 22);

        jpasswordfieldSenha.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(jpasswordfieldSenha);
        jpasswordfieldSenha.setBounds(220, 270, 200, 35);

        jtextfieldPassword.setVisible(false);
        jtextfieldPassword.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(jtextfieldPassword);
        jtextfieldPassword.setBounds(220, 270, 200, 35);

        jbuttonMostrarSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/desbloquear.png"))); // NOI18N
        jbuttonMostrarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbuttonMostrarSenhaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jbuttonMostrarSenhaMouseReleased(evt);
            }
        });
        getContentPane().add(jbuttonMostrarSenha);
        jbuttonMostrarSenha.setBounds(430, 270, 40, 35);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Login Unico:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 230, 120, 24);

        jtextfieldLoginUnico.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(jtextfieldLoginUnico);
        jtextfieldLoginUnico.setBounds(220, 220, 200, 35);

        setBounds(400, 40, 541, 508);
    }// </editor-fold>//GEN-END:initComponents

    // Evento button Cancelar
    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    // Evento button Editar
    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        if (jtextfieldNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor insira um nome!", "Nome em Branco", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldLoginUnico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor insira um Login unico!", "Login Unico em Branco", JOptionPane.ERROR_MESSAGE);
        } else if (jpasswordfieldSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor insira uma senha!", "Senha em Branco", JOptionPane.ERROR_MESSAGE);
        } else {
            editarUser();
        }
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    // Evento radio Aluno
    private void jradiobuttonAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonAlunoActionPerformed
        jradiobuttonAluno.setSelected(true);
        jradiobuttonProfessor.setSelected(false);
        jradiobuttonAdministrador.setSelected(false);
        tipouser = jradiobuttonAluno.getText();
    }//GEN-LAST:event_jradiobuttonAlunoActionPerformed

    // Evento radio Professor
    private void jradiobuttonProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonProfessorActionPerformed
        jradiobuttonAluno.setSelected(false);
        jradiobuttonProfessor.setSelected(true);
        jradiobuttonAdministrador.setSelected(false);
        tipouser = jradiobuttonProfessor.getText();
    }//GEN-LAST:event_jradiobuttonProfessorActionPerformed

    // Evento radio Administrador
    private void jradiobuttonAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonAdministradorActionPerformed
        jradiobuttonAluno.setSelected(false);
        jradiobuttonProfessor.setSelected(false);
        jradiobuttonAdministrador.setSelected(true);
        tipouser = jradiobuttonAdministrador.getText();
    }//GEN-LAST:event_jradiobuttonAdministradorActionPerformed

    // Evento button Ocultar Senha ao soltar Clique
    private void jbuttonMostrarSenhaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbuttonMostrarSenhaMouseReleased
        jpasswordfieldSenha.setVisible(true);
        jpasswordfieldSenha.setText(jtextfieldPassword.getText());
        jtextfieldPassword.setVisible(false);
    }//GEN-LAST:event_jbuttonMostrarSenhaMouseReleased

    // Evento button Mostrar Senha ao Pressionar
    private void jbuttonMostrarSenhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbuttonMostrarSenhaMousePressed
        jtextfieldPassword.setVisible(true);
        jtextfieldPassword.setText(jpasswordfieldSenha.getText());
        jpasswordfieldSenha.setVisible(false);
    }//GEN-LAST:event_jbuttonMostrarSenhaMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JButton jbuttonMostrarSenha;
    private javax.swing.JPasswordField jpasswordfieldSenha;
    private javax.swing.JRadioButton jradiobuttonAdministrador;
    private javax.swing.JRadioButton jradiobuttonAluno;
    private javax.swing.JRadioButton jradiobuttonProfessor;
    private javax.swing.JTextField jtextfieldLoginUnico;
    private javax.swing.JTextField jtextfieldNome;
    private javax.swing.JTextField jtextfieldPassword;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelMatriculaCod;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTipoUsuario;
    // End of variables declaration//GEN-END:variables
}
