package pweb.aula1509.model.entity;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class PessoaJuridica extends Pessoa implements Serializable {

    private String razaoSocial, cnpj;

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
