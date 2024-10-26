package DAO;

import Entidade.Turma;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public Turma createTurma(Turma turma){

        try {
            Connection coon = ConnectionMysql.openConnection();

            String buscaProfessor = "SELECT * FROM professor WHERE idProfessor = ?";
            PreparedStatement statementBusca = coon.prepareStatement(buscaProfessor);
            statementBusca.setInt(1, turma.getIdProfessor());

            ResultSet resultado = statementBusca.executeQuery();

            if(!resultado.next()) {
                System.out.println("Professor não existe");
                return null;
            }


            String sqlTurma = "INSERT INTO turma (Nome, Professor_idProfessor) " +
                    "VALUES (?,?)";
            PreparedStatement statementPessoa = coon.prepareStatement(sqlTurma);
            statementPessoa.setString(1, turma.getNome());
            statementPessoa.setInt(2, turma.getIdProfessor());

            statementPessoa.executeUpdate();

            ConnectionMysql.closeConnection();

            System.out.println("Turma criado");

        }catch (SQLException s){
            System.out.println("Erro ao criar: "+s.getMessage());
        }
        return turma;
    }

    public List<Turma> findAll(){

        List<Turma> objects = new ArrayList<>();
        try {
            Connection conn = ConnectionMysql.openConnection();

            String sql = "SELECT * FROM turma";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Turma turma = new Turma();
                turma.setIdTurma(resultSet.getInt("idTurma"));
                turma.setNome(resultSet.getString("Nome"));
                turma.setIdProfessor(resultSet.getInt("Professor_idProfessor"));
                objects.add(turma);
            }


        }catch (SQLException e){
            System.out.println("Erro no findAll: "+e.getMessage());
        }
        return objects;
    }

    public void mostrarTurma(int id) {
        try {
            Connection conn = ConnectionMysql.openConnection();

            String sql = "SELECT turma.Nome, pessoa.nome FROM turma join professor on turma.Professor_idProfessor = professor.idProfessor" +
                    " join pessoa on pessoa.idPessoa = professor.Pessoa_idPessoa " +
                    " where  turma.idturma = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Turma: " + resultSet.getString("turma.Nome"));
                System.out.println("Professor: " + resultSet.getString("pessoa.nome"));
            }

            String sqlAlunos = "Select pessoa.nome from aluno join pessoa on aluno.Pessoa_idPessoa = pessoa.idPessoa " +
                    "where aluno.Turma_idturma = ?";

            PreparedStatement statementAlunos = conn.prepareStatement(sqlAlunos);
            statementAlunos.setInt(1, id);
            ResultSet resultSetAlunos = statementAlunos.executeQuery();

            System.out.println("Alunos: ");
            while (resultSetAlunos.next()) {
                System.out.println(resultSetAlunos.getString("pessoa.nome"));
            }

        } catch (SQLException s) {
            System.out.println("Erro ao buscar " + s.getMessage());
        }
    }

    public void deleteTurma(int id){
        try {
            Connection conn = ConnectionMysql.openConnection();

            String sqlDelete = "Delete from turma where idturma = ?";

            PreparedStatement statementDelete = conn.prepareStatement(sqlDelete);
            statementDelete.setInt(1, id);

            int rowsAffected = statementDelete.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Turma excluida");
            }

        }catch (SQLException e){
            System.out.println("Erro ao excluir Cliente: "+e.getMessage());
        }
    }
}


