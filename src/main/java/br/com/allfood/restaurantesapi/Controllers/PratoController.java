package br.com.allfood.restaurantesapi.Controllers;

import br.com.allfood.restaurantesapi.Services.ServiceException.PratoService;
import br.com.allfood.restaurantesapi.Services.ServiceException.ServiceException;
import br.com.allfood.restaurantesapi.models.entities.Prato;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class PratoController {
    private PratoService pratoService;

    @GetMapping("/pratos")
    public Page<Prato> listarTodosOsPratos(Pageable pageable) {
        return pratoService.listarTodosOsPratos(pageable);
    }

    @GetMapping("/prato/{id}")
    public Optional<Prato> buscarPratoPorId(@PathVariable Long id) {
        try {
            return pratoService.buscarPratoPorId(id);
        } catch (ServiceException e) {
            throw new ServiceException(e.getHttpStatus(), e.getMessage());
        }
    }

}

