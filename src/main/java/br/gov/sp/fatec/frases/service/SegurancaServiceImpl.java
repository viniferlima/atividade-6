package br.gov.sp.fatec.frases.service;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.frases.entity.Aviao;
import br.gov.sp.fatec.frases.entity.Peca;
import br.gov.sp.fatec.frases.repository.AviaoRepository;
import br.gov.sp.fatec.frases.repository.PecaRepository;

@Service
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    AviaoRepository aviaoRepo;

    @Autowired
    PecaRepository pecaRepo; 

    @org.springframework.transaction.annotation.Transactional
    public Aviao novoAviao(String modelo, String prefixo, String cor, String pecas) {
        Peca peca = pecaRepo.findByDescricao(pecas);
        if (peca == null) {
        peca = new Peca();
        peca.setCodigo("Codigo");
        peca.setTipo("Tipo");
        peca.setDescricao(pecas);
        pecaRepo.save(peca);
        }
        Aviao aviao = new Aviao();
        aviao.setModelo(modelo);
        aviao.setCor(cor);
        aviao.setPrefixo(prefixo);
        aviao.setPecas(new HashSet<Peca>());
        aviao.getPecas().add(peca);
        aviaoRepo.save(aviao);

        return aviao;
    }

    public List<Aviao> buscarTodosAvioes() {
        return aviaoRepo.findAll();
    }
}