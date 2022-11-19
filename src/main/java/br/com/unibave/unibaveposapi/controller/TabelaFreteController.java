package br.com.unibave.unibaveposapi.controller;

import br.com.unibave.unibaveposapi.controller.converter.MapConverter;
import br.com.unibave.unibaveposapi.entity.TabelaFrete;
import br.com.unibave.unibaveposapi.exception.ConverterException;
import br.com.unibave.unibaveposapi.service.TabelaFreteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/tabelas")
public class TabelaFreteController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MapConverter mapConverter;

    @Autowired
    @Qualifier("tabelaFreteServiceProxy")
    private TabelaFreteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody Map<String, Object> TabelaFreteMap)
    {
        if (TabelaFreteMap != null) {
            TabelaFrete tabelaFreteConvertida = mapper.convertValue(TabelaFreteMap, TabelaFrete.class);
            TabelaFrete tabelaFreteService = service.salvar(tabelaFreteConvertida);
            return ResponseEntity.created(URI.create("/tabelas/id/" + tabelaFreteService.getId())).build();
        }

        throw  new ConverterException("A bandeira enviada para inserção não existe");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> alterar(Map<String, Object> TabelaFreteMap)
    {
        if (TabelaFreteMap != null) {
            TabelaFrete bandeiraConvertida = mapper.convertValue(TabelaFreteMap, TabelaFrete.class);
            TabelaFrete bandeiraAtualizada = service.alterar(bandeiraConvertida);
            return ResponseEntity.ok(mapConverter.toJsonMap(bandeiraAtualizada));
        }

        throw  new ConverterException("A bandeira enviada para alteração não existe");
    }

    @DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(mapConverter.toJsonMap(service.removerPor(id)));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPorId(id)));
    }
}
