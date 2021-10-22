package br.gov.sp.fatec.frases.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.frases.entity.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    
    public List<Manutencao> findByAviaoModelo(String modelo);
}
