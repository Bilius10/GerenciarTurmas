package Entidade;

public abstract class Pessoa {

    private int idPessoa;
    private String nome;
    private int idade;

    public Pessoa(int idPessoa, String nome, int idade) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.idade = idade;
    }

    public Pessoa() {
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "idPessoa=" + idPessoa +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
