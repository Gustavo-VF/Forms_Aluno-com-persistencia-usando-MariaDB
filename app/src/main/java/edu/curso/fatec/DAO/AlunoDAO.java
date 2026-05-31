package edu.curso.fatec.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.curso.fatec.connection.ConnectionBD;
import edu.curso.fatec.entity.Aluno;

/*
dados do docker pra conectar 

jdbc:mariadb://localhost:3306/escola

banco: escola
usuário: root
senha: root

*/

public class AlunoDAO {
    List<Aluno> alunos = new ArrayList<>();

    public void Inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno(ra,nome,idade, nascimento) VALUES ( ?, ?, ?, ? )";

        try {
            Connection con = ConnectionBD.getConnection(); // abre a coneção com o banco
            PreparedStatement stmt = con.prepareStatement(sql); // trata a linha de comando od sql
            stmt.setString(1, aluno.getRa());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, Integer.parseInt(aluno.getIdade()));
            stmt.setDate(4, java.sql.Date.valueOf(aluno.getNascimento()));

            stmt.executeUpdate();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erro ao criar aluno no banco: " + e.getMessage());
        }

    }

    public void Atualizar(Aluno aluno) {

        String sql = "UPDATE aluno SET nome = ?, idade = ? , nascimento = ? WHERE ra = ?";

        try {
            Connection con = ConnectionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(4, aluno.getRa());
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, Integer.parseInt(aluno.getIdade()));
            stmt.setDate(3, java.sql.Date.valueOf(aluno.getNascimento()));

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erro ao atualizar aluno no banco: " + e.getMessage());
        }

    }

    public void Remover(String ra) {

        String sql = "DELETE FROM aluno WHERE ra = ?";

        try {
            Connection con = ConnectionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, ra);

            stmt.executeUpdate();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erro ao REMOVER aluno no banco: " + e.getMessage());
        }

    }

    public Aluno Pesquisar(String ra) {
        String sql = "SELECT * FROM aluno where ra = ?";

        try {
            Connection con = ConnectionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, ra);

            ResultSet pesquisado = stmt.executeQuery();

            if (pesquisado.next()) {
                System.out.println(pesquisado.getString("nome"));

                Aluno pAluno = new Aluno();

                pAluno.setRa(pesquisado.getString("ra"));
                pAluno.setNome(pesquisado.getString("nome"));
                pAluno.setIdade(String.valueOf(pesquisado.getInt("idade")));
                pAluno.setNascimento(pesquisado.getDate("nascimento").toLocalDate());

                return pAluno;
            }

            pesquisado.close();
            stmt.close();
            con.close();

            return null;

        } catch (Exception e) {
            System.out.println("erro ao PESQUISAR aluno no banco" + e.getMessage());
        }

        return null;
    }

    public List<Aluno> getAlunos() {
        String sql = "SELECT * FROM aluno";

        try {
            Connection con = ConnectionBD.getConnection();
            PreparedStatement tsmt = con.prepareStatement(sql);

            ResultSet alunosList = tsmt.executeQuery();

            alunos.clear();

            while (alunosList.next()) {
                Aluno pAluno = new Aluno();

                pAluno.setRa(alunosList.getString("ra"));
                pAluno.setNome(alunosList.getString("nome"));
                pAluno.setIdade(String.valueOf(alunosList.getInt("idade")));
                pAluno.setNascimento(alunosList.getDate("nascimento").toLocalDate());

                alunos.add(pAluno);
            }

            alunosList.close();
            tsmt.close();
            con.close();

            return alunos;

        } catch (Exception e) {
            // TODO: handle exception
        }

        return alunos;
    }

}
