package br.ifms.edu.ifms.cadastrolistagemservicos.dao;

import br.ifms.edu.ifms.cadastrolistagemservicos.model.Servicos;
import java.util.List;

public interface IServicosDao extends IDao<Servicos> {

    List<Servicos> buscarPorNome(String nome);
}
