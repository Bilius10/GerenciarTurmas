package Entidade;

public class Aluno extends Pessoa{

    private int idAluno;
    private double nota;
    private int Turma_idturma;

    public Aluno(int idPessoa, String nome, int idade, int idAluno, double nota, int turma_idturma) {
        super(idPessoa, nome, idade);
        this.idAluno = idAluno;
        this.nota = nota;
        this.Turma_idturma = turma_idturma;
    }

    public Aluno(){

    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getTurma_idturma() {
        return Turma_idturma;
    }

    public void setTurma_idturma(int turma_idturma) {
        this.Turma_idturma = turma_idturma;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "idAluno=" + idAluno +
                ", nota=" + nota +
                ", Turma_idturma=" + Turma_idturma +
                '}';
    }
}
