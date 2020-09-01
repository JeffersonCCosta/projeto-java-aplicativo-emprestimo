package br.com.trainee.tqi.aplicativoemprestimo.domain.repository;


import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findFirstByEmailAndSenhaOrderByIdDesc(String email, String senha);
}
