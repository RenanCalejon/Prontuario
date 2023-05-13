package br.com.fiap.prontuario.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.prontuario.controller.ProntuarioController;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prontuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prontuarioId;

    @NotNull(message = "nome não pode ser nulo")
    @Pattern(regexp="[a-zA-Z ]+", message="O nome deve conter apenas letras e espaços")
    private String nomePaciente;

    @NotBlank @Size(min = 5, max = 255)
    private String sintomas;

    @NotBlank @Size(min = 5, max = 255)
    private String diagnostico;

    @NotBlank @Size(min = 5, max = 255)
    private String medicamentos;

    @ManyToOne
    private Paciente paciente;

    @ManyToMany
    private Clinico clinico;

        public EntityModel<Prontuario> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(ProntuarioController.class).show(prontuarioId)).withSelfRel(),
            linkTo(methodOn(ProntuarioController.class).destroy(prontuarioId)).withRel("delete"),
            linkTo(methodOn(ProntuarioController.class).index(null, Pageable.unpaged())).withRel("all")
            );
    }
}
