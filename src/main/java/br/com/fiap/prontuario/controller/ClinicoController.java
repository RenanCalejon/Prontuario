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

import br.com.fiap.prontuario.models.Clinico;
import br.com.fiap.prontuario.repository.ClinicoRepository;
import br.com.fiap.prontuario.repository.ProntuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clinico")
public class ClinicoController {

    Logger log = LoggerFactory.getLogger(ClinicoController.class);


    @Autowired
    ClinicoRepository clinicoRepository;

    @Autowired
    ProntuarioRepository prontuarioRepository;

    @GetMapping
    public List<Clinico> index(){
        return clinicoRepository.findAll();
    }
 
    @GetMapping("{clinicoId}")
    public ResponseEntity<Clinico> show(@PathVariable Long clinicoId){
        var clinico = clinicoRepository.findById(clinicoId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, " clinico não encontrado")
        );
        return ResponseEntity.ok(clinico);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Clinico clinico, BindingResult result){
        log.info("cadastrar clinico: " + clinico);
        clinicoRepository.save(clinico);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinico);
    }   

    @DeleteMapping("{clinicoId}")
    public ResponseEntity<Clinico> destroy(@PathVariable Long clinicoId){
        log.info("apagando clinico" + clinicoId);
        Clinico clinico = clinicoRepository.findById(clinicoId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao apagar Clinico não encontrado")
        );
        
        clinicoRepository.delete(clinico);
            
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{clinicoId}")
    public ResponseEntity<Clinico> update(@PathVariable Long clinicoId, @RequestBody @Valid Clinico clinico, BindingResult result){
        log.info("alterar clinico com id " + clinicoId);
        var clinicoEncontrado = clinicoRepository.findById(clinicoId);

        if (clinicoEncontrado.isEmpty()) return ResponseEntity.notFound().build();

        var novoClinico = clinicoEncontrado.get();
        BeanUtils.copyProperties(clinico, novoClinico, "clinicoId");
        
        clinicoRepository.save(novoClinico);
            
        return ResponseEntity.ok(novoClinico);
    }

  }


