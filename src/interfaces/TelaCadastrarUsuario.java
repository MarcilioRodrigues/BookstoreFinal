package interfaces;

import conexao.ConexaoMysql;
import modelos.ModeloTabela;
import util.JTextFieldTamanhoMaximo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 * Classe responsável em disponibilizar algumas modificações dos Usuarios
 * com:<br>
 * - Cadastrar novo Usuário<br>
 * - Excluir Usuário<br>
 * - Visualizar usuários cadastrados.
 *
 * @author Marcilio-Pc
 */
public class TelaCadastrarUsuario extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    public String loginunicoCad;
    public String nomeCad;
    public String senhaCad;
    public String tipoCad = "Aluno";

    /**
     * Construtor inicia componentes básicos da interface gráfica, e inicia o
     * listaruser que consiste em preencher um componente jtable com os dados da
     * consulta SQL dos usuários cadastrados.
     */
    public TelaCadastrarUsuario() {
        initComponents();
        listarUser();
        setLimitTextFields();
    }

     /** 
     * Método responsável por inicializar os componentes de entrada de dados,
     * com a classes que limita os componentes<br>
     * Ex: jtextfield, jpasswordfield.
     */
    public void setLimitTextFields(){
        jtextfieldNome.setDocument(new JTextFieldTamanhoMaximo(40));
        jtextfieldLoginUnico.setDocument(new JTextFieldTamanhoMaximo(20));
        jpasswordfieldSenha.setDocument(new JTextFieldTamanhoMaximo(20));
    }
    
    /**
     * Método cadastra usuários de acordo com os campos:<br>
     * - Nome do Usuário<br>
     * - Senha - Tipo de Permissão do usuário.
     */
    public void cadastrarUser() {

        try { 
            Boolean cadastrado = false;
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbuser");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("loginunico").equals(loginunicoCad)) {
                    JOptionPane.showMessageDialog(null, "O Login único ja está cadastrado!\n"
                            + "Sua matrícula é: " + conectmysql.resultset.getString("matriculauser"), "Usuario Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                    cadastrado = true;
                }
            }
           
            if (cadastrado == false) {
                conectmysql.statement.executeUpdate("INSERT INTO tbuser (loginunico,nomeuser,senhauser,permissaouser) VALUES ("+"'"+ loginunicoCad +"'," + "'" + nomeCad + "'," + "'" + senhaCad + "'" + ",'" + tipoCad + "')");
                conectmysql.createStatement();
                conectmysql.executaSQL("select * from tbuser");

                while (conectmysql.resultset.next()) {
                    if (conectmysql.resultset.getString("nomeuser").equals(nomeCad)) {
                        JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!\n"
                                + "Sua matrícula é: " + conectmysql.resultset.getString("matriculauser"), "Usuario Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                        jpasswordfieldSenha.setText("");
                        jtextfieldNome.setText("");
                    }
                }

            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Usuario: " + erro);
        }
    }

    /**
     * Método faz uma consulta na tabela tbusuario do banco de dados bookle e
     * preenche uma jtable com os usuarios cadastrados.
     */
    public void listarUser() {
        ArrayList dadosuser = new ArrayList();
        dadosuser.clear();

        String[] colunas = new String[]{"Matrícula","Login Único", "Nome", "Senha", "Permissão"};
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbuser");

            while (conectmysql.resultset.next()) {
                dadosuser.add(new Object[]{conectmysql.resultset.getString("matriculauser"),conectmysql.resultset.getString("loginunico"), conectmysql.resultset.getString("nomeuser"), conectmysql.resultset.getString("senhauser"), conectmysql.resultset.getString("permissaouser")});
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela Usuários: " + erro);

        }
        ModeloTabela modeltable = new ModeloTabela(dadosuser, colunas);

        jtableListaUsuarios.setModel(modeltable);
        jtableListaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtableListaUsuarios.getColumnModel().getColumn(0).setResizable(false);
        jtableListaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(130);
        jtableListaUsuarios.getColumnModel().getColumn(1).setResizable(false);
        jtableListaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(244);
        jtableListaUsuarios.getColumnModel().getColumn(2).setResizable(false);
        jtableListaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(110);
        jtableListaUsuarios.getColumnModel().getColumn(3).setResizable(false);
        jtableListaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtableListaUsuarios.getColumnModel().getColumn(4).setResizable(false);
        jtableListaUsuarios.getTableHeader().setReorderingAllowed(false);
        jtableListaUsuarios.setAutoResizeMode(jtableListaUsuarios.AUTO_RESIZE_OFF);
        jtableListaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtableListaUsuarios.setRowHeight(50);

    }

    /**
     * Método exclui usuário de acordo com o getSelectedRow, ou seja de acordo
     * com a seleção feita no jtable pelo usuário.
     */
    public void excluirUser() {
        int selecionado = jtableListaUsuarios.getSelectedRow();
        if (selecionado != -1) {
            try {

                conectmysql.abrirConexao();
                conectmysql.createStatement();

                String nomeusuario = jtableListaUsuarios.getValueAt(jtableListaUsuarios.getSelectedRow(), 2).toString();
                String loginunico = jtableListaUsuarios.getValueAt(jtableListaUsuarios.getSelectedRow(), 1).toString();
                int opcao = JOptionPane.showConfirmDialog(null, "Nome do usuário: " + nomeusuario 
                        +"\nLogin Unico: "+loginunico
                        +"\n\nDeseja Excluir este usuário?", "Exclusão de Usuário", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tbuser  WHERE loginunico ='" + loginunico + "'");
                    JOptionPane.showMessageDialog(null, "Usuário " + nomeusuario + " Excluído com sucesso!", "Usuário Excluído", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
                listarUser();
                conectmysql.fecharConexao();

            } catch (Exception erro) {
                System.out.println("Erro Excluir Usuario: " + erro);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar pelo menos um usuário para excluir!");
        }

    }

    // Método gerado automaticamente GUI
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jbuttonSalvar = new javax.swing.JButton();
        jbuttonVoltarMenu = new javax.swing.JButton();
        jpanelCadastroUser = new javax.swing.JPanel();
        labelTipoUsuario = new javax.swing.JLabel();
        radiobuttonAluno = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        radiobuttonProfessor = new javax.swing.JRadioButton();
        radiobuttonAdministrador = new javax.swing.JRadioButton();
        jpasswordfieldSenha = new javax.swing.JPasswordField();
        labelSenhaCad = new javax.swing.JLabel();
        labelNomeCad = new javax.swing.JLabel();
        jtextfieldNome = new javax.swing.JTextField();
        labelloginunico = new javax.swing.JLabel();
        jtextfieldLoginUnico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbuttonNovo = new javax.swing.JButton();
        jbuttonExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableListaUsuarios = new javax.swing.JTable();
        jbuttonCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbuttonEditar = new javax.swing.JButton();
        jbuttonAtualizar = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1290, 606));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jbuttonSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save.png"))); // NOI18N
        jbuttonSalvar.setText("Salvar");
        jbuttonSalvar.setEnabled(false);
        jbuttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonSalvarActionPerformed(evt);
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

        jpanelCadastroUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelTipoUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTipoUsuario.setText("Tipo de Usuário:");
        labelTipoUsuario.setEnabled(false);

        radiobuttonAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radiobuttonAluno.setSelected(true);
        radiobuttonAluno.setText("Aluno");
        radiobuttonAluno.setEnabled(false);
        radiobuttonAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobuttonAlunoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("CADASTRAR USUÁRIO");

        radiobuttonProfessor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radiobuttonProfessor.setText("Professor");
        radiobuttonProfessor.setEnabled(false);
        radiobuttonProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobuttonProfessorActionPerformed(evt);
            }
        });

        radiobuttonAdministrador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radiobuttonAdministrador.setText("Administrador");
        radiobuttonAdministrador.setEnabled(false);
        radiobuttonAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobuttonAdministradorActionPerformed(evt);
            }
        });

        jpasswordfieldSenha.setEnabled(false);

        labelSenhaCad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelSenhaCad.setText("Senha:");
        labelSenhaCad.setEnabled(false);

        labelNomeCad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelNomeCad.setText("Nome do Usuário");
        labelNomeCad.setEnabled(false);

        jtextfieldNome.setEnabled(false);
        jtextfieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextfieldNomeKeyTyped(evt);
            }
        });

        labelloginunico.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelloginunico.setText("Login Único:");
        labelloginunico.setEnabled(false);

        jtextfieldLoginUnico.setEnabled(false);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastrar.png"))); // NOI18N

        javax.swing.GroupLayout jpanelCadastroUserLayout = new javax.swing.GroupLayout(jpanelCadastroUser);
        jpanelCadastroUser.setLayout(jpanelCadastroUserLayout);
        jpanelCadastroUserLayout.setHorizontalGroup(
            jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelCadastroUserLayout.createSequentialGroup()
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelCadastroUserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTipoUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(radiobuttonAluno)
                        .addGap(18, 18, 18)
                        .addComponent(radiobuttonProfessor)
                        .addGap(18, 18, 18)
                        .addComponent(radiobuttonAdministrador))
                    .addGroup(jpanelCadastroUserLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jpanelCadastroUserLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(labelNomeCad))
                            .addComponent(jtextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(jpanelCadastroUserLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelloginunico)
                    .addComponent(jtextfieldLoginUnico, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 45, Short.MAX_VALUE)
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpasswordfieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenhaCad))
                .addGap(23, 23, 23))
        );
        jpanelCadastroUserLayout.setVerticalGroup(
            jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelCadastroUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addComponent(labelNomeCad)
                .addGap(14, 14, 14)
                .addComponent(jtextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenhaCad)
                    .addComponent(labelloginunico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtextfieldLoginUnico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpasswordfieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jpanelCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radiobuttonAluno)
                    .addComponent(labelTipoUsuario)
                    .addComponent(radiobuttonProfessor)
                    .addComponent(radiobuttonAdministrador))
                .addContainerGap())
        );

        jbuttonNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/accept_page.png"))); // NOI18N
        jbuttonNovo.setText("Novo Usuário");
        jbuttonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoActionPerformed(evt);
            }
        });

        jbuttonExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/block.png"))); // NOI18N
        jbuttonExcluir.setText("Excluir Usuário");
        jbuttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirActionPerformed(evt);
            }
        });

        jtableListaUsuarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableListaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableListaUsuarios);

        jbuttonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        jbuttonCancelar.setText("Cancelar");
        jbuttonCancelar.setEnabled(false);
        jbuttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Usuários Cadastrados no Sistema");

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
            }
        });

        jbuttonAtualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/refresh.png"))); // NOI18N
        jbuttonAtualizar.setText("Atualizar");
        jbuttonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonAtualizarActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jbuttonSalvar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonVoltarMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jpanelCadastroUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonNovo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonExcluir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbuttonAtualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel2))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jpanelCadastroUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbuttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jbuttonVoltarMenu)
                                .addGap(28, 28, 28)
                                .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbuttonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                .addComponent(jbuttonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbuttonAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jpanelCadastroUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jbuttonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonAtualizar))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbuttonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
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

        setBounds(0, 0, 822, 591);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarActionPerformed
        nomeCad = jtextfieldNome.getText();
        senhaCad = jpasswordfieldSenha.getText();
        loginunicoCad = jtextfieldLoginUnico.getText();

        if (!nomeCad.equals("") && !senhaCad.equals("") && !loginunicoCad.equals("")) {
            cadastrarUser();
            jbuttonNovo.setEnabled(true);
            labelNomeCad.setEnabled(false);
            labelSenhaCad.setEnabled(false);
            labelTipoUsuario.setEnabled(false);
            labelloginunico.setEnabled(false);
            jtextfieldNome.setEnabled(false);
            jpasswordfieldSenha.setEnabled(false);
            jtextfieldLoginUnico.setEnabled(false);
            radiobuttonAdministrador.setEnabled(false);
            radiobuttonAluno.setEnabled(false);
            radiobuttonProfessor.setEnabled(false);
            jbuttonExcluir.setEnabled(true);
            jbuttonSalvar.setEnabled(false);
            jbuttonCancelar.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            jbuttonEditar.setEnabled(true);
            jtextfieldNome.setText("");
            jpasswordfieldSenha.setText("");
            jtextfieldLoginUnico.setText("");
            listarUser();
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jbuttonSalvarActionPerformed

    private void radiobuttonProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobuttonProfessorActionPerformed
        radiobuttonProfessor.setSelected(true);
        radiobuttonAluno.setSelected(false);
        radiobuttonAdministrador.setSelected(false);
        tipoCad = radiobuttonProfessor.getText();
    }//GEN-LAST:event_radiobuttonProfessorActionPerformed

    private void radiobuttonAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobuttonAdministradorActionPerformed
        radiobuttonAdministrador.setSelected(true);
        radiobuttonAluno.setSelected(false);
        radiobuttonProfessor.setSelected(false);
        tipoCad = radiobuttonAdministrador.getText();
    }//GEN-LAST:event_radiobuttonAdministradorActionPerformed

    private void radiobuttonAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobuttonAlunoActionPerformed
        radiobuttonAluno.setSelected(true);
        radiobuttonAdministrador.setSelected(false);
        radiobuttonProfessor.setSelected(false);
        tipoCad = radiobuttonAluno.getText();
    }//GEN-LAST:event_radiobuttonAlunoActionPerformed

    private void jbuttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirActionPerformed
        excluirUser();
    }//GEN-LAST:event_jbuttonExcluirActionPerformed

    private void jbuttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonNovoActionPerformed
        jbuttonNovo.setEnabled(false);
        labelNomeCad.setEnabled(true);
        labelSenhaCad.setEnabled(true);
        labelTipoUsuario.setEnabled(true);
        labelloginunico.setEnabled(true);
        jtextfieldNome.setEnabled(true);
        jtextfieldLoginUnico.setEnabled(true);
        jpasswordfieldSenha.setEnabled(true);
        radiobuttonAdministrador.setEnabled(true);
        radiobuttonAluno.setEnabled(true);
        radiobuttonProfessor.setEnabled(true);
        jbuttonExcluir.setEnabled(false);
        jbuttonCancelar.setEnabled(true);
        jbuttonSalvar.setEnabled(true);
        jbuttonVoltarMenu.setEnabled(false);
        jbuttonEditar.setEnabled(false);
    }//GEN-LAST:event_jbuttonNovoActionPerformed

    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        jbuttonNovo.setEnabled(true);
        labelNomeCad.setEnabled(false);
        labelSenhaCad.setEnabled(false);
        labelTipoUsuario.setEnabled(false);
        labelloginunico.setEnabled(false);
        jtextfieldNome.setEnabled(false);
        jpasswordfieldSenha.setEnabled(false);
        jtextfieldLoginUnico.setEnabled(false);
        radiobuttonAdministrador.setEnabled(false);
        radiobuttonAluno.setEnabled(false);
        radiobuttonProfessor.setEnabled(false);
        jbuttonExcluir.setEnabled(true);
        jbuttonSalvar.setEnabled(false);
        jbuttonCancelar.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jbuttonEditar.setEnabled(true);
        jtextfieldNome.setText("");
        jpasswordfieldSenha.setText("");
        jtextfieldLoginUnico.setText("");
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    private void jtextfieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldNomeKeyTyped
        String caracteres = "0123456789";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtextfieldNomeKeyTyped

    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        int selecionado = jtableListaUsuarios.getSelectedRow();
        if (selecionado != -1) {
            TelaEditarUsuario editaruser = new TelaEditarUsuario(jtableListaUsuarios.getValueAt(jtableListaUsuarios.getSelectedRow(), 0).toString());
            jDesktopPane1.add(editaruser);
            editaruser.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum usuário e clique em editar!");
        }
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    private void jbuttonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonAtualizarActionPerformed
        listarUser();
    }//GEN-LAST:event_jbuttonAtualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonAtualizar;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JButton jbuttonExcluir;
    private javax.swing.JButton jbuttonNovo;
    private javax.swing.JButton jbuttonSalvar;
    private javax.swing.JButton jbuttonVoltarMenu;
    private javax.swing.JPanel jpanelCadastroUser;
    private javax.swing.JPasswordField jpasswordfieldSenha;
    private javax.swing.JTable jtableListaUsuarios;
    private javax.swing.JTextField jtextfieldLoginUnico;
    private javax.swing.JTextField jtextfieldNome;
    private javax.swing.JLabel labelNomeCad;
    private javax.swing.JLabel labelSenhaCad;
    private javax.swing.JLabel labelTipoUsuario;
    private javax.swing.JLabel labelloginunico;
    private javax.swing.JRadioButton radiobuttonAdministrador;
    private javax.swing.JRadioButton radiobuttonAluno;
    private javax.swing.JRadioButton radiobuttonProfessor;
    // End of variables declaration//GEN-END:variables
}
