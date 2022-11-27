package br.com.allfood.restaurantesapi.validations;

import br.com.allfood.restaurantesapi.models.entities.Usuario;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Validar {

    public boolean validarNome(Usuario usuario) {
        String nome = usuario.getNome();

        return nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$");
    }

    public boolean validarSenha(Usuario usuario) {
        String senha = usuario.getSenha();

        return senha.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])(?=.*[@#$%&*!-+&*]).{8,}$");
    }

    public boolean validarEmail(Usuario usuario) {
        String email = usuario.getEmail();

        return email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
    }
}
