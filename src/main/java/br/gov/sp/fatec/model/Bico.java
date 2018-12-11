package br.gov.sp.fatec.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "bico")
public class Bico implements Serializable {

	private static final long serialVersionUID = -4175224450033765996L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_bico")
	@JsonView(View.Bico.class)
	private Long id;
    
    @Column(name = "titulo", length = 20, nullable = false)
    @JsonView(View.Bico.class)
    private String assunto;
    
    @Column(name = "pagamento", length = 128, nullable = true)
    @JsonView(View.Bico.class)
    private String texto;
    
    @Column(name = "descricao", length = 512, nullable = true)
    @JsonView(View.Bico.class)
    private Date dataHora;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_bico_id")
    @JsonView(View.Bico.class)
    private Usuario usuario;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
