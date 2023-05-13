package br.com.fiap.prontuario.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Clinico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clinicoId ;

    @NotNull(message = "nome não pode ser nulo")
    @Pattern(regexp="[a-zA-Z ]+", message="O nome deve conter apenas letras e espaços")
    private String nome;

    @NotNull(message = "cpf não pode ser nulo")
    @Pattern(regexp="\\d{11}", message="O CPF deve conter 11 dígitos")
    private String cpf;

    @NotNull(message = "data não pode ser nulo")
    private LocalDate data;

    @Email
    private String email;  

}