package DAO;
import Entidade.Professor;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public Professor createProfessor(Professor professor) {
        try {

            Connection coon = ConnectionMysql.openConnection();

            // Inserindo pessoa
            String sqlPessoa = "INSERT INTO pessoa (nome, idade) " +
                    "VALUES (?,?)";
            PreparedStatement statementPessoa = coon.prepareStatement(sqlPessoa);
            statementPessoa.setString(1, professor.getNome());
            statementPessoa.setInt(2, professor.getIdade());
            statementPessoa.executeUpdate();

            // Buscando o id da pessoa gerado
            String sqlPessoaFind = "SELECT idPessoa FROM pessoa WHERE nome=? AND idade=? LIMIT 1";
            PreparedStatement statementPessoaFind = coon.prepareStatement(sqlPessoaFind);
            statementPessoaFind.setString(1, professor.getNome());
            statementPessoaFind.setInt(2, professor.getIdade());
            ResultSet resultSet = statementPessoaFind.executeQuery();

            int idPessoa = -1;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("idPessoa");
            }

            // Inserindo o cliente
            String sqlCliente = "INSERT INTO professor (Salario, Pessoa_idPessoa) " +
                    "VALUES (?,?)";
            PreparedStatement statementCliente = coon.prepareStatement(sqlCliente);
            statementCliente.setDouble(1, professor.getSalario());
            statementCliente.setInt(2, idPessoa);

            statementCliente.executeUpdate();

            ConnectionMysql.closeConnection();

            System.out.println("Professor criado");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }

        return professor;
    }

    public List<Professor> findAll(){

        List<Professor> objects = new ArrayList<>();
        try {
            Connection conn = ConnectionMysql.openConnection();

            String sql = "SELECT * FROM professor join pessoa on pessoa.idPessoa = professor.Pessoa_idpessoa";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Professor professor = new Professor();
                professor.setNome(resultSet.getString("nome"));
                professor.setIdade(resultSet.getInt("idade"));
                professor.setSalario(resultSet.getDouble("salario"));
                professor.setIdProfessor(resultSet.getInt("idProfessor"));
                objects.add(professor);
            }


        }catch (SQLException e){
            System.out.println("Erro no findAll: "+e.getMessage());
        }
        return objects;
    }

    public Professor findById(int id){
        Professor professor = new Professor();
        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlFindByID = "SELECT * FROM professor join pessoa on pessoa.idPessoa = professor.Pessoa_idpessoa"+
                    " where professor.idProfessor = ?";

            PreparedStatement statementFindById = coon.prepareStatement(sqlFindByID);
            statementFindById.setInt(1, id);
            ResultSet resultSet = statementFindById.executeQuery();

            while (resultSet.next()){

                professor.setNome(resultSet.getString("nome"));
                professor.setIdade(resultSet.getInt("idade"));
                professor.setSalario(resultSet.getDouble("salario"));
                professor.setIdProfessor(resultSet.getInt("idProfessor"));
            }

        }catch (SQLException e){
            System.out.println("Erro ao buscar "+e.getMessage());
        }

        return professor;
    }

    public void deleteProfessor(int id){
        try {

            Connection conn = ConnectionMysql.openConnection();

            String sqlDelete = "Delete from professor where idProfessor = ?";

            PreparedStatement statementDelete = conn.prepareStatement(sqlDelete);
            statementDelete.setInt(1, id);

            int rowsAffected = statementDelete.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Professor excluido");
            }else{
                System.out.println("Professor não encontrado");
            }

        }catch (SQLException e){
            System.out.println("Erro ao excluir Cliente: "+e.getMessage());
        }

    }
}