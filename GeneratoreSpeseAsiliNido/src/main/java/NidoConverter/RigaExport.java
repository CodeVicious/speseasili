package NidoConverter;

import com.univocity.parsers.annotations.NullString;
import com.univocity.parsers.annotations.Parsed;

public class RigaExport {
	// if the value parsed in the quantity column is "?" or "-", it will be replaced
	// by null.
	@NullString(nulls = { "?", "-" })
	// if a value resolves to null, it will be converted to the String "0".
	
	

	@Parsed(field = "Comune")
	private String comune;

	@Parsed(field = "Scuola")
	private String scuola;

	@Parsed(field = "Classe")
	private String classe;

	@Parsed(field = "Persona")
	private String persona;

	@Parsed(field = "Cognome")
	private String cognome;

	@Parsed(field = "Nome")
	private String nome;

	@Parsed(field = "CodiceFiscale")
	private String codiceFiscale;
	
	@Parsed(field = "Totale Pagato")
	private String totalePagato;

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getScuola() {
		return scuola;
	}

	public void setScuola(String scuola) {
		this.scuola = scuola;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getTotalePagato() {
		return totalePagato;
	}

	public void setTotalePagato(String totalePagato) {
		this.totalePagato = totalePagato;
	}

	
}
