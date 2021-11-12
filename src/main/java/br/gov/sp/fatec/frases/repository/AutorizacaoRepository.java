package br.gov.sp.fatec.frases.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.frases.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{

    public List<Autorizacao> findByUsuariosNome(String nome);

    public Autorizacao findByNome(String nome);
    
}
