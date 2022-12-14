package br.com.allfood.restaurantesapi.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_pratos")
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    private String tag;
    @NotBlank
    private String imagem;
    @NotBlank
    private String descricao;
    @NotNull
    private Long restaurante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prato prato = (Prato) o;
        return Objects.equals(id, prato.id)
                && Objects.equals(nome, prato.nome)
                && Objects.equals(tag, prato.tag)
                && Objects.equals(imagem, prato.imagem)
                && Objects.equals(descricao, prato.descricao)
                && Objects.equals(restaurante, prato.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tag, imagem, descricao, restaurante);
    }
}
