package br.gov.sp.fatec.frases.service;

import java.util.List;

import br.gov.sp.fatec.frases.entity.Aviao;

public interface SegurancaService {

    public Aviao novoAviao(String modelo, String prefixo, String cor, String pecas);

    public List<Aviao> buscarTodosAvioes();
}