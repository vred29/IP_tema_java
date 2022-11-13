package human.profesor;

import human.Human;

public class Profesor extends Human {
    private String materie;

    public Profesor(String nume, String prenume, String acronim_fac, Integer varsta, String materie) {
        super(nume, prenume, acronim_fac, varsta);
        this.materie = materie;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    @Override
    public void greeting() {
        System.out.println("Buna ziua, ma numesc "+nume+" "+prenume +" si predau "+ materie+" la Facultatea " + acronim_fac);
    }

    @Override
    public void doWork() {
        System.out.println("Make hard finals, of course.");
    }

    @Override
    public String toString() {
        return "Prof. " + nume + " " + prenume + ", Fac. " + acronim_fac+", Varsta " + varsta + ", Materia predata - " + materie;
    }
}
