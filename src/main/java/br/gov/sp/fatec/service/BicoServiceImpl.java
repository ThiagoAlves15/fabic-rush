package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Bico;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.BicoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("bicoService")
@Transactional
public class BicoServiceImpl implements BicoService {

	@Autowired
	private BicoRepository bicoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void setBicoRepo(BicoRepository bicoRepo) {
		this.bicoRepo = bicoRepo;
	}
	
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Bico salvar(Bico bico) {
		if(bico.getUsuario() != null) {
			Usuario usuario = bico.getUsuario();
			if(usuario.getId() != null) {
				usuario = usuarioRepo.findById(usuario.getId()).get();
			}
			else {
				usuario = usuarioRepo.save(usuario);
			}
		}
		bico.setDataHora(new Date());
		return bicoRepo.save(bico);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void excluir(Long idBico) {
		bicoRepo.deleteById(idBico);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Bico> todos() {
		List<Bico> retorno = new ArrayList<Bico>();
		for(Bico bico: bicoRepo.findAll()) {
			retorno.add(bico);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Bico> buscarPorUsuario(String nome) {
		if(nome == null || nome.isEmpty()) {
			return todos();
		}
		return bicoRepo.findByUsuarioNome(nome);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Bico buscarPorId(Long idBico) {
		Optional<Bico> bico = bicoRepo.findById(idBico);
		if(bico.isPresent()) {
			return bico.get();
		}
		return null;
	}

}
