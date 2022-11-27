package br.com.allfood.restaurantesapi.Services;

import br.com.allfood.restaurantesapi.Repositories.UsuarioRepository;
import br.com.allfood.restaurantesapi.models.entities.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.listarUsuarios(pageable);
    }

}
