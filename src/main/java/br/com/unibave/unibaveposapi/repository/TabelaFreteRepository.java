package br.com.unibave.unibaveposapi.repository;

import br.com.unibave.unibaveposapi.entity.TabelaFrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabelaFreteRepository extends JpaRepository<TabelaFrete, Integer> {

    @Query("SELECT tf FROM TabelaFrete tf WHERE tf.id = :id")
    TabelaFrete buscarPor(Integer id);

    @Query("SELECT tf FROM TabelaFrete tf ORDER BY tf.nome ")
    List<TabelaFrete> listarTodas();
}
