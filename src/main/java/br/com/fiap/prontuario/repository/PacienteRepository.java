package br.com.fiap.prontuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.prontuario.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
