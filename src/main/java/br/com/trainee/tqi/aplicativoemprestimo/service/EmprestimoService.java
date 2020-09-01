package br.com.trainee.tqi.aplicativoemprestimo.service;

import br.com.trainee.tqi.aplicativoemprestimo.controller.request.EmprestimoRequest;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Emprestimo;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Usuario;
import br.com.trainee.tqi.aplicativoemprestimo.domain.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmprestimoService emprestimoService;

    public Emprestimo solicitarEmprestimo(EmprestimoRequest request) {
        Usuario usuario = usuarioService.buscarUsuarioPeloId(request.getUsuarioId());

        Emprestimo emprestimo = new Emprestimo();
        if (usuario != null) {
            emprestimo.setValor(request.getValor());
            emprestimo.setUsuario(usuario);
            emprestimoRepository.save(emprestimo);
        }
        return emprestimo;
    }

    public Emprestimo buscarEmprestimo(int id, int usuarioId) {
        Emprestimo emprestimo = emprestimoRepository.findByIdAndUsuario_Id(id, usuarioId);
        if (emprestimo != null) {
            return emprestimo;
        }
        return null;
    }

    public List<Emprestimo> buscaTodosEmprestimosPeloUsuarioId(int usuarioId) {
        List<Emprestimo> emprestimos = emprestimoRepository.findAllByUsuario_Id(usuarioId);
        return emprestimos;
    }
}
