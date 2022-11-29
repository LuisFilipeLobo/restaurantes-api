package br.com.allfood.restaurantesapi.Services.ServiceException;

import br.com.allfood.restaurantesapi.Repositories.PratoRepository;
import br.com.allfood.restaurantesapi.models.entities.Prato;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PratoService {
    private PratoRepository pratoRepository;

    public Page<Prato> listarTodosOsPratos(Pageable pageable) {
        return pratoRepository.listarTodosOsPratos(pageable);
    }

    public Optional<Prato> buscarPratoPorId(Long id) {

        Optional<Prato> prato = pratoRepository.findById(id);
        if (prato.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Prato não encontrado");
        }

        return prato;
    }

    public List<Prato> buscarPratoPorNome(String nome) {
        List<Prato> prato = pratoRepository.findByNomeContaining(nome);
        if (prato.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Nenhum prato encontrado");
        }

        return prato;
    }

}
