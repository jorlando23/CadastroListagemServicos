package br.ifms.edu.ifms.cadastrolistagemservicos.model;

import br.ifms.edu.ifms.cadastrolistagemservicos.dao.IServicosDao;
import br.ifms.edu.ifms.cadastrolistagemservicos.dao.ServicosDao;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ServicosHibernateTableModel extends AbstractTableModel {

    private IServicosDao dao;
    private List<Servicos> lista;
    private String[] colunas = {"Id", "nome", "descricao", "tempoEstimado", "valor", "empresa"};

    public ServicosHibernateTableModel() {
        dao = new ServicosDao();
        lista = dao.listar();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Servicos obj = lista.get(row);
        switch (col) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getNome();
            case 2:
                return obj.getDescricao();
            case 3:
                return obj.getTempoEstimado();
            case 4:
                return obj.getValor();
            case 5:
                return obj.getEmpresa();
            default:
                return "";
        }
    }

    public void refresh(String nome) {
        lista.clear();
        lista.addAll(dao.buscarPorNome(nome));
        fireTableStructureChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 0) {
            return Long.class;
        }
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
