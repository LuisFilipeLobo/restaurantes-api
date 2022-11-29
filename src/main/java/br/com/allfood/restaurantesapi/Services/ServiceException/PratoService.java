package br.com.allfood.restaurantesapi.Services.ServiceException;

import br.com.allfood.restaurantesapi.Repositories.PratoRepository;
import br.com.allfood.restaurantesapi.models.entities.Prato;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PratoService {
    private PratoRepository pratoRepository;

    public Page<Prato> listarTodosOsPratos(Pageable pageable) {
        return pratoRepository.listarTodosOsPratos(pageable);
    }

}
