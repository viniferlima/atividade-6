package br.gov.sp.fatec.frases.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.frases.entity.Peca;

public interface PecaRepository extends JpaRepository<Peca, Long>{
    
    public List<Peca> findByAvioesModelo(String modelo);

    public Peca findByDescricao(String descricao);
}
