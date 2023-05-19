package app;
import javax.persistence.*;

@Entity
@DiscriminatorValue("RIVISTA")
class Rivista extends ElementoCatalogo {
    public enum Periodicita {SETTIMANALE, MENSILE, SEMESTRALE}

    private Periodicita periodicità;

	public Periodicita getPeriodicità() {
		return periodicità;
	}

	public void setPeriodicità(Periodicita periodicità) {
		this.periodicità = periodicità;
	}

    
}