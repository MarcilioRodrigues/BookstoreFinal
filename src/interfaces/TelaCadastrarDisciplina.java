package interfaces;

import conexao.ConexaoMysql;
import modelos.ModeloTabela;
import util.JTextFieldTamanhoMaximo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 * Classe responsável em disponibilizar algumas modificações das Disciplinas
 * com:<br>
 * - Cadastrar nova disciplina<br>
 * - Excluir disciplina<br>
 * - Visualizar disciplinas cadastradas.
 *
 * @author Marcilio-Pc
 */
public class TelaCadastrarDisciplina extends javax.swing.JInternalFrame {

    TelaPesquisar telapesquisar = new TelaPesquisar();
    ConexaoMysql conectmysql = new ConexaoMysql();

    /**
     * Construtor inicia componentes básicos da interface gráfica, e inicia o
     * listarDisciplinar que consiste em preencher um componente jtable com os
     * dados da consulta SQL, o construtor também inicia o preencherCombobox da
     * Classe telaPesquisar, que preenche um combobox com o nome dos cursos.
     */
    public TelaCadastrarDisciplina() {
        initComponents();
        listarDisciplina();
        telapesquisar.preencheComboBox("select * from tbcurso", "nomecurso", jcomboboxCursoCadDisciplina);
        jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
        setLimitTextFields();
    }
    
     /** 
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields(){
        jtextfieldDisciplina.setDocument(new JTextFieldTamanhoMaximo(35,true));
    }
    
    /**
     * Método faz uma consulta na tabela tbdisciplina do banco de dados bookle e
     * preenche uma jtable com as disciplinas.
     */
    public void listarDisciplina() {
        ArrayList dadosdisciplina = new ArrayList();

        dadosdisciplina.clear();

        String[] colunas = new String[]{"Cód Disciplina", "Nome da Disciplina"};
        try {
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbdisciplina");

            while (conectmysql.resultset.next()) {
                dadosdisciplina.add(new Object[]{conectmysql.resultset.getString("coddisciplina"), conectmysql.resultset.getString("nomedisciplina")});
            }
            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela Disciplinas: " + erro);

        }
        ModeloTabela modeltable = new ModeloTabela(dadosdisciplina, colunas);

        jtableListaDisciplinas.setModel(modeltable);
        jtableListaDisciplinas.getColumnModel().getColumn(0).setPreferredWidth(100);
        jtableListaDisciplinas.getColumnModel().getColumn(0).setResizable(false);
        jtableListaDisciplinas.getColumnModel().getColumn(1).setPreferredWidth(301);
        jtableListaDisciplinas.getColumnModel().getColumn(1).setResizable(false);

        jtableListaDisciplinas.getTableHeader().setReorderingAllowed(false);
        jtableListaDisciplinas.setAutoResizeMode(jtableListaDisciplinas.AUTO_RESIZE_OFF);
        jtableListaDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtableListaDisciplinas.setRowHeight(50);
    }

