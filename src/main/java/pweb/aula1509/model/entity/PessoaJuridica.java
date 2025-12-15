package pweb.aula1509.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;

@Entity
public class PessoaJuridica extends Pessoa implements Serializable {

    @NotBlank
    private String razaoSocial;
    @NotBlank
    private String cnpj;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getNome() {
        return razaoSocial;
    }

    @Override
    public String getTipo() {
        return "PJ";
    }
}
