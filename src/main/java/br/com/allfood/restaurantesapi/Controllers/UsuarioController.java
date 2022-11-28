package br.com.allfood.restaurantesapi.Controllers;

import br.com.allfood.restaurantesapi.Services.ServiceException.ServiceException;
import br.com.allfood.restaurantesapi.Services.UsuarioService;
import br.com.allfood.restaurantesapi.models.DTO.UsuarioDto;
import br.com.allfood.restaurantesapi.models.entities.Usuario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1")
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/usuario/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> adicionarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.adicionarUsuario(usuario), HttpStatus.CREATED);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/usuario/{id}")
    public Usuario alterarUsuario(@Valid @PathVariable Long id, @RequestBody UsuarioDto dto) {
        try {
            return usuarioService.alterarUsuario(id, dto);
        } catch (ServiceException e) {
            throw new ServiceException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/usuario/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        try {
            return usuarioService.deletarUsuario(id);
        } catch (ServiceException e) {
            throw new ServiceException(e.getHttpStatus(), e.getMessage());
        }
    }

}
