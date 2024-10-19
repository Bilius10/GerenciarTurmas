package Entidade;

import java.util.List;

public class Turma {

    private int idTurma;
    private String nome;
    private int idProfessor;
    private List<Aluno> alunos;

    public Turma(int idTurma, String nome, int idProfessor, List<Aluno> alunos) {
        this.idTurma = idTurma;
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.alunos = alunos;
    }

    public Turma() {
    }

    public void addAluno(Aluno aluno){
        this.alunos.add(aluno);
    }


    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "idTurma=" + idTurma +
                ", nome='" + nome + '\'' +
                ", idProfessor=" + idProfessor +
                ", alunos=" + alunos +
                '}';
    }
}
