package br.com.unibave.unibaveposapi.service;

import br.com.unibave.unibaveposapi.entity.TabelaFrete;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Validated
public interface TabelaFreteService {

    TabelaFrete salvar(@Valid @NotNull(message = "A tabela de frete não pode ser nula") TabelaFrete tabelaFrete);

    TabelaFrete alterar(@Valid @NotNull(message = "A tabela de frete não pode ser nula") TabelaFrete tabelaFrete);

    TabelaFrete removerPor(@Positive(message = "O id para remoção não pode ser menor que zero") Integer id);

    TabelaFrete buscarPorId(@Positive(message = "O id para busca não pode ser menor que zero") Integer id);

}
