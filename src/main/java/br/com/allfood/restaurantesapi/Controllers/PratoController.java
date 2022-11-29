package br.com.allfood.restaurantesapi.Controllers;

import br.com.allfood.restaurantesapi.Services.PratoService;
import br.com.allfood.restaurantesapi.Services.ServiceException.ServiceException;
import br.com.allfood.restaurantesapi.models.entities.Prato;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/prato/nome")
    public List<Prato> buscarPratoPorNome(@RequestParam String nome) {
        try {
            return pratoService.buscarPratoPorNome(nome);
        } catch (ServiceException e) {
            throw new ServiceException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/prato")
    public String adicionarPrato(@RequestBody Prato prato) {
        try {
            return pratoService.adicionarPrato(prato);
        } catch (ServiceException e) {
            throw new ServiceException(e.getHttpStatus(), e.getMessage());
        }
    }

}

