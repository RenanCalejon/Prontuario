package br.com.fiap.prontuario.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import br.com.fiap.prontuario.models.Paciente;
import br.com.fiap.prontuario.repository.PacienteRepository;
import br.com.fiap.prontuario.repository.ProntuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    Logger log = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ProntuarioRepository prontuarioRepository;

    @GetMapping
    public List<Paciente> index(){
        return pacienteRepository.findAll();
    }

    @GetMapping("{pacienteId}")
    public ResponseEntity<Paciente> show(@PathVariable Long pacienteId){
        var paciente = pacienteRepository.findById(pacienteId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "paciente não encontrada")
        );
        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody @Valid Paciente paciente, BindingResult result){
        log.info("cadastrar paciente: " + paciente);
        pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }   

    @DeleteMapping("{pacienteId}")
    public ResponseEntity<Paciente> destroy(@PathVariable Long pacienteId){
        log.info("apagando paciente " + pacienteId);
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao apagar Paciente não encontrada")
        );
        
        pacienteRepository.delete(paciente);
            
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{pacienteId}")
    public ResponseEntity<Paciente> update(@PathVariable Long pacienteId, @RequestBody @Valid Paciente paciente, BindingResult result){
        log.info("apagar paciente com id" + pacienteId);
        var pacienteEncontrado = pacienteRepository.findById(pacienteId);

        if (pacienteEncontrado.isEmpty()) return ResponseEntity.notFound().build();

        var novoPaciente = pacienteEncontrado.get();
        BeanUtils.copyProperties(paciente, novoPaciente, "pacienteId");
        
        pacienteRepository.save(novoPaciente);
            
        return ResponseEntity.ok(novoPaciente);
  }

}



