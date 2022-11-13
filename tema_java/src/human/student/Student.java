package human.student;

import human.Human;

public class Student extends Human {
    private Integer an_studiu;

    public Student(String nume, String prenume, String acronim_fac, Integer varsta, Integer an_studiu) {
        super(nume, prenume, acronim_fac, varsta);
        this.an_studiu = an_studiu;
    }

    public Integer getAn_studiu() {
        return an_studiu;
    }

    public void setAn_studiu(Integer an_studiu) {
        this.an_studiu = an_studiu;
    }

    @Override
    public void greeting() {
        System.out.println("Buna ziua, ma numesc "+nume+" "+prenume+"si sunt student la Facultatea "+acronim_fac+", anul "+an_studiu+" de studiu.");
    }

    @Override
    public void doWork() {
        if(an_studiu.equals(4)){
            System.out.println("Do licenta.");
        }
        else{
            System.out.println("Gg ez");
        }
    }

    @Override
    public String toString() {
        return "Sd. " + nume + " "+prenume+", Anul "+an_studiu +", Fac. "+ acronim_fac + ", Varsta " +varsta;
    }
}
