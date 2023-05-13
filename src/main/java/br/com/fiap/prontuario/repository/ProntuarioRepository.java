package br.com.fiap.prontuario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.prontuario.models.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

        // @Query("SELECT d FROM Prontuario d WHERE d.diagnostico LIKE %?1%") //JPQL
        Page<Prontuario> findByProntuarioContaining(String busca, Pageable pageable);

        // @Query("SELECT d FROM Prontuario d ORDER BY d.prontuarioId LIMIT ?1 OFFSET ?2")
        // List<Prontuario> buscarPaginado(int tamanho, int offset);

    
}
