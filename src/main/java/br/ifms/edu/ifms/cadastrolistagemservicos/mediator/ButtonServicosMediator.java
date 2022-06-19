package br.ifms.edu.ifms.cadastrolistagemservicos.mediator;

import javax.swing.JButton;

public class ButtonServicosMediator {

    private JButton btAlterar;
    private JButton btExcluir;

    public void registerAlterar(JButton btAlterar) {
        this.btAlterar = btAlterar;
    }

    public void registerExcluir(JButton btExcluir) {
        this.btExcluir = btExcluir;
    }

    public void ativarBotoesConsulta() {
        this.btAlterar.setVisible(true);
        this.btExcluir.setVisible(true);
    }

    public void desativarBotoes() {
        this.btAlterar.setVisible(true);
        this.btExcluir.setVisible(true);
    }
}
