package br.com.allfood.restaurantesapi.Repositories;

import br.com.allfood.restaurantesapi.models.entities.Prato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

    @Query("SELECT obj FROM Prato obj ORDER BY obj.id")
    Page<Prato> listarTodosOsPratos(Pageable pageable);

}
