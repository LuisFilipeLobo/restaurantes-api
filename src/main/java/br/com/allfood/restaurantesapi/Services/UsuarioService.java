package br.com.allfood.restaurantesapi.Services;

import br.com.allfood.restaurantesapi.Repositories.UsuarioRepository;
import br.com.allfood.restaurantesapi.Services.ServiceException.ServiceException;
import br.com.allfood.restaurantesapi.models.DTO.UsuarioDto;
import br.com.allfood.restaurantesapi.models.entities.Usuario;
import br.com.allfood.restaurantesapi.validations.Validar;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private Validar validar;

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.listarUsuarios(pageable);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario adicionarUsuario(Usuario usuario) {

        // Evitar espaços em branco
        usuario.setNome(usuario.getNome().trim().replaceAll("\s{2,}+", " "));
        usuario.setEmail(usuario.getEmail().toLowerCase().trim().replaceAll("\s{2,}", " "));
        usuario.setSenha(usuario.getSenha().trim().replaceAll("\s{2,}", " "));

        // Validar nome
        if (!validar.validarNome(usuario)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Nome inválido");
        }

        // Validar email
        if (!validar.validarEmail(usuario)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Email inválido");
        }

        // Impedir cadastro duplicado de email
        Usuario obj = usuarioRepository.findUsuarioByEmail(usuario.getEmail());

        if (obj != null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        // Validar senha
        if (!validar.validarSenha(usuario)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "A senha deve conter pelo menos 8 caracteres");
        }

        // Criptografar senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Usuario alterarUsuario(Long id, @NotNull UsuarioDto dto) {

        Usuario obj = usuarioRepository.findById(id).get();

        if (!Objects.equals(obj.getEmail(), dto.getEmail())) {
            Usuario verificarSeEmailJaExiste = usuarioRepository.findUsuarioByEmail(dto.getEmail());

            if (verificarSeEmailJaExiste != null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
            }
        }

        obj.setNome(dto.getNome());
        obj.setEmail(dto.getEmail());

        return usuarioRepository.save(obj);
    }
}