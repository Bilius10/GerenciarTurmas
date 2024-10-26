import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import DAO.TurmaDAO;
import Entidade.Aluno;
import Entidade.Professor;
import Entidade.Turma;

import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        TurmaDAO turmaDAO = new TurmaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        int opcao = 0;
        while (opcao != 11) {
            System.out.println("\n 1-Criar Professor \n 2-Criar ALuno \n 3-Criar Sala" +
                    "\n 4-Mostrar Professores \n 5-Mostrar Alunos \n 6-Procurar Sala por id" +
                    "\n 7-Excluir Professor \n 8-Excluir Aluno \n 9-Atribuir nota a aluno \n 10-Mostrar Salas " +
                    "\n 11-Sair");

            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    Professor professor = new Professor();

                    System.out.print("Nome: ");
                    professor.setNome(teclado.next());
                    System.out.print("Idade: ");
                    teclado.nextLine();
                    professor.setIdade(teclado.nextInt());
                    System.out.print("Salario: ");
                    professor.setSalario(teclado.nextDouble());

                    professorDAO.createProfessor(professor);
                    break;
                case 2:
                    Aluno aluno = new Aluno();
                    System.out.print("Nome: ");
                    aluno.setNome(teclado.next());
                    System.out.print("Idade: ");
                    teclado.nextLine();
                    aluno.setIdade(teclado.nextInt());
                    System.out.print("Id da turma: ");
                    aluno.setTurma_idturma(teclado.nextInt());

                    alunoDAO.createAluno(aluno);
                    break;
                case 3:
                    Turma turma = new Turma();

                    System.out.print("Nome: ");
                    turma.setNome(teclado.next());
                    System.out.print("Id do professor: ");
                    teclado.nextLine();
                    turma.setIdProfessor(teclado.nextInt());

                    turmaDAO.createTurma(turma);
                    break;
                case 4:
                    List<Professor> allProfessores = professorDAO.findAll();

                    for (Professor allProfessore : allProfessores) {
                        System.out.println("id:"+allProfessore.getIdProfessor());
                        System.out.println("nome:"+allProfessore.getNome());
                        System.out.println("Idade:"+allProfessore.getIdade());
                        System.out.println("Salario:"+allProfessore.getSalario());
                        System.out.println(" ");
                    }
                    break;
                case 5:
                    List<Aluno> allAlunos = alunoDAO.findAll();

                    for (Aluno allAluno : allAlunos) {
                        System.out.println("id:"+allAluno.getIdAluno());
                        System.out.println("nome:"+allAluno.getNome());
                        System.out.println("Idade:"+allAluno.getIdade());
                        System.out.println("Nota: "+allAluno.getNota());
                        System.out.println("Id da turma:"+allAluno.getTurma_idturma());
                        System.out.println(" ");
                    }
                    break;
                case 6:
                    System.out.println("Digite o id da turma:");
                    int id = teclado.nextInt();
                    turmaDAO.mostrarTurma(id);

                    break;
                case 7:
                    System.out.println("Digite o id da professor:");
                    int idProfessor = teclado.nextInt();
                    professorDAO.deleteProfessor(idProfessor);
                    break;
                case 8:
                    System.out.println("Digite o id da aluno:");
                    int idAluno = teclado.nextInt();
                    alunoDAO.deleteAluno(idAluno);
                    break;
                case 9:
                    System.out.print("Digite o id do aluno: ");
                    int idAluno2 = teclado.nextInt();
                    System.out.print("Digite nota: ");
                    double nota = teclado.nextDouble();

                    alunoDAO.atribuirNota(idAluno2, nota);
                    break;
                case 10:
                    List<Turma> Turmas = turmaDAO.findAll();

                    for (Turma turma1 : Turmas) {
                        System.out.println("id:"+turma1.getIdTurma());
                        System.out.println("nome:"+turma1.getNome());
                        System.out.println(" ");
                    }

                    break;
                default:
                    System.out.println("Saindo");
                    break;
            }
        }
    }
}