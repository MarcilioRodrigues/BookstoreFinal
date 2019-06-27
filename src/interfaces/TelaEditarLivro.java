package interfaces;

import conexao.ConexaoMysql;
import util.JTextFieldTamanhoMaximo;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela edição dos dados do livro.<br>
 *
 * @author Marcilio-Pc
 */
public class TelaEditarLivro extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    TelaPesquisar telapesquisar = new TelaPesquisar();
    Boolean cadastrado = false;
    String nomedolivro = "";
    String localbiblioteca = "";

    /**
     * Este construtor inicializa os componentes de interface GUI, gerados
     * automaticamente, é inicializado também um método preencheCamposEditar que
     * é responsável em preencher cada componente de acordo com a opcao
     * selecionada na jtable do formulário de cadastro de livros.
     *
     * @param codlivro
     */
    public TelaEditarLivro(String codlivro) {
        initComponents();
        telapesquisar.preencheComboBox("select * from tbdisciplina", "nomedisciplina", jcomboboxDisciplinas);
        setLimitTextFields();
        preencheCamposEditar(codlivro);
    }

    /**
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields() {
        jtextfieldNomeLivro.setDocument(new JTextFieldTamanhoMaximo(58));
        jtextfieldStatus.setDocument(new JTextFieldTamanhoMaximo(12));
        jtextfieldQuantidadeLivros.setDocument(new JTextFieldTamanhoMaximo(5));
    }

    /**
     * O Método é responsável por preencher os componentes do formulário, com
     * base ao codigo da disciplina que foi informado via argumento no
     * construtor
     *
     * @param codlivro String - Codigo do Livro
     */
    public void preencheCamposEditar(String codlivro) {

        String sql = "SELECT * FROM tblivros INNER JOIN tbdisciplina ON tblivros.coddisciplina = tbdisciplina.coddisciplina AND tblivros.codlivro = " + codlivro;
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL(sql);
            conectmysql.resultset.first();
            nomedolivro = conectmysql.resultset.getString("nomelivro");
            localbiblioteca = conectmysql.resultset.getString("localbiblioteca");
            jtextfieldNomeLivro.setText(nomedolivro);
            labelCodLivroResultado.setText(codlivro);
            labelCodDisciplinaResultado.setText(conectmysql.resultset.getString("coddisciplina"));
            jtextfieldStatus.setText(conectmysql.resultset.getString("statuslivro"));
            jtextfieldQuantidadeLivros.setText(conectmysql.resultset.getString("contexemplares"));
            jcomboboxLocalBiblioteca.setSelectedItem(conectmysql.resultset.getString("localbiblioteca"));
            jcomboboxDisciplinas.setSelectedItem(conectmysql.resultset.getString("nomedisciplina"));
            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Campos Editar: " + erro);

        }
    }

    /**
     * Este método é responsável por pegar as informações dos componentes do
     * formulário e atualiza-las no banco de dados.
     */
    public void editarLivro() {
        String sql = "UPDATE tblivros set coddisciplina = ?, nomelivro = ?, statuslivro = ?, contexemplares = ?, localbiblioteca = ? where codlivro = ?";
        try {
            cadastrado = false;
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tblivros");
            if ((!jtextfieldNomeLivro.getText().equals(nomedolivro)) || (!localbiblioteca.equals(jcomboboxLocalBiblioteca.getSelectedItem()))) {
                while (conectmysql.resultset.next()) {
                    if (conectmysql.resultset.getString("nomelivro").equals(jtextfieldNomeLivro.getText()) && conectmysql.resultset.getString("localbiblioteca").equals(jcomboboxLocalBiblioteca.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "O Livro informado já está cadastrado nesta biblioteca informada!", "Livro Existente!", JOptionPane.ERROR_MESSAGE);
                        cadastrado = true;
                    }
                }
            }

            if (cadastrado == false) {
                conectmysql.createStatement();
                conectmysql.executaSQL("SELECT * FROM tbdisciplina WHERE nomedisciplina LIKE " + "'" + jcomboboxDisciplinas.getSelectedItem() + "'");
                conectmysql.resultset.first();
                String coddisciplina = conectmysql.resultset.getString("coddisciplina");
                conectmysql.createStatement();
                conectmysql.prepareStatement(sql);
                conectmysql.preparestatement.setString(1, coddisciplina);
                conectmysql.preparestatement.setString(2, jtextfieldNomeLivro.getText());
                conectmysql.preparestatement.setString(3, jtextfieldStatus.getText());
                conectmysql.preparestatement.setString(4, jtextfieldQuantidadeLivros.getText());
                conectmysql.preparestatement.setString(5, (String) jcomboboxLocalBiblioteca.getSelectedItem());
                conectmysql.preparestatement.setString(6, labelCodLivroResultado.getText());
                conectmysql.preparestatement.executeUpdate();
                dispose();

                JOptionPane.showMessageDialog(null, "Livro editado com sucesso, clique no botão ATUALIZAR", "Livro Editado", JOptionPane.INFORMATION_MESSAGE);
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
        labelCodLivro = new javax.swing.JLabel();
        labelNomeLivro = new javax.swing.JLabel();
        jtextfieldNomeLivro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelCodDisciplinaResultado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelCodLivroResultado = new javax.swing.JLabel();
        labelDisciplinas = new javax.swing.JLabel();
        jcomboboxDisciplinas = new javax.swing.JComboBox();
        labelCodDisciplina = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        jtextfieldStatus = new javax.swing.JTextField();
        labelQuantidadeLivros = new javax.swing.JLabel();
        labelLocalBiblioteca = new javax.swing.JLabel();
        jtextfieldQuantidadeLivros = new javax.swing.JTextField();
        jcomboboxLocalBiblioteca = new javax.swing.JComboBox();

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

        labelCodLivro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCodLivro.setText("Cod. Livro");

        labelNomeLivro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeLivro.setText("Nome do Livro");

        jtextfieldNomeLivro.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNomeLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        labelHeader.setText("EDITAR LIVRO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(labelHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelHeader)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelCodDisciplinaResultado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelCodDisciplinaResultado.setForeground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodDisciplinaResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCodDisciplinaResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        labelCodLivroResultado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelCodLivroResultado.setForeground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodLivroResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCodLivroResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        labelDisciplinas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelDisciplinas.setText("Disciplinas");

        jcomboboxDisciplinas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcomboboxDisciplinas.setModel(new javax.swing.DefaultComboBoxModel());

        labelCodDisciplina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCodDisciplina.setText("Cod. Disciplina");

        labelStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelStatus.setText("Status");

        jtextfieldStatus.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelQuantidadeLivros.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelQuantidadeLivros.setText("Qnt. Exemplares (Somente Numeros)");

        labelLocalBiblioteca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelLocalBiblioteca.setText("Local Biblioteca");

        jtextfieldQuantidadeLivros.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldQuantidadeLivros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtextfieldQuantidadeLivros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextfieldQuantidadeLivrosKeyTyped(evt);
            }
        });

        jcomboboxLocalBiblioteca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcomboboxLocalBiblioteca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Campus I", "Campus II", "Campus III" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDisciplinas)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCodLivro)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCodDisciplina)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcomboboxDisciplinas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtextfieldNomeLivro, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNomeLivro, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelQuantidadeLivros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(66, 66, 66))
                                    .addComponent(jtextfieldQuantidadeLivros, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelLocalBiblioteca)
                                            .addComponent(jtextfieldStatus)
                                            .addComponent(labelStatus)
                                            .addComponent(jcomboboxLocalBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jbuttonCancelar)))))
                        .addGap(74, 74, 74))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodLivro)
                    .addComponent(labelCodDisciplina))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomeLivro)
                    .addComponent(labelStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtextfieldNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtextfieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelQuantidadeLivros)
                    .addComponent(labelLocalBiblioteca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtextfieldQuantidadeLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcomboboxLocalBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelDisciplinas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103))
        );

        setBounds(285, 10, 707, 566);
    }// </editor-fold>//GEN-END:initComponents

    // Evento Cancelar
    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    // Evento Editar
    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        if (jtextfieldNomeLivro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o nome do Livro", "Nome disciplina em Branco", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldStatus.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o status do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldQuantidadeLivros.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira a quantidade de Exemplares!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else {
            editarLivro();
        }

    }//GEN-LAST:event_jbuttonEditarActionPerformed

    // Evento Bloqueia Letras Quantidade de Livros
    private void jtextfieldQuantidadeLivrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldQuantidadeLivrosKeyTyped
        String caracteres = "0123456789";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtextfieldQuantidadeLivrosKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JComboBox jcomboboxDisciplinas;
    private javax.swing.JComboBox jcomboboxLocalBiblioteca;
    private javax.swing.JTextField jtextfieldNomeLivro;
    private javax.swing.JTextField jtextfieldQuantidadeLivros;
    private javax.swing.JTextField jtextfieldStatus;
    private javax.swing.JLabel labelCodDisciplina;
    private javax.swing.JLabel labelCodDisciplinaResultado;
    private javax.swing.JLabel labelCodLivro;
    private javax.swing.JLabel labelCodLivroResultado;
    private javax.swing.JLabel labelDisciplinas;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelLocalBiblioteca;
    private javax.swing.JLabel labelNomeLivro;
    private javax.swing.JLabel labelQuantidadeLivros;
    private javax.swing.JLabel labelStatus;
    // End of variables declaration//GEN-END:variables
}
