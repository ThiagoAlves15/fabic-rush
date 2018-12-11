package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Bico;

public interface BicoRepository extends CrudRepository<Bico, Long> {
	
	public List<Bico> findByUsuarioNome(String nome);

}
