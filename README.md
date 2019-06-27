Book Store
======

Sistema de compartilhamento de materiais de estudo

Este projeto foi desenvolvido como prova da disciplina de Laboratório de Software do curso de Informática da EEEP Antônio Rodrigues de Oliveira. O sistema tem como objetivo disponibilizar ao aluno a pesquisa de livros sugeridos pelo professor.

Criadores @author
======
- Marcilio Rodrigues Bezerra (Gerente).
- Érika Alves Vieira (Conexão, Telas).
- Davi da Silva Rodrigues (Base de dados).
- Kaylane Guedes (Telas).

Características 
======
* Login
* Alterar Senha
* Pesquisar Livros Sugeridos
* CRUD Usuários
* CRUD Cursos
* CRUD Disciplinas
* CRUD Livros
* Relatórios Usuários 
* Relatórios Cursos
* Relatórios Disciplinas
* Relatórios Livros

Sobre do Desenvolvimento 
======
O sistema foi desenvolvido em JAVA utilizando o IDE NetBeans, foi utilizado a biblioteca iReport para a geração dos relatórios. Como persistencia dos dados foi utilizado o banco de dados MySQL. (Phpmyadmin).

Como utilizar o sistema
======
1. Abra o NetBeans, clique no menu "EQUIPE" > "GIT" e clique em "CLONAR".
2. Coloque o caminho do projeto Bookstore "https://github.com/MarcilioRodrigues/bookprojetofinal.git"
3. Após clonado e aberto o projeto, clique nas bibliotecas do projeto e crie uma nova biblioteca com o nome "iReport/iReport-5.5.1".
4. E adicione todos os .Jar que estão nas pastas:
  - BookleProjeto\library\iReport-5.5.1\ireport\libs\
  - BookleProjeto\library\iReport-5.5.1\ireport\modules\ext\
5. Instale o banco de dados MySQL na sua máquina.
6. Atribua a senha "" (sem senha) ao usuário "root" do MySQL (Phpmyadmin).
7. Crie uma base de dados com o nome de "bookstore".
8. Copie os códigos do pacote bancodedados/bookstore.sql, cole na base de dados bookstore (http://localhost/phpmyadmin/db_sql.php?db=bookstore), execute.
9. Feito esses passos é só executar o projeto.
10. Ao Executar o projeto, em Login use "marcilior" (sem as aspas), e senha "123456" (sem as aspas).
