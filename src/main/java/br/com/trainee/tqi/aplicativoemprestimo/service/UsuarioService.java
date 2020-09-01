package br.com.trainee.tqi.aplicativoemprestimo.service;

import br.com.trainee.tqi.aplicativoemprestimo.controller.request.LoginRequest;
import br.com.trainee.tqi.aplicativoemprestimo.controller.request.UsuarioRequest;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Token;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Usuario;
import br.com.trainee.tqi.aplicativoemprestimo.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    //TODO:Implementar validação para permitir somente um unico email (não ter dois usuarios com msm email).
    public Usuario cadastrarUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setCpf(request.getCpf());
        usuario.setTelefone(request.getTelefone());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setEndereco(request.getEndereco());
        //TODO:implementrar criptografia senha
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario buscarUsuarioPeloId(int usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }
        return null;
    }

    public Token login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findFirstByEmailAndSenhaOrderByIdDesc(request.getEmail(), request.getSenha());
        if (usuario != null) {
            return tokenService.criarToken(usuario);
        }
        return null;
    }

    public boolean validarLogin(int usuarioId, String token){
        return tokenService.validarToken(token, usuarioId);
    }
}

