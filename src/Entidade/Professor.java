package Entidade;

public class Professor extends Pessoa{

    private int idProfessor;
    private double salario;

    public Professor(int idPessoa, String nome, int idade, int idProfessor, double salario) {
        super(idPessoa, nome, idade);
        this.idProfessor = idProfessor;
        this.salario = salario;
    }
    public Professor(){

    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "idProfessor=" + idProfessor +
                ", salario=" + salario +
                '}';
    }
}
