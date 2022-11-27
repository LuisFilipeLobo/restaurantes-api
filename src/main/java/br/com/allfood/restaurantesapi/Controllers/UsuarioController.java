package br.com.allfood.restaurantesapi.Controllers;

import br.com.allfood.restaurantesapi.Services.UsuarioService;
import br.com.allfood.restaurantesapi.models.entities.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1")
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

}
