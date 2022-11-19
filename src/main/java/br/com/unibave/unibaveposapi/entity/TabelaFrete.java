package br.com.unibave.unibaveposapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tabela_frete")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TabelaFrete {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "nome")
    @NotEmpty(message = "O nome do titular do cartão é obrigatorio")
    @Size(max = 50, message = "O numero do titular não pode ultrapassar 50 caracteres")
    private String nome;

    @Column(name = "valor_km")
    //@Positive(message = "O valor da transação deve ser maior que zero")
    private BigDecimal valorKm;

    @Column(name = "valor_taxa")
    //@Positive(message = "O valor da transação deve ser maior que zero")
    private BigDecimal valorTaxa;

}
