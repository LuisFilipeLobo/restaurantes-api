package br.com.allfood.restaurantesapi.Services;

import br.com.allfood.restaurantesapi.Repositories.PratoRepository;
import br.com.allfood.restaurantesapi.Services.ServiceException.ServiceException;
import br.com.allfood.restaurantesapi.models.entities.Prato;
import br.com.allfood.restaurantesapi.validations.Validar;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PratoService {
    private PratoRepository pratoRepository;
    private Validar validar;

    public Page<Prato> listarTodosOsPratos(Pageable pageable) {
        return pratoRepository.listarTodosOsPratos(pageable);
    }

    public Optional<Prato> buscarPratoPorId(Long id) {

        Optional<Prato> prato = pratoRepository.findById(id);
        if (prato.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Prato não encontrado");
        }

        return prato;
    }

    public List<Prato> buscarPratoPorNome(String nome) {
        List<Prato> prato = pratoRepository.findByNomeContaining(nome);
        if (prato.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Nenhum prato encontrado");
        }

        return prato;
    }

    public String adicionarPrato(Prato prato) {

        // Remover espaços em branco
        prato.setNome(validar.stringField(prato.getNome()));
        if (prato.getNome().length() < 2) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "O nome deve conter ao menos 2 caracteres");
        }

        prato.setTag(validar.stringField(prato.getTag()));
        prato.setImagem(validar.stringField(prato.getImagem()));

        prato.setDescricao(validar.stringField(prato.getDescricao()));
        if (prato.getDescricao().length() < 2) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "O prato deve conter uma descrição");
        }

        try {
            pratoRepository.save(prato);

            return prato.getNome() + " foi adicionado com sucesso";
        } catch (ServiceException e) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Não foi possível adicionar este prato");
        }
    }

}
