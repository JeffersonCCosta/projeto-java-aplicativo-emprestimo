package br.com.trainee.tqi.aplicativoemprestimo.controller;

import br.com.trainee.tqi.aplicativoemprestimo.controller.request.EmprestimoRequest;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Emprestimo;
import br.com.trainee.tqi.aplicativoemprestimo.service.EmprestimoService;
import br.com.trainee.tqi.aplicativoemprestimo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity cadastrarEmprestimo(@RequestHeader("x-user-token") String token, @RequestBody EmprestimoRequest request) {
        boolean sessaoValida = tokenService.validarToken(token, request.getUsuarioId());
        if(sessaoValida) {
            Emprestimo emprestimo = emprestimoService.solicitarEmprestimo(request);
            if (emprestimo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @GetMapping("/user/{usuarioId}")
    public ResponseEntity buscaTodosEmprestimosPeloUsuarioId(@RequestHeader("x-user-token") String token, @PathVariable("usuarioId") int usuarioId) {
        boolean sessaoValida = tokenService.validarToken(token, usuarioId);
        if(sessaoValida) {
            List<Emprestimo> emprestimos = emprestimoService.buscaTodosEmprestimosPeloUsuarioId(usuarioId);
            if (!emprestimos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(emprestimos);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}/user/{usuarioId}")
    public ResponseEntity<Emprestimo> buscaEmprestimoPeloId(@RequestHeader("x-user-token") String token, @PathVariable("usuarioId") int usuarioId, @PathVariable("id") int id) {
        boolean sessaoValida = tokenService.validarToken(token, usuarioId);
        if(sessaoValida) {
            Emprestimo emprestimo = emprestimoService.buscarEmprestimo(id, usuarioId);
            if (emprestimo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
