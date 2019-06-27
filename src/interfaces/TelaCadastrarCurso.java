package interfaces;

import conexao.ConexaoMysql;
import modelos.ModeloTabela;
import util.JTextFieldTamanhoMaximo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 * Classe responsável em disponibilizar algumas modificações dos cursos com:<br>
 * - Cadastrar novo curso<br>
 * - Excluir curso<br>
 * - Visualizar cursos cadastrados.
 * @author Marcilio-Pc
 */
public class TelaCadastrarCurso extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();

    /**
     * Construtor inicia componentes básicos da interface gráfica, e inicia o
     * listarCursos que consiste em preencher um componente jtable com os dados
     * da consulta SQL.
     */
    public TelaCadastrarCurso() {
        initComponents();
        listarCursos();
        setLimitTextFields();
    }

     /** 
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields(){
        jtextfieldCurso.setDocument(new JTextFieldTamanhoMaximo(30,true));
    }
    
    /**
     * Método faz uma consulta na tabela tbcursos do banco de dados bookle e
     * preenche uma jtable com os cursos.
     */
    public void listarCursos() {
        ArrayList dadoscurso = new ArrayList();
        dadoscurso.clear();

        String[] colunas = new String[]{"Código do Curso", "Nome do Curso"};
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbcurso");

            while (conectmysql.resultset.next()) {

                dadoscurso.add(new Object[]{conectmysql.resultset.getString("codcurso"), conectmysql.resultset.getString("nomecurso")});
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela Cursos: " + erro);

        }

        ModeloTabela modeltable = new ModeloTabela(dadoscurso, colunas);

        jtableListaCursos.setModel(modeltable);
        jtableListaCursos.getColumnModel().getColumn(0).setPreferredWidth(105);
        jtableListaCursos.getColumnModel().getColumn(0).setResizable(false);
        jtableListaCursos.getColumnModel().getColumn(1).setPreferredWidth(246);
        jtableListaCursos.getColumnModel().getColumn(1).setResizable(false);

        jtableListaCursos.getTableHeader().setReorderingAllowed(false);
        jtableListaCursos.setAutoResizeMode(jtableListaCursos.AUTO_RESIZE_OFF);
        jtableListaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtableListaCursos.setRowHeight(50);
    }

    /**
     * Método cadastra curso de acordo com o jtextfield informado, se não estiver cadastrado.
     */
    public void cadastrarCurso() {

        try {
            Boolean cadastrado = false;
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbcurso");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("nomecurso").equals(jtextfieldCurso.getText())) {
                    JOptionPane.showMessageDialog(null, "O Curso ja está cadastrado", "Curso Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                    cadastrado = true;
                }
            }

            if (cadastrado == false) {
                conectmysql.statement.executeUpdate("INSERT INTO tbcurso (nomecurso) VALUES (" + "'" + jtextfieldCurso.getText() + "')");
                JOptionPane.showMessageDialog(null, "Curso Cadastrado com Sucesso!", "Usuario Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                jtextfieldCurso.setText(null);
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Curso: " + erro);

        }
    }
    
    /**
     * Método exclui curso de acordo com o getSelectedRow, ou seja de acordo com a seleção
     * feita no jtable pelo usuário.
     */
    public void excluirCurso(){
       int selecionado = jtableListaCursos.getSelectedRow();
        if (selecionado != -1) {
            try {
                conectmysql.abrirConexao();
                conectmysql.createStatement();
                String nomecurso = jtableListaCursos.getValueAt(jtableListaCursos.getSelectedRow(), 1).toString();
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Curso: " + nomecurso, "Exclusão de Curso", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tbcurso  WHERE nomecurso ='" + (jtableListaCursos.getValueAt(jtableListaCursos.getSelectedRow(), 1)) + "'");
                    
                    JOptionPane.showMessageDialog(null, "Curso " + nomecurso + " Excluído com sucesso!","Curso Excluído",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão de curso cancelada!");
                }
                listarCursos();
                conectmysql.fecharConexao();

            } catch (Exception erro) {

                if (erro instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Existem Disciplinas vinculadas a este curso, exclua as disciplinas do curso", "Erro SQL Foreign", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Erro Excluir Curso: " + erro);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Um curso deve ser seleciona para excluir!");
        }
    
    }
   
    // Método gerado automaticamente da interface gráfica GUI
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jbuttonCancelarCurso = new javax.swing.JButton();
        jbuttonVoltarMenu = new javax.swing.JButton();
        jtabelCursoCadastrados = new javax.swing.JLabel();
        jbuttonExcluirCurso = new javax.swing.JButton();
        jbuttonSalvarCurso = new javax.swing.JButton();
        jbuttonNovoCurso = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableListaCursos = new javax.swing.JTable();
        jpanelCadastroCurso = new javax.swing.JPanel();
        labelCursoCad = new javax.swing.JLabel();
        jtextfieldCurso = new javax.swing.JTextField();
        labelTituloCadCurso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jbuttonEditar = new javax.swing.JButton();
        jbuttonAtualizar3 = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1290, 615));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jbuttonCancelarCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jbuttonCancelarCurso.setText("Cancelar");
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonCancelarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarCursoActionPerformed(evt);
            }
        });

        jbuttonVoltarMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonVoltarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/back.png"))); // NOI18N
        jbuttonVoltarMenu.setText("Voltar ao Menu");
        jbuttonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarMenuActionPerformed(evt);
            }
        });

        jtabelCursoCadastrados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtabelCursoCadastrados.setText("Cursos Cadastrados no Sistema");

        jbuttonExcluirCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/block.png"))); // NOI18N
        jbuttonExcluirCurso.setText("Excluir Curso");
        jbuttonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirCursoActionPerformed(evt);
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

        jbuttonNovoCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovoCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_page.png"))); // NOI18N
        jbuttonNovoCurso.setText("Novo Curso");
        jbuttonNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoCursoActionPerformed(evt);
            }
        });

        jtableListaCursos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableListaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableListaCursos);

        jpanelCadastroCurso.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelCursoCad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelCursoCad.setText("Nome do Curso");
        labelCursoCad.setEnabled(false);

        jtextfieldCurso.setEnabled(false);
        jtextfieldCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextfieldCursoKeyTyped(evt);
            }
        });

        labelTituloCadCurso.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        labelTituloCadCurso.setText("CADASTRAR CURSO");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastrarcurso.png"))); // NOI18N

        javax.swing.GroupLayout jpanelCadastroCursoLayout = new javax.swing.GroupLayout(jpanelCadastroCurso);
        jpanelCadastroCurso.setLayout(jpanelCadastroCursoLayout);
        jpanelCadastroCursoLayout.setHorizontalGroup(
            jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelCadastroCursoLayout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(labelTituloCadCurso)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                        .addComponent(labelCursoCad)
                        .addGap(172, 172, 172))))
            .addGroup(jpanelCadastroCursoLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jtextfieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 97, Short.MAX_VALUE))
        );
        jpanelCadastroCursoLayout.setVerticalGroup(
            jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTituloCadCurso)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addComponent(labelCursoCad)
                .addGap(18, 18, 18)
                .addComponent(jtextfieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
            }
        });

        jbuttonAtualizar3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonAtualizar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/refresh.png"))); // NOI18N
        jbuttonAtualizar3.setText("Atualizar");
        jbuttonAtualizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonAtualizar3ActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jbuttonCancelarCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonVoltarMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jtabelCursoCadastrados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonExcluirCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonSalvarCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonNovoCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jpanelCadastroCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonAtualizar3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbuttonVoltarMenu)
                .addGap(18, 18, 18)
                .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jtabelCursoCadastrados))
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                            .addGap(112, 112, 112)
                            .addComponent(jpanelCadastroCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                            .addGap(154, 154, 154)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbuttonNovoCurso)
                                .addComponent(jbuttonAtualizar3)
                                .addComponent(jbuttonExcluirCurso)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jpanelCadastroCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtabelCursoCadastrados)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jbuttonNovoCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbuttonExcluirCurso)
                        .addGap(12, 12, 12)
                        .addComponent(jbuttonAtualizar3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        setBounds(0, 0, 768, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed
        dispose();     
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonCancelarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarCursoActionPerformed
        jbuttonNovoCurso.setEnabled(true);
        labelCursoCad.setEnabled(false);
        jtextfieldCurso.setEnabled(false);
        jbuttonExcluirCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jtextfieldCurso.setText("");
        jbuttonEditar.setEnabled(true);
    }//GEN-LAST:event_jbuttonCancelarCursoActionPerformed

    private void jbuttonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirCursoActionPerformed
     excluirCurso();
    }//GEN-LAST:event_jbuttonExcluirCursoActionPerformed

    private void jbuttonNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonNovoCursoActionPerformed
        jbuttonNovoCurso.setEnabled(false);
        labelCursoCad.setEnabled(true);
        jtextfieldCurso.setEnabled(true);
        jbuttonExcluirCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(true);
        jbuttonVoltarMenu.setEnabled(false);
        jbuttonEditar.setEnabled(false);
    }//GEN-LAST:event_jbuttonNovoCursoActionPerformed

    private void jbuttonSalvarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarCursoActionPerformed
        if (!jtextfieldCurso.getText().equals("")) {
            cadastrarCurso();
            jtextfieldCurso.setText(null);
            jbuttonNovoCurso.setEnabled(true);
            labelCursoCad.setEnabled(false);
            jtextfieldCurso.setEnabled(false);
            jbuttonExcluirCurso.setEnabled(true);
            jbuttonCancelarCurso.setEnabled(false);
            jbuttonSalvarCurso.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            jbuttonEditar.setEnabled(true);
            listarCursos();
        } else {
            JOptionPane.showMessageDialog(null, "Insira o nome do Curso!", "Campo Curso Vazio", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbuttonSalvarCursoActionPerformed

    private void jtextfieldCursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldCursoKeyTyped
           String caracteres = "0123456789";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtextfieldCursoKeyTyped

    private void jbuttonAtualizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonAtualizar3ActionPerformed
        listarCursos();
    }//GEN-LAST:event_jbuttonAtualizar3ActionPerformed

    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        int selecionado = jtableListaCursos.getSelectedRow();
        if (selecionado != -1) {
            TelaEditarCurso editarcurso = new TelaEditarCurso(jtableListaCursos.getValueAt(jtableListaCursos.getSelectedRow(), 0).toString());
            jDesktopPane1.add(editarcurso);
            editarcurso.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum CURSO e clique em editar!");
        }
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonAtualizar3;
    private javax.swing.JButton jbuttonCancelarCurso;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JButton jbuttonExcluirCurso;
    private javax.swing.JButton jbuttonNovoCurso;
    private javax.swing.JButton jbuttonSalvarCurso;
    private javax.swing.JButton jbuttonVoltarMenu;
    private javax.swing.JPanel jpanelCadastroCurso;
    private javax.swing.JLabel jtabelCursoCadastrados;
    private javax.swing.JTable jtableListaCursos;
    private javax.swing.JTextField jtextfieldCurso;
    private javax.swing.JLabel labelCursoCad;
    private javax.swing.JLabel labelTituloCadCurso;
    // End of variables declaration//GEN-END:variables
}
