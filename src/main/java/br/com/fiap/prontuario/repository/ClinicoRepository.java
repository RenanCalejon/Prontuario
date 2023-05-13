package br.com.fiap.prontuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.prontuario.models.Clinico;

public interface ClinicoRepository extends JpaRepository<Clinico, Long> {
    
}
