package br.com.trainee.tqi.aplicativoemprestimo.service;

import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Token;
import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Usuario;
import br.com.trainee.tqi.aplicativoemprestimo.domain.repository.TokenRepository;
import br.com.trainee.tqi.aplicativoemprestimo.service.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token criarToken(Usuario usuario){
        Token token = new Token();
        token.setUsuario(usuario);
        token.setValor(RandomString.getAlphaNumericString(10));
        token.setDataExpiracao(LocalDateTime.now().plusMinutes(25));
        tokenRepository.save(token);
        return token;
    }

    public boolean validarToken(String valor, int usuarioId){
        Token token = buscarToken(valor, usuarioId);
        if (token != null && LocalDateTime.now().isBefore(token.getDataExpiracao())){
            return true;
        }
        return false;
    }

    private Token buscarToken(String valor, int usuarioId){
        Token token = tokenRepository.findByValorAndUsuario_Id(valor, usuarioId);
        return token;
    }
}
