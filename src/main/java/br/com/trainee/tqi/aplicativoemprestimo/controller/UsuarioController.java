package br.com.trainee.tqi.aplicativoemprestimo.controller;

import br.com.trainee.tqi.aplicativoemprestimo.controller.request.LoginRequest;
import br.com.trainee.tqi.aplicativoemprestimo.controller.request.UsuarioRequest;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Token;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Usuario;
import br.com.trainee.tqi.aplicativoemprestimo.service.TokenService;
import br.com.trainee.tqi.aplicativoemprestimo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrarUsuario (@RequestBody UsuarioRequest request){
        Usuario usuario = usuarioService.cadastrarUsuario(request);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){
        Token token = usuarioService.login(request);
        if(token != null){
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
