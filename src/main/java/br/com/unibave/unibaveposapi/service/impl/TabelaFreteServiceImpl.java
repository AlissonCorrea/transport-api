package br.com.unibave.unibaveposapi.service.impl;

import br.com.unibave.unibaveposapi.entity.TabelaFrete;
import br.com.unibave.unibaveposapi.exception.RegistroNaoEncontradoException;
import br.com.unibave.unibaveposapi.repository.TabelaFreteRepository;
import br.com.unibave.unibaveposapi.service.TabelaFreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("tabelaFreteServiceImpl")
public class TabelaFreteServiceImpl implements TabelaFreteService {

    @Autowired
    private TabelaFreteRepository repository;

    @Override
    public TabelaFrete salvar(TabelaFrete tabelaFrete)
    {
        return repository.save(tabelaFrete);
    }

    @Override
    public TabelaFrete alterar(TabelaFrete tabelaFrete)
    {
        this.checarExistenciaNaBaseDo(tabelaFrete.getId());
        return  repository.save(tabelaFrete);
    }

    private boolean checarExistenciaNaBaseDo(Integer idTabelaFrete)
    {
        boolean isExiste = repository.existsById(idTabelaFrete);

        if (!isExiste) {
            throw new RegistroNaoEncontradoException("Não foi encontrado tabela frete com o id informado");
        }

        return isExiste;
    }

    @Override
    public TabelaFrete removerPor(Integer id)
    {
        this.checarExistenciaNaBaseDo(id);

        TabelaFrete tabelaFrete = repository.buscarPor(id);

        this.repository.deleteById(id);

        return tabelaFrete;
    }

    @Override
    public TabelaFrete buscarPorId(Integer id)
    {
        TabelaFrete tabelaFrete = repository.buscarPor(id);

        if (tabelaFrete != null) {
            return tabelaFrete;
        }

        throw new RegistroNaoEncontradoException("Não existe tabela de frete com o id informado");
    }
}
