package br.com.fiap.prontuario.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.prontuario.models.Clinico;
import br.com.fiap.prontuario.models.Paciente;
import br.com.fiap.prontuario.models.Prontuario;
import br.com.fiap.prontuario.repository.ClinicoRepository;
import br.com.fiap.prontuario.repository.PacienteRepository;
import br.com.fiap.prontuario.repository.ProntuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    ProntuarioRepository prontuarioRepository;
    
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ClinicoRepository clinicoRepository;

    @Override
    public void run(String... args) throws Exception {

        Clinico c1 = new Clinico(null, "João Silva", "12345678900", LocalDate.now(), "joao.silva@email.com");
        Clinico c2 = new Clinico(null, "Maria Santos", "98765432100", LocalDate.now(), "maria.santos@email.com");
        Clinico c3 = new Clinico(null, "Pedro Souza", "11122233344", LocalDate.now(), "pedro.souza@email.com");
        clinicoRepository.saveAll(List.of(c1, c2, c3));

        Paciente p1 = new Paciente(null, "Ana Luiza", "12345678900", LocalDate.now(), "ana.luiza@email.com", "12345678");
        Paciente p2 = new Paciente(null, "João Paulo", "98765432100", LocalDate.now(), "joao.paulo@email.com", "87654321");
        Paciente p3 = new Paciente(null, "Maria Clara", "11122233344", LocalDate.now(), "maria.clara@email.com", "11112222");
        pacienteRepository.saveAll(List.of(p1, p2, p3));
        
        prontuarioRepository.saveAll(List.of(
            Prontuario.builder().prontuarioId(null).nomePaciente("Ana Luiza").sintomas("Dor de Cabeça").diagnostico("Enxaqueca").medicamentos("Dipirona").paciente(p1).clinico(c1).build(),
            Prontuario.builder().prontuarioId(null).nomePaciente("Ana Luiza").sintomas("Dor Muscular").diagnostico("Estresse").medicamentos("Tandrilax").paciente(p1).clinico(c2).build(),
            Prontuario.builder().prontuarioId(null).nomePaciente("Ana Luiza").sintomas("Dor de barriga").diagnostico("Gases").medicamentos("Luftal").paciente(p1).clinico(c3).build(),
            Prontuario.builder().prontuarioId(null).nomePaciente("João Paulo").sintomas("Dor de Cabeça").diagnostico("Ansiedade").medicamentos("Setralina").paciente(p2).clinico(c1).build(),
            Prontuario.builder().prontuarioId(null).nomePaciente("João Paulo").sintomas("Dor de garganta").diagnostico("Amigdalite").medicamentos("Amoxicilina").paciente(p2).clinico(c2).build(),
            Prontuario.builder().prontuarioId(null).nomePaciente("Maria Clara").sintomas("Febre alta").diagnostico("Infecção").medicamentos("Paracetamol").paciente(p3).clinico(c3).build()
        ));
    }
}


