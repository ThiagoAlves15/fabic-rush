package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Bico;

public interface BicoService {
	
	public Bico salvar(Bico autorizacao);
	
	public void excluir(Long idBico);
	
	public List<Bico> todos();
	
	public List<Bico> buscarPorUsuario(String nome);
	
	public Bico buscarPorId(Long idBico);

}
