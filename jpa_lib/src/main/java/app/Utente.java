package app;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "utenti")
class Utente {
    @Id
    private String numeroTessera;
    private String nome;
    private String cognome;

    @Temporal(TemporalType.DATE)
    private Date dataNascita;

	public String getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

 
}