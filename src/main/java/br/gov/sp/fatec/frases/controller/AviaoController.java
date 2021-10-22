package br.gov.sp.fatec.frases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.frases.entity.Aviao;
import br.gov.sp.fatec.frases.service.SegurancaService;

@RestController
@CrossOrigin()
@RequestMapping(value = "/aviao")
public class AviaoController 
{
    @Autowired
    private SegurancaService segurancaService;

    @GetMapping
    @JsonView(View.AviaoComum.class)
    public List<Aviao> buscarTodosAvioes() {
       return segurancaService.buscarTodosAvioes(); 
    }

    @PostMapping
    @JsonView(View.AviaoCompleto.class)
    public Aviao novoAviao(@RequestBody Aviao aviao)
    {
        return segurancaService.novoAviao(aviao.getModelo(), aviao.getPrefixo(), aviao.getCor(), "teste");
    }
}