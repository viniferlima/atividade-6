package br.gov.sp.fatec.frases.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.frases.controller.View;

@Entity
@Table(name = "avi_aviao")
public class Aviao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avi_id")
    @JsonView(View.AviaoCompleto.class)
    private Long id;

    @Column(name = "avi_modelo")
    @JsonView(View.AviaoComum.class)
    private String modelo;

    @Column(name = "avi_prefixo")
    @JsonView(View.AviaoComum.class)
    private String prefixo;

    @Column(name = "avi_cor")
    @JsonView(View.AviaoComum.class)
    private String cor;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ape_aviao_peca",
    joinColumns = { @JoinColumn(name = "avi_id") },
    inverseJoinColumns = { @JoinColumn(name = "pec_id") })
    @JsonView(View.AviaoComum.class)
    private Set<Peca> pecas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aviao")
    private Set<Manutencao> manutencoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Set<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(Set<Peca> pecas) {
        this.pecas = pecas;
    }
}