    /**
     * Método cadastra disciplina de acordo com o jtextfield informado e de
     * acordo com o curso selecionado, e se esta disciplina não existir.
     */
    public void cadastrarDisciplina() {
        try {
            Boolean cadastrado = false;
            int codigodocurso = 0;

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbcurso");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("nomecurso").equals(jcomboboxCursoCadDisciplina.getSelectedItem())) {
                    codigodocurso = conectmysql.resultset.getInt("codcurso");
                }
            }

            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbdisciplina");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("nomedisciplina").equals(jtextfieldDisciplina.getText())) {
                    JOptionPane.showMessageDialog(null, "A Disciplina ja está cadastrada", "Disciplina Cadastrada!", JOptionPane.INFORMATION_MESSAGE);
                    cadastrado = true;
                }
            }

            if (cadastrado == false) {
                conectmysql.statement.executeUpdate("INSERT INTO tbdisciplina(codcurso,nomedisciplina) VALUES (" + codigodocurso + "," + "'" + jtextfieldDisciplina.getText() + "')");
                JOptionPane.showMessageDialog(null, "Disciplina Cadastrada com Sucesso!", "Disciplina Cadastrada!", JOptionPane.INFORMATION_MESSAGE);
                jtextfieldDisciplina.setText(null);
                jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Disciplina: " + erro);
        }
    }

    /**
     * Método exclui disciplina de acordo com o getSelectedRow, ou seja de acordo com a seleção
     * feita no jtable pelo usuário.
     */
    public void excluirDisciplina() {
        int selecionado = jtableListaDisciplinas.getSelectedRow();
        if (selecionado != -1) {
            try {
               
                conectmysql.abrirConexao();
                conectmysql.createStatement();
                
                String nomedisciplina = jtableListaDisciplinas.getValueAt(jtableListaDisciplinas.getSelectedRow(), 1).toString();
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Excluir a Disciplina: " + nomedisciplina, "Exclusão de Disciplina", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tbdisciplina  WHERE nomedisciplina ='" + nomedisciplina + "'");
                    JOptionPane.showMessageDialog(null, "Curso " + nomedisciplina + " Excluído com sucesso!","Disciplina Excluída",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão de disciplina cancelada");
                }
                listarDisciplina();
                conectmysql.fecharConexao();

            } catch (Exception erro) {
                if (erro instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Existem Livros vinculados a esta disciplina, exclua os livros do curso", "Erro SQL Foreign", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Erro Excluir Disciplina: " + erro);
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar pelo menos uma disciplina para excluir!");
        }
    }

    // Método gerado automaticamente GUI
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableListaDisciplinas = new javax.swing.JTable();
        jtabelDisciplinasCadastrados = new javax.swing.JLabel();
        jbuttonVoltarMenu = new javax.swing.JButton();
        jbuttonExcluirCurso = new javax.swing.JButton();
        jbuttonCancelarCurso = new javax.swing.JButton();
        jbuttonSalvarCurso = new javax.swing.JButton();
        jbuttonNovoDisciplina = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelTituloCadCurso = new javax.swing.JLabel();
        jcomboboxCursoCadDisciplina = new javax.swing.JComboBox();
        labelNomeCurso = new javax.swing.JLabel();
        jtextfieldDisciplina = new javax.swing.JTextField();
        labelNomeDisciplina = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jbuttonAtualizar3 = new javax.swing.JButton();
        jbuttonEditar = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1290, 615));
        getContentPane().setLayout(null);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jtableListaDisciplinas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableListaDisciplinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableListaDisciplinas);

        jtabelDisciplinasCadastrados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtabelDisciplinasCadastrados.setText("Disciplinas Cadastradas no Sistema");

        jbuttonVoltarMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonVoltarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/back.png"))); // NOI18N
        jbuttonVoltarMenu.setText("Voltar ao Menu");
        jbuttonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarMenuActionPerformed(evt);
            }
        });

        jbuttonExcluirCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/block.png"))); // NOI18N
        jbuttonExcluirCurso.setText("Excluir Disciplina");
        jbuttonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirCursoActionPerformed(evt);
            }
        });

        jbuttonCancelarCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jbuttonCancelarCurso.setText("Cancelar");
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonCancelarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarCursoActionPerformed(evt);
            }
        });

        jbuttonSalvarCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonSalvarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save.png"))); // NOI18N
        jbuttonSalvarCurso.setText("Salvar");
        jbuttonSalvarCurso.setEnabled(false);
        jbuttonSalvarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonSalvarCursoActionPerformed(evt);
            }
        });

        jbuttonNovoDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovoDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_page.png"))); // NOI18N
        jbuttonNovoDisciplina.setText("Nova Disciplina");
        jbuttonNovoDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoDisciplinaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelTituloCadCurso.setFont(new java.awt.Font("Bookman Old Style", 3, 24)); // NOI18N
        labelTituloCadCurso.setText("CADASTRAR DISCIPLINA");

        jcomboboxCursoCadDisciplina.setEditable(true);
        jcomboboxCursoCadDisciplina.setModel(new javax.swing.DefaultComboBoxModel());
        jcomboboxCursoCadDisciplina.setEnabled(false);

        labelNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeCurso.setText("Escolha o Curso:");
        labelNomeCurso.setEnabled(false);

        jtextfieldDisciplina.setEnabled(false);
        jtextfieldDisciplina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextfieldDisciplinaKeyTyped(evt);
            }
        });

        labelNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeDisciplina.setText("Nome da Disciplina:");
        labelNomeDisciplina.setEnabled(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastrardisciplina.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtextfieldDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomeCurso)
                            .addComponent(jcomboboxCursoCadDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomeDisciplina))
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTituloCadCurso)
                        .addGap(51, 51, 51))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTituloCadCurso)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(labelNomeCurso)
                .addGap(2, 2, 2)
                .addComponent(jcomboboxCursoCadDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNomeDisciplina)
                .addGap(7, 7, 7)
                .addComponent(jtextfieldDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        jbuttonAtualizar3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonAtualizar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/refresh.png"))); // NOI18N
        jbuttonAtualizar3.setText("Atualizar");
        jbuttonAtualizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonAtualizar3ActionPerformed(evt);
            }
        });

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jtabelDisciplinasCadastrados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonVoltarMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonExcluirCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonCancelarCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonSalvarCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonNovoDisciplina, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonAtualizar3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jtabelDisciplinasCadastrados))
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jbuttonVoltarMenu)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                            .addGap(130, 130, 130)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbuttonNovoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbuttonExcluirCurso, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addComponent(jbuttonAtualizar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(533, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtabelDisciplinasCadastrados)
                .addGap(9, 9, 9)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbuttonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jbuttonNovoDisciplina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonExcluirCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonAtualizar3)
                        .addContainerGap(150, Short.MAX_VALUE))))
        );

        getContentPane().add(jDesktopPane1);
        jDesktopPane1.setBounds(0, 0, 1280, 580);

        setBounds(0, 0, 820, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirCursoActionPerformed
        excluirDisciplina();
    }//GEN-LAST:event_jbuttonExcluirCursoActionPerformed

    private void jbuttonCancelarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarCursoActionPerformed
        jbuttonNovoDisciplina.setEnabled(true);
        labelNomeCurso.setEnabled(false);
        labelNomeDisciplina.setEnabled(false);
        jcomboboxCursoCadDisciplina.setEnabled(false);
        jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
        jtextfieldDisciplina.setEnabled(false);
        jbuttonExcluirCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jtextfieldDisciplina.setText("");
        jbuttonEditar.setEnabled(true);
    }//GEN-LAST:event_jbuttonCancelarCursoActionPerformed

    private void jbuttonSalvarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarCursoActionPerformed
        String cursoselecionado = ((String) jcomboboxCursoCadDisciplina.getSelectedItem());
        if (cursoselecionado == null) {
            JOptionPane.showMessageDialog(null, "Selecione um curso!", "Campo Curso Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldDisciplina.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o nome da Disciplina!", "Campo Disciplina Vazio", JOptionPane.ERROR_MESSAGE);
        } else {
            cadastrarDisciplina();
            jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
            jtextfieldDisciplina.setText(null);
            jbuttonNovoDisciplina.setEnabled(true);
            labelNomeCurso.setEnabled(false);
            jtextfieldDisciplina.setEnabled(false);
            jbuttonExcluirCurso.setEnabled(true);
            jbuttonCancelarCurso.setEnabled(false);
            jbuttonSalvarCurso.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            jcomboboxCursoCadDisciplina.setEnabled(false);
            jbuttonEditar.setEnabled(true);
            listarDisciplina();
        }
    }//GEN-LAST:event_jbuttonSalvarCursoActionPerformed

    private void jbuttonNovoDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonNovoDisciplinaActionPerformed
        jbuttonNovoDisciplina.setEnabled(false);
        labelNomeCurso.setEnabled(true);
        labelNomeDisciplina.setEnabled(true);
        jbuttonExcluirCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(true);
        jbuttonVoltarMenu.setEnabled(false);
        jtextfieldDisciplina.setEnabled(true);
        jcomboboxCursoCadDisciplina.setEnabled(true);
        jbuttonEditar.setEnabled(false);
    }//GEN-LAST:event_jbuttonNovoDisciplinaActionPerformed

    private void jbuttonAtualizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonAtualizar3ActionPerformed
        listarDisciplina();
    }//GEN-LAST:event_jbuttonAtualizar3ActionPerformed

    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        int selecionado = jtableListaDisciplinas.getSelectedRow();
        if (selecionado != -1) {
            TelaEditarDisciplina editardisciplina = new TelaEditarDisciplina(jtableListaDisciplinas.getValueAt(jtableListaDisciplinas.getSelectedRow(), 0).toString());
            jDesktopPane1.add(editardisciplina);
            editardisciplina.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione alguma DISCIPLINA e clique em editar!");
        }
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    private void jtextfieldDisciplinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldDisciplinaKeyTyped
        String caracteres = "0123456789";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtextfieldDisciplinaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonAtualizar3;
    private javax.swing.JButton jbuttonCancelarCurso;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JButton jbuttonExcluirCurso;
    private javax.swing.JButton jbuttonNovoDisciplina;
    private javax.swing.JButton jbuttonSalvarCurso;
    private javax.swing.JButton jbuttonVoltarMenu;
    private javax.swing.JComboBox jcomboboxCursoCadDisciplina;
    private javax.swing.JLabel jtabelDisciplinasCadastrados;
    private javax.swing.JTable jtableListaDisciplinas;
    private javax.swing.JTextField jtextfieldDisciplina;
    private javax.swing.JLabel labelNomeCurso;
    private javax.swing.JLabel labelNomeDisciplina;
    private javax.swing.JLabel labelTituloCadCurso;
    // End of variables declaration//GEN-END:variables
}
