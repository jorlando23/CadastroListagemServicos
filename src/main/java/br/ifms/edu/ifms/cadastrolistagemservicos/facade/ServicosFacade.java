package br.ifms.edu.ifms.cadastrolistagemservicos.facade;

import br.ifms.edu.ifms.cadastrolistagemservicos.dao.IServicosDao;
import br.ifms.edu.ifms.cadastrolistagemservicos.dao.ServicosDao;
import br.ifms.edu.ifms.cadastrolistagemservicos.model.Servicos;
import br.ifms.edu.ifms.cadastrolistagemservicos.view.TelaFormServicos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ServicosFacade {

    private IServicosDao dao;

    public ServicosFacade(IServicosDao dao) {
        this.dao = dao;
    }

    public ServicosFacade() {
        this(new ServicosDao());
    }

    public TelaFormServicos abrirFormulario(JFrame frame, ServicosFacade facade) {
        TelaFormServicos dialog = new TelaFormServicos(frame, true, facade);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        return dialog;
    }

    public TelaFormServicos editarFormulario(
            JFrame frame,
            ServicosFacade facade,
            Long id) {
        TelaFormServicos dialog = abrirFormulario(frame, facade);
        dialog.setId(id);
        return dialog;
    }

    public void carregarDados(
            Long id,
            JTextField txtId,
            JTextField txtNome,
            JTextField txtDescricao,
            JTextField txtTempoEstimado,
            JTextField txtValor,
            JTextField txtEmpresa) {
        Servicos a = dao.buscarPorId(id);
        txtId.setText(a.getId().toString());
        txtNome.setText(a.getNome());
        txtDescricao.setText(a.getDescricao());
        txtTempoEstimado.setText(a.getTempoEstimado());
        txtValor.setText(a.getValor());
        txtEmpresa.setText(a.getEmpresa());
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtDescricao,
            JTextField txtTempoEstimado,
            JTextField txtValor,
            JTextField txtEmpresa) {
        boolean isId = txtId.getText().matches("\\d+");
        Long id = isId ? Long.parseLong(txtId.getText()) : null;

        Servicos servicos = new Servicos();
        servicos.setId(id);
        servicos.setNome(txtNome.getText());
        servicos.setDescricao(txtDescricao.getText());
        servicos.setTempoEstimado(txtTempoEstimado.getText());
        servicos.setValor(txtValor.getText());
        servicos.setEmpresa(txtEmpresa.getText());
        if (!isId) {
            dao.inserir(servicos);
        } else {
            dao.alterar(servicos);
        }

        return Boolean.TRUE;
    }

    public Boolean excluir(JFrame frame, Long id) {
        if (JOptionPane.showConfirmDialog(frame, "Deseja excluir?",
                "Excluir", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            dao.excluir(id);

            JOptionPane.showMessageDialog(frame, "Excluído com sucesso!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

}
