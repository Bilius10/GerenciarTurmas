package DAO;

import Entidade.Aluno;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public Aluno createAluno(Aluno aluno){
        try {

            Connection coon = ConnectionMysql.openConnection();

            // Inserindo pessoa
            String sqlPessoa = "INSERT INTO pessoa (nome, idade) " +
                    "VALUES (?,?)";
            PreparedStatement statementPessoa = coon.prepareStatement(sqlPessoa);
            statementPessoa.setString(1, aluno.getNome());
            statementPessoa.setInt(2, aluno.getIdade());
            statementPessoa.executeUpdate();

            // Buscando o id da pessoa gerado
            String sqlPessoaFind = "SELECT idPessoa FROM pessoa WHERE nome=? AND idade=? LIMIT 1";
            PreparedStatement statementPessoaFind = coon.prepareStatement(sqlPessoaFind);
            statementPessoaFind.setString(1, aluno.getNome());
            statementPessoaFind.setInt(2, aluno.getIdade());
            ResultSet resultSet = statementPessoaFind.executeQuery();

            int idPessoa = -1;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("idPessoa");
            }

            // Inserindo o cliente
            String sqlCliente = "INSERT INTO aluno (Pessoa_idPessoa, Turma_idturma) " +
                    "VALUES (?,?)";
            PreparedStatement statementCliente = coon.prepareStatement(sqlCliente);
            statementCliente.setInt(1, idPessoa);
            statementCliente.setInt(2, aluno.getTurma_idturma());

            statementCliente.executeUpdate();

            ConnectionMysql.closeConnection();

            System.out.println("Aluno criado");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }

        return aluno;
    }

    public List<Aluno> findAll(){

        List<Aluno> objects = new ArrayList<>();
        try {
            Connection conn = ConnectionMysql.openConnection();

            String sql = "SELECT * FROM aluno join pessoa on pessoa.idPessoa = aluno.Pessoa_idpessoa";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Aluno aluno = new Aluno();
                aluno.setNome(resultSet.getString("nome"));
                aluno.setIdade(resultSet.getInt("idade"));
                aluno.setTurma_idturma(resultSet.getInt("turma_idturma"));
                aluno.setNota(resultSet.getDouble("nota"));
                aluno.setIdAluno(resultSet.getInt("idAluno"));
                objects.add(aluno);
            }
            ConnectionMysql.closeConnection();


        }catch (SQLException e){
            System.out.println("Erro no findAll: "+e.getMessage());
        }
        return objects;
    }

    public Aluno findById(int id){
        Aluno aluno = new Aluno();
        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlFindByID = "SELECT * FROM aluno join pessoa on aluno.Pessoa_idpessoa = pessoa.idPessoa"+
                    " where aluno.pessoa_idpessoa = ?";

            PreparedStatement statementFindById = coon.prepareStatement(sqlFindByID);
            statementFindById.setInt(1, id);
            ResultSet resultSet = statementFindById.executeQuery();

            while (resultSet.next()){

                aluno.setNome(resultSet.getString("nome"));
                aluno.setIdade(resultSet.getInt("idade"));
                aluno.setTurma_idturma(resultSet.getInt("turma_idturma"));
                aluno.setNota(resultSet.getDouble("nota"));
                aluno.setIdAluno(resultSet.getInt("idAluno"));
            }
            ConnectionMysql.closeConnection();

        }catch (SQLException e){
            System.out.println("Erro ao buscar "+e.getMessage());
        }

        return aluno;
    }

    public void deleteAluno(int id){
        try {

            Connection conn = ConnectionMysql.openConnection();

            String sqlDelete = "Delete from aluno where idAluno = ?";

            PreparedStatement statementDelete = conn.prepareStatement(sqlDelete);
            statementDelete.setInt(1, id);

            int rowsAffected = statementDelete.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Aluno excluido");
            }

            ConnectionMysql.closeConnection();
        }catch (SQLException e){
            System.out.println("Erro ao excluir Cliente: "+e.getMessage());
        }

    }
    public void atribuirNota(int id, double nota) {

        try {

            Connection conn = ConnectionMysql.openConnection();

            String mudarNota = "UPDATE aluno SET nota = ? WHERE idAluno = ?";

            PreparedStatement statement = conn.prepareStatement(mudarNota);
            statement.setDouble(1, nota);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Aluno atualizado");
            }
            ConnectionMysql.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }
}
