package br.gov.sp.fatec.frases.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.frases.entity.Aviao;

public interface AviaoRepository extends JpaRepository<Aviao, Long> {
    
    public Aviao findByPrefixo(String prefixo);

    public List<Aviao> findByModeloContainsOrPrefixoContains(String modelo, String prefixo);

    public List<Aviao> findByPecasDescricao(String descricao);

    public List<Aviao> findByManutencoesProcedimento(String procedimento);
}
