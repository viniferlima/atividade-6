package br.gov.sp.fatec.frases.service;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.frases.entity.Autorizacao;
import br.gov.sp.fatec.frases.entity.Aviao;
import br.gov.sp.fatec.frases.entity.Peca;
import br.gov.sp.fatec.frases.entity.Usuario;
import br.gov.sp.fatec.frases.repository.AutorizacaoRepository;
import br.gov.sp.fatec.frases.repository.AviaoRepository;
import br.gov.sp.fatec.frases.repository.PecaRepository;
import br.gov.sp.fatec.frases.repository.UsuarioRepository;

@Service
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    AviaoRepository aviaoRepo;

    @Autowired
    PecaRepository pecaRepo; 

    @Autowired
    UsuarioRepository usuarioRepo;

    @Autowired
    AutorizacaoRepository autorizacaoRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @org.springframework.transaction.annotation.Transactional
    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public List<Aviao> buscarTodosAvioes() {
        return aviaoRepo.findAll();
    }

    @org.springframework.transaction.annotation.Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario novoUsuario(String nome, String email, String senha, String autorizacao) {
        
        Autorizacao aut = autorizacaoRepo.findByNome(autorizacao);
        if(aut == null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autorizacaoRepo.save(aut);
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);

        return usuario;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(username);
        if (usuario == null) {
        throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return User.builder().username(username).password(usuario.getSenha())
            .authorities(usuario.getAutorizacoes().stream()
                .map(Autorizacao::getNome).collect(Collectors.toList())
                .toArray(new String[usuario.getAutorizacoes().size()]))
            .build();
    }
}