package br.gov.sp.fatec.frases.service;

import java.util.List;

import br.gov.sp.fatec.frases.entity.Aviao;
import br.gov.sp.fatec.frases.entity.Usuario;

public interface SegurancaService {

    public Aviao novoAviao(String modelo, String prefixo, String cor, String pecas);

    public List<Aviao> buscarTodosAvioes();

    public Usuario novoUsuario(String nome, String email, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();
}