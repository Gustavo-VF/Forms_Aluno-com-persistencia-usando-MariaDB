package edu.curso.fatec.control;

import java.time.LocalDate;

import edu.curso.fatec.DAO.AlunoDAO;
import edu.curso.fatec.entity.Aluno;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl {

    // observer dos inputs
    private StringProperty ra = new SimpleStringProperty("");
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty idade = new SimpleStringProperty();
    private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>();

    private ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    AlunoDAO dao = new AlunoDAO();

    public void Inserir() {
        if (ra.get().isEmpty() || nome.get().isEmpty()
                || idade.get().isEmpty() || nascimento.get() == null) {
            System.out.println("preencha todos os campos");

            return;
        }

        Aluno novoAluno = new Aluno();
        novoAluno.setRa(ra.get());
        novoAluno.setNome(nome.get());
        novoAluno.setIdade(idade.get());
        //
        novoAluno.setNascimento(nascimento.get());
        dao.Inserir(novoAluno);

    }

    public void Atualizar() {

        if (ra.get().isEmpty() || nome.get().isEmpty()
                || idade.get().isEmpty() || nascimento.get() == null) {
            System.out.println("preencha todos os campos");

            return;
        }

        Aluno ediAluno = new Aluno();
        ediAluno.setRa(ra.get());
        ediAluno.setNome(nome.get());
        ediAluno.setIdade(idade.get());
        //
        ediAluno.setNascimento(nascimento.get());
        dao.Atualizar(ediAluno);

    }

    public void Remover() {

        if (ra.get().isEmpty()) {
            System.out.println("digite algo no campo de RA");
            return;
        }

        dao.Remover(ra.get());

    }

    public void Pesquisar() {

        if (this.ra.get().isEmpty()) {
            System.out.println("digite algo no campo de RA");
            return;
        }

        Aluno pesquisado = dao.Pesquisar(ra.get());

        if (pesquisado == null) {
            System.out.println("aluno nao encondrado");
            return;
        }

        ra.set(pesquisado.getRa());
        nome.set(pesquisado.getNome());
        idade.set(pesquisado.getIdade());
        nascimento.set(pesquisado.getNascimento());

    }

    // consecoes do observer

    public StringProperty raProperty() {
        return ra;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty idadeProperty() {
        return idade;
    }

    public ObjectProperty<LocalDate> nascProperty() {
        return nascimento;
    }

    public ObservableList<Aluno> getAlunos() {
        return alunos;
    }

    public void atualizaLista() {
        alunos.clear();
        alunos.addAll(dao.getAlunos());

    }

    public void limparCampos() {
        ra.set("");
        nome.set("");
        idade.set("");
        nascimento.set(null);
    }
}
