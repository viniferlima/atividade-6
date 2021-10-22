package br.gov.sp.fatec.frases;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frases.entity.Aviao;
import br.gov.sp.fatec.frases.entity.Manutencao;
import br.gov.sp.fatec.frases.entity.Peca;
import br.gov.sp.fatec.frases.repository.AviaoRepository;
import br.gov.sp.fatec.frases.repository.ManutencaoRepository;
import br.gov.sp.fatec.frases.repository.PecaRepository;
import br.gov.sp.fatec.frases.service.SegurancaService;

@SpringBootTest
@Transactional
@Rollback
class FrasesApplicationTests {

	@Autowired
	private AviaoRepository aviaoRepo;

	@Autowired
	private PecaRepository pecaRepo;

	@Autowired
	private ManutencaoRepository manutencaoRepo;

	@Autowired
	private SegurancaService segurancaService;

	@Test
	void contextLoads() {
	}

	@Test
	void findByPrefixoTest() 
	{
		Aviao aviao = new Aviao();
		aviao.setModelo("Boeing 737");
		aviao.setPrefixo("PT");
		aviao.setCor("azul");

		aviaoRepo.save(aviao);

		assertNotNull(aviaoRepo.findByPrefixo("PT"));
	}

	@Test
	void findByModeloContainsOrPrefixoContainsTest() {

		Aviao aviao = new Aviao();
		aviao.setModelo("Boeing 737");
		aviao.setPrefixo("PT");
		aviao.setCor("azul");

		aviaoRepo.save(aviao);

		assertFalse(aviaoRepo.findByModeloContainsOrPrefixoContains("bo", "testesteteste").isEmpty());
	}

	@Test
	void findByPecasDescricaoTest() {

		Peca peca = new Peca();
		peca.setDescricao("Teste");
		peca.setCodigo("Teste");
		peca.setTipo("Teste");
		pecaRepo.save(peca);

		Aviao aviao = new Aviao();
		aviao.setModelo("Teste");
		aviao.setPrefixo("PT");
		aviao.setCor("azul");
		aviao.setPecas(new HashSet<Peca>());
		aviao.getPecas().add(peca);
		aviaoRepo.save(aviao);

		assertFalse(aviaoRepo.findByPecasDescricao("Teste").isEmpty());
	}

	@Test
	void findByAvioesModeloTest() {

		Peca peca = new Peca();
		peca.setDescricao("Teste");
		peca.setCodigo("Teste");
		peca.setTipo("Teste");
		pecaRepo.save(peca);

		Aviao aviao = new Aviao();
		aviao.setModelo("Teste");
		aviao.setPrefixo("PT");
		aviao.setCor("azul");
		aviao.setPecas(new HashSet<Peca>());
		aviao.getPecas().add(peca);
		aviaoRepo.save(aviao);

		assertFalse(pecaRepo.findByAvioesModelo("Teste").isEmpty());
	}

	@Test
	void findByAviaoModeloTest() {

		Aviao aviao = new Aviao();
		aviao.setModelo("Teste");
		aviao.setPrefixo("PT");
		aviao.setCor("azul");
		aviaoRepo.save(aviao);

		Manutencao manutencao = new Manutencao();
		manutencao.setProcedimento("Teste");
		manutencao.setData(new Date());
		manutencao.setAviao(aviao);
		manutencaoRepo.save(manutencao);

		assertFalse(manutencaoRepo.findByAviaoModelo("Teste").isEmpty());
	}

	@Test
	void findByManutencoesProcedimentoTest() {

		Aviao aviao = new Aviao();
		aviao.setModelo("Teste");
		aviao.setPrefixo("PT");
		aviao.setCor("azul");
		aviaoRepo.save(aviao);

		Manutencao manutencao = new Manutencao();
		manutencao.setProcedimento("Teste");
		manutencao.setData(new Date());
		manutencao.setAviao(aviao);
		manutencaoRepo.save(manutencao);

		assertFalse(aviaoRepo.findByManutencoesProcedimento("Teste").isEmpty());
	}

	@Test
	void novoAviaoTest() 
	{
		segurancaService.novoAviao("Teste", "teste", "teste", "TESTE");
		assertNotNull(aviaoRepo.findByPecasDescricao("PT"));
	}

}
