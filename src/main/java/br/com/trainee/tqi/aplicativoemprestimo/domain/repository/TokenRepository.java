package br.com.trainee.tqi.aplicativoemprestimo.domain.repository;

import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TokenRepository extends JpaRepository <Token, Integer>{

    Token findByValorAndUsuario_Id(String valor, int usuarioId);
}
