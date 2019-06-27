package interfaces;

import conexao.ConexaoMysql;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.Box;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe da tela principal do sistema, nessa tela é apresentado 
 * um menu com módulos do sistema que podem variar de acordo com 
 * o nível de permissão do usuário logado.
 */
public class TelaPrincipal extends javax.swing.JFrame {

    // Criando objetos acessíveis na classe
    TelaLogin telalogin = new TelaLogin();
    ConexaoMysql conectmysql = new ConexaoMysql();

    /**
     * Construtor inicializa componentes básicos da classe e chama o método
     * liberapermissão.
     */
    public TelaPrincipal() {
        initComponents();
        liberaPermissao();
        setIcon();
    }
    
    /**
     * Método seta a imagem ao favicon do frame.
     */
     private void setIcon() {
//         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bookstore2.0/src/imagens/book.jpg")));
     }
     /**
     * Metódo para liberar os módulos do sistema de acordo com o nível de
     * permissão do usuário.
     */
    public final void liberaPermissao() {
        switch (telalogin.tipouser) {
            case "Aluno":
                menuCadastrar.setVisible(false);
                menuRelatorios.setVisible(false);
                break;

            case "Professor":
                submenuCurso.setVisible(false);
                submenuDisciplina.setVisible(false);
                submenuUsuario.setVisible(false);
                submenuCursos.setVisible(false);
                submenuDisciplinas.setVisible(false);
                submenuUsuarios.setVisible(false);
                break;

            case "Administrador":
                break;

        }
    }

    /**
     * Metódo para gerar relatório do iReport.
     *
     * @param Query String - Código SQL para consulta no Banco.
     * @param localArquivo String - Caminho do arquivo do relatório jasper.
     */
    public void gerarRelatorio(String Query, String localArquivo) {
        conectmysql.abrirConexao();
        JasperPrint jasperprint = null;

        try {
            conectmysql.executaSQL(Query);
            JRResultSetDataSource relatorioresult = new JRResultSetDataSource(conectmysql.resultset);
            jasperprint = JasperFillManager.fillReport(localArquivo, new HashMap(), relatorioresult);
            JasperViewer jasperview = new JasperViewer(jasperprint, false);
            jasperview.setZoomRatio((float) 0.8);
            jasperview.setVisible(true);
            conectmysql.fecharConexao();
        } catch (JRException erro) {
            System.out.println("Erro: " + erro);
        }

    }

    // Método é iniciado com o construtor gerado automaticamente pelo Editor (GUI)
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlabelBackground = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jmenuPrincipal = new javax.swing.JMenuBar();
        menuUsuario = new javax.swing.JMenu();
        submenuAlterarSenha = new javax.swing.JMenuItem();
        submenuTrocarUsuario = new javax.swing.JMenuItem();
        submenuSair = new javax.swing.JMenuItem();
        menuPesquisar = new javax.swing.JMenu();
        submenuLivrosSugeridos = new javax.swing.JMenuItem();
        menuCadastrar = new javax.swing.JMenu();
        submenuUsuario = new javax.swing.JMenuItem();
        submenuCurso = new javax.swing.JMenuItem();
        submenuDisciplina = new javax.swing.JMenuItem();
        submenuLivro = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        submenuUsuarios = new javax.swing.JMenuItem();
        submenuCursos = new javax.swing.JMenuItem();
        submenuDisciplinas = new javax.swing.JMenuItem();
        submenuLivros = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        submenuAutoria = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bookle Sistema Acadêmico");
        setResizable(false);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Olá "+telalogin.nomelogado+" - "+telalogin.matriculalogado);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastraruser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane1.add(jPanel1);
        jPanel1.setBounds(0, 290, 410, 60);

        jlabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/book.jpg"))); // NOI18N
        jDesktopPane1.add(jlabelBackground);
        jlabelBackground.setBounds(370, -10, 1290, 620);

        jLabel3.setText("jLabel3");
        jDesktopPane1.add(jLabel3);
        jLabel3.setBounds(810, 170, 34, 14);

        jmenuPrincipal.setAlignmentY(0.5F);

        menuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuario.png"))); // NOI18N
        menuUsuario.setText("Usuário");
        menuUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuUsuario.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        menuUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuUsuario.setIconTextGap(5);
        menuUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        submenuAlterarSenha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        submenuAlterarSenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuAlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterarsenha.png"))); // NOI18N
        submenuAlterarSenha.setText("Alterar Senha");
        submenuAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuAlterarSenhaActionPerformed(evt);
            }
        });
        menuUsuario.add(submenuAlterarSenha);

        submenuTrocarUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        submenuTrocarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuTrocarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/trocaruser.png"))); // NOI18N
        submenuTrocarUsuario.setText("Trocar Usuário");
        submenuTrocarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuTrocarUsuarioActionPerformed(evt);
            }
        });
        menuUsuario.add(submenuTrocarUsuario);

        submenuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        submenuSair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        submenuSair.setText("Sair");
        submenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuSairActionPerformed(evt);
            }
        });
        menuUsuario.add(submenuSair);

        jmenuPrincipal.add(menuUsuario);

        menuPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        menuPesquisar.setText("Pesquisar");
        menuPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        submenuLivrosSugeridos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        submenuLivrosSugeridos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuLivrosSugeridos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/livrossugeridos.png"))); // NOI18N
        submenuLivrosSugeridos.setText("Livros Sugeridos");
        submenuLivrosSugeridos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuLivrosSugeridosActionPerformed(evt);
            }
        });
        menuPesquisar.add(submenuLivrosSugeridos);

        jmenuPrincipal.add(menuPesquisar);

        menuCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastrar.png"))); // NOI18N
        menuCadastrar.setText("Cadastrar");
        menuCadastrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuCadastrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuCadastrar.setIconTextGap(5);
        menuCadastrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        submenuUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        submenuUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionauser.png"))); // NOI18N
        submenuUsuario.setText("Usuário");
        submenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuUsuarioActionPerformed(evt);
            }
        });
        menuCadastrar.add(submenuUsuario);

        submenuCurso.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        submenuCurso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/curso.png"))); // NOI18N
        submenuCurso.setText("Curso");
        submenuCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuCursoActionPerformed(evt);
            }
        });
        menuCadastrar.add(submenuCurso);

        submenuDisciplina.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        submenuDisciplina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina.png"))); // NOI18N
        submenuDisciplina.setText("Disciplina");
        submenuDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuDisciplinaActionPerformed(evt);
            }
        });
        menuCadastrar.add(submenuDisciplina);

        submenuLivro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        submenuLivro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/livro.png"))); // NOI18N
        submenuLivro.setText("Livro");
        submenuLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuLivroActionPerformed(evt);
            }
        });
        menuCadastrar.add(submenuLivro);

        jmenuPrincipal.add(menuCadastrar);

        menuRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorios.png"))); // NOI18N
        menuRelatorios.setText("Relatórios");
        menuRelatorios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuRelatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuRelatorios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        submenuUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        submenuUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatoriouser.png"))); // NOI18N
        submenuUsuarios.setText("Relatorio Usuários");
        submenuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuUsuariosActionPerformed(evt);
            }
        });
        menuRelatorios.add(submenuUsuarios);

        submenuCursos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        submenuCursos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatoriocurso.png"))); // NOI18N
        submenuCursos.setText("Relatório Cursos");
        submenuCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuCursosActionPerformed(evt);
            }
        });
        menuRelatorios.add(submenuCursos);

        submenuDisciplinas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        submenuDisciplinas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuDisciplinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatoriodisciplinas.png"))); // NOI18N
        submenuDisciplinas.setText("Relatório Disciplinas");
        submenuDisciplinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuDisciplinasActionPerformed(evt);
            }
        });
        menuRelatorios.add(submenuDisciplinas);

        submenuLivros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        submenuLivros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatoriolivros.png"))); // NOI18N
        submenuLivros.setText("Relatório Livros");
        submenuLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuLivrosActionPerformed(evt);
            }
        });
        menuRelatorios.add(submenuLivros);

        jmenuPrincipal.add(menuRelatorios);

        menuAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ajuda.png"))); // NOI18N
        menuAjuda.setText("Ajuda");
        menuAjuda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuAjuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuAjuda.setIconTextGap(5);
        menuAjuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        submenuAutoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        submenuAutoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submenuAutoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/autoria.png"))); // NOI18N
        submenuAutoria.setText("Autoria");
        submenuAutoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuAutoriaActionPerformed(evt);
            }
        });
        menuAjuda.add(submenuAutoria);

        jmenuPrincipal.add(menuAjuda);

        jmenuPrincipal.add(Box.createHorizontalGlue());
        menuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/exit.png"))); // NOI18N
        menuSair.setText("Sair");
        menuSair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuSair.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        menuSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuSair.setIconTextGap(5);
        menuSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuSairMousePressed(evt);
            }
        });
        jmenuPrincipal.add(menuSair);

        setJMenuBar(jmenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(901, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Evento submenu Autoria
    private void submenuAutoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuAutoriaActionPerformed
        TelaAutoria telaautoria = new TelaAutoria();
        jDesktopPane1.add(telaautoria);
        telaautoria.setLocation(450,70);
        telaautoria.setVisible(true);
    }//GEN-LAST:event_submenuAutoriaActionPerformed
    // Evento submenu Curso
    private void submenuCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuCursoActionPerformed
        TelaCadastrarCurso telacadastrarcurso = new TelaCadastrarCurso();
        jDesktopPane1.add(telacadastrarcurso);
        telacadastrarcurso.setVisible(true);
    }//GEN-LAST:event_submenuCursoActionPerformed
    // Evento submenu Usuario
    private void submenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuUsuarioActionPerformed
        TelaCadastrarUsuario telacadastrarusuario = new TelaCadastrarUsuario();
        jDesktopPane1.add(telacadastrarusuario);
        telacadastrarusuario.setVisible(true);
    }//GEN-LAST:event_submenuUsuarioActionPerformed
    // Evento submenu Livros
    private void submenuLivrosSugeridosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuLivrosSugeridosActionPerformed
        TelaPesquisar telapesquisar = new TelaPesquisar();
        jDesktopPane1.add(telapesquisar);
        telapesquisar.setVisible(true);
    }//GEN-LAST:event_submenuLivrosSugeridosActionPerformed
    // Evento submenu TrocarUsuario
    private void submenuTrocarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuTrocarUsuarioActionPerformed
        dispose();
        telalogin.setVisible(true);
    }//GEN-LAST:event_submenuTrocarUsuarioActionPerformed
    // Evento submenu AlterarSenha
    private void submenuAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuAlterarSenhaActionPerformed
        TelaAlterarSenha telaalterarsenha = new TelaAlterarSenha();
        jDesktopPane1.add(telaalterarsenha);
        telaalterarsenha.setVisible(true);
    }//GEN-LAST:event_submenuAlterarSenhaActionPerformed
    // Evento menu Sair
    private void menuSairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMousePressed
        dispose();
    }//GEN-LAST:event_menuSairMousePressed
    // Evento submenu Disciplina
    private void submenuDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuDisciplinaActionPerformed
        TelaCadastrarDisciplina telacadastrardisciplina = new TelaCadastrarDisciplina();
        jDesktopPane1.add(telacadastrardisciplina);
        telacadastrardisciplina.setVisible(true);
    }//GEN-LAST:event_submenuDisciplinaActionPerformed
    // Evento submenu Usuarios
    private void submenuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuUsuariosActionPerformed
        gerarRelatorio("SELECT * FROM tbuser", "/src/relatorios/RelatorioUsuarios.jasper");
    }//GEN-LAST:event_submenuUsuariosActionPerformed
    // Evento submenu Curso
    private void submenuCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuCursosActionPerformed
        gerarRelatorio("SELECT * FROM tbcurso", "Bookstore2.0/src/relatorios/RelatorioCurso.jasper");
    }//GEN-LAST:event_submenuCursosActionPerformed
    // Evento submenu Disciplinas
    private void submenuDisciplinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuDisciplinasActionPerformed
        gerarRelatorio("SELECT * FROM tbdisciplina INNER JOIN tbcurso ON tbdisciplina.codcurso = tbcurso.codcurso", "Bookstore2.0/src/relatorios/RelatorioDisciplinas.jasper");
    }//GEN-LAST:event_submenuDisciplinasActionPerformed
    // Evento submenu Livros
    private void submenuLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuLivrosActionPerformed
        gerarRelatorio(
                "SELECT\n"
                + "     *,\n"
                + "     tbcurso.`codcurso` AS tbcurso_codcurso,\n"
                + "     tbcurso.`nomecurso` AS tbcurso_nomecurso,\n"
                + "     tbdisciplina_A.`codcurso` AS tbdisciplina_A_codcurso\n"
                + "FROM\n"
                + "     `tblivros` tblivros INNER JOIN `tbdisciplina` tbdisciplina_A ON tblivros.`coddisciplina` = tbdisciplina_A.`coddisciplina`\n"
                + "     INNER JOIN `tbcurso` tbcurso ON tbdisciplina_A.`codcurso` = tbcurso.`codcurso`", "Bookstore2.0/src/relatorios/RelatorioLivro.jasper");
    }//GEN-LAST:event_submenuLivrosActionPerformed
    // Evento submenu Livro
    private void submenuLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuLivroActionPerformed
        TelaCadastrarLivro telacadastrarlivro = new TelaCadastrarLivro();
        jDesktopPane1.add(telacadastrarlivro);
        telacadastrarlivro.setVisible(true);
    }//GEN-LAST:event_submenuLivroActionPerformed

    private void submenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuSairActionPerformed
        dispose();
    }//GEN-LAST:event_submenuSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabelBackground;
    private javax.swing.JMenuBar jmenuPrincipal;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuCadastrar;
    private javax.swing.JMenu menuPesquisar;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuUsuario;
    private javax.swing.JMenuItem submenuAlterarSenha;
    private javax.swing.JMenuItem submenuAutoria;
    private javax.swing.JMenuItem submenuCurso;
    private javax.swing.JMenuItem submenuCursos;
    private javax.swing.JMenuItem submenuDisciplina;
    private javax.swing.JMenuItem submenuDisciplinas;
    private javax.swing.JMenuItem submenuLivro;
    private javax.swing.JMenuItem submenuLivros;
    private javax.swing.JMenuItem submenuLivrosSugeridos;
    private javax.swing.JMenuItem submenuSair;
    private javax.swing.JMenuItem submenuTrocarUsuario;
    private javax.swing.JMenuItem submenuUsuario;
    private javax.swing.JMenuItem submenuUsuarios;
    // End of variables declaration//GEN-END:variables
}
