package br.com.unibave.unibaveposapi.service.proxy;

import br.com.unibave.unibaveposapi.entity.TabelaFrete;
import br.com.unibave.unibaveposapi.service.TabelaFreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("tabelaFreteServiceProxy")
public class TabelaFreteServiceProxy implements TabelaFreteService {

    @Autowired
    @Qualifier("tabelaFreteServiceImpl")
    private TabelaFreteService service;


    @Override
    public TabelaFrete salvar(TabelaFrete tabelaFrete)
    {

        return service.salvar(tabelaFrete);
    }

    @Override
    public TabelaFrete alterar(TabelaFrete tabelaFrete) {

        return service.alterar(tabelaFrete);
    }

    @Override
    public TabelaFrete removerPor(Integer id)
    {
        return service.removerPor(id);
    }

    @Override
    public TabelaFrete buscarPorId(Integer id)
    {
        return service.buscarPorId(id);
    }
}
