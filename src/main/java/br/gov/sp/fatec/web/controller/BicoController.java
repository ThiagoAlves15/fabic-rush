package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.model.Bico;
import br.gov.sp.fatec.service.BicoService;
import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/bico")
@CrossOrigin
public class BicoController {
	
	@Autowired
	private BicoService bicoService;

	public void setBicoService(BicoService bicoService) {
		this.bicoService = bicoService;
	}
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	@JsonView(View.Bico.class)
	public ResponseEntity<Bico> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Bico>(bicoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Bico.class)
	public ResponseEntity<Collection<Bico>> getAll() {
		return new ResponseEntity<Collection<Bico>>(bicoService.todos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Bico.class)
	public ResponseEntity<Bico> salvar(@RequestBody Bico bico, UriComponentsBuilder uriComponentsBuilder) {
		bico = bicoService.salvar(bico);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + bico.getId()).build().toUri());
		return new ResponseEntity<Bico>(bico, responseHeaders, HttpStatus.CREATED);
	}
	
}
