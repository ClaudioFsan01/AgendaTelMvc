package br.nom.belo.marcio.agendatel.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import br.nom.belo.marcio.agendatel.modelo.Contato;
import br.nom.belo.marcio.agendatel.servico.AgendaDao;
import br.nom.belo.marcio.agendatel.visao.TelaAgenda;
import br.nom.belo.marcio.agendatel.visao.TelaEditarContato;

public class AgendaControle implements ActionListener,
    WindowListener {

    private TelaAgenda tela;
    TabelaAgendaModelo agendaModelo=new TabelaAgendaModelo();
    
    public AgendaControle() {
        
        this.tela=new TelaAgenda();
        tela.setVisible(true);

        // Adiciona os ouvintes
        tela.setJtAgendaModel(agendaModelo);
        tela.addActionListener(this);
        tela.addWindowListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String cmd=e.getActionCommand();
        if(cmd.equals("Adicionar")) {

            TelaEditarContato telaEditar=new TelaEditarContato(this.tela, true);
            telaEditar.setVisible(true);

            String nome=telaEditar.getNome();
            String telefone=telaEditar.getTelefone();

            Contato contato=new Contato(nome,telefone);

            AgendaDao dao = new AgendaDao(); 
            dao.adicionarContato( contato);

            agendaModelo.adicionarContato(contato);

        } else if(cmd.equals("Editar")) {

            int linha=tela.getLinhaSelecionada();
            if(linha!=-1) {
                
                Contato contato=agendaModelo.getContato(linha);

                String telefoneOriginal = contato.getTelefone();
                
                TelaEditarContato telaEditar=new TelaEditarContato(this.tela, true,contato);
                telaEditar.setVisible(true);

                String nome=telaEditar.getNome();
                String telefone=telaEditar.getTelefone();

                Contato contatoEditado=new Contato(nome,telefone);

                agendaModelo.setContato(contatoEditado,linha);
                new AgendaDao().alterarContato( telefoneOriginal, contatoEditado);

            } else {
                JOptionPane.showMessageDialog(tela, "Selecione um contato.");
            }
        } else if(cmd.equals("Excluir")) {
            int linha=tela.getLinhaSelecionada();
            if(linha!=-1) {
                agendaModelo.remove(linha);
            } else {
                JOptionPane.showMessageDialog(tela, "Selecione um contato.");
            }
        }
        
    }

    public void windowOpened(WindowEvent e) {
        agendaModelo.carregarContatos();
    }

    public void windowClosing(WindowEvent e) {

    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
