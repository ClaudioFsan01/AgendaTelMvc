package br.nom.belo.marcio.agendatel.servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.nom.belo.marcio.agendatel.modelo.Contato;

public class AgendaDao {

    public void adicionarContato(Contato contato) {

        try {

            Connection con = Banco.getConexao();

            PreparedStatement ps = con.prepareStatement("insert into agenda (nome, telefone) values (?,?)");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.executeUpdate();
            ps.close();

        } catch(Exception exception) {

            throw new RuntimeException( exception);
        }
    }

    public List<Contato> obterTodos() {

        try {
            Connection con = Banco.getConexao();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from agenda");
            List<Contato> lista = new ArrayList<>();
            while( rs.next()) {
    
                Contato contato = new Contato( rs.getString(1), rs.getString(2));
                lista.add( contato);
            }
            return lista;
        } catch(Exception exception) {

            throw new RuntimeException( exception);
        }
    }

    public void excluir(Contato contato) {

        try {

            Connection con = Banco.getConexao();
            PreparedStatement ps = con.prepareStatement("delete from agenda where telefone=?");
            ps.setString(1, contato.getTelefone());
            ps.executeUpdate();
            ps.close();
        } catch(Exception exception) {

            throw new RuntimeException( exception);
        }
    }

    public void alterarContato(String telefoneOriginal, Contato contato) {

        try {

            Connection con = Banco.getConexao();
            PreparedStatement ps = con.prepareStatement("update agenda set nome=?,telefone=? where telefone=?");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, telefoneOriginal);
            ps.executeUpdate();
            ps.close();
        } catch(Exception exception) {

            throw new RuntimeException( exception);
        }
    }
}
