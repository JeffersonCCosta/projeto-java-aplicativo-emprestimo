package br.com.trainee.tqi.aplicativoemprestimo.domain.repository;

import br.com.trainee.tqi.aplicativoemprestimo.domain.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>{

    List<Emprestimo> findAllByUsuario_Id(int usuarioId);

    Emprestimo findByIdAndUsuario_Id(int id, int usuarioId);
}
