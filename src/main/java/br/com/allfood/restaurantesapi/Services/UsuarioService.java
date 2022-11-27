package br.com.allfood.restaurantesapi.Services;

import br.com.allfood.restaurantesapi.Repositories.UsuarioRepository;
import br.com.allfood.restaurantesapi.Services.ServiceException.ServiceException;
import br.com.allfood.restaurantesapi.models.entities.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.listarUsuarios(pageable);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario adicionarUsuario(Usuario usuario) {

        // Verificar se o nome contem ao menos 2 caracteres
        if (usuario.getNome().length() < 2) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "O nome deve conter pelo menos 2 caracteres");
        }

        // Impedir cadastro duplicado de email
        Usuario obj = usuarioRepository.findUsuarioByEmail(usuario.getEmail());

        if (obj != null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        // Verificar se a senha contem ao menos 8 caracteres
        if (usuario.getSenha().length() < 8) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "A senha deve conter pelo menos 8 caracteres");
        }

        // Criptografar senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }
}
