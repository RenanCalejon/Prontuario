package br.com.fiap.prontuario.controller;

import java.util.List;

import org.hibernate.engine.spi.Resolution;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.fiap.prontuario.models.Prontuario;
import br.com.fiap.prontuario.repository.ProntuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/prontuario")
@Slf4j
public class ProntuarioController {

    @Autowired
    ProntuarioRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String diagnostico, @PageableDefault(size=5) Pageable pageable){

        Page<Prontuario> prontuarios = (diagnostico == null)?
            repository.findAll(pageable):
            repository.findByProntuarioContaining(diagnostico, pageable);

        return assembler.toModel(prontuarios.map(Prontuario::toEntityModel));
    }


    @GetMapping("{prontuarioId}")
    public EntityModel<Prontuario> show(@PathVariable Long prontuarioId) {
        log.info("buscando prontuario com id " + prontuarioId);
        return getProntuario(prontuarioId).toEntityModel();
    }

    @PostMapping ResponseEntity<Object> create(@RequestBody @Valid Prontuario prontuario){
        log.info("cadastrar prontuario: " + prontuario);
        repository.save(prontuario);
        return ResponseEntity
            .created(prontuario.toEntityModel().getRequiredLink("self").toUri())
            .body(prontuario.toEntityModel());
    }   

    @DeleteMapping("{prontuarioId}")
    public ResponseEntity<Prontuario> destroy(@PathVariable Long prontuarioId){
        log.info("apagando prontuario " + prontuarioId);
        repository.delete(getProntuario(prontuarioId));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{prontuarioId}")
    public EntityModel<Prontuario> update(@PathVariable Long prontuarioId, @RequestBody @Valid Prontuario prontuario){
        log.info("atualizando prontuario " + prontuarioId);
        getProntuario(prontuarioId);
        prontuario.setProntuarioId(prontuarioId);
        repository.save(prontuario);
        return prontuario.toEntityModel();
    }


    private Prontuario getProntuario(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuario não encontrado")
        );
   }

}
