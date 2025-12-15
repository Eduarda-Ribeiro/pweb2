package pweb.aula1509.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Scope("session")
@Component
public class PessoaFisica extends Pessoa implements Serializable {

    @NotBlank
    private String nome;
    private String cpf;

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getTipo() {
        return "PF";
    }
}
