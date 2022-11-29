package br.com.allfood.restaurantesapi.Repositories;

import br.com.allfood.restaurantesapi.models.entities.Prato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

    @Query("SELECT obj FROM Prato obj ORDER BY obj.id")
    Page<Prato> listarTodosOsPratos(Pageable pageable);

    @Query("SELECT obj FROM Prato obj WHERE :nome LIKE LOWER(CONCAT('%',:nome,'%'))")
    List<Prato> findByNomeContaining(String nome);

}
