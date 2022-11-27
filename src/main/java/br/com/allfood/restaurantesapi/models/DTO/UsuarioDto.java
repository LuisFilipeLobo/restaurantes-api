package br.com.allfood.restaurantesapi.models.DTO;

import br.com.allfood.restaurantesapi.models.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Usuario} entity
 */
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto implements Serializable {
    private final Long id;
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 255, message = "O nome deve ter no mínimo 2 caracteres")
    private final String nome;
    @NotBlank(message = "Email é obrigatório")
    private final String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto that = (UsuarioDto) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email);
    }
}