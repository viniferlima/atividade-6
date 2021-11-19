package br.gov.sp.fatec.frases.service;

import java.util.List;

import br.gov.sp.fatec.frases.entity.Aviao;
import br.gov.sp.fatec.frases.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SegurancaService extends UserDetailsService {

    public Aviao novoAviao(String modelo, String prefixo, String cor, String pecas);

    public List<Aviao> buscarTodosAvioes();

    public Usuario novoUsuario(String nome, String email, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();
}