package human;

import java.util.ArrayList;
import java.util.List;

abstract public class Human implements IHuman, Comparable<Human> {
    protected String nume;
    protected String prenume;
    protected String acronim_fac;
    protected Integer varsta;

    public Human(String nume, String prenume, String acronim_fac, Integer varsta) {
        this.nume = nume;
        this.prenume = prenume;
        this.acronim_fac = acronim_fac;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAcronim_fac() {
        return acronim_fac;
    }

    public void setAcronim_fac(String acronim_fac) {
        this.acronim_fac = acronim_fac;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    @Override
    public boolean equals(Object object) {
        boolean sameSame = false;

        if (object != null && object instanceof Human)
        {
            if(this.varsta.compareTo(((Human) object).getVarsta()) == 0 &&
                    this.nume.compareTo(((Human) object).getNume())==0 &&
                    this.prenume.compareTo(((Human) object).getPrenume())==0 )
                sameSame=true;
        }

        return sameSame;
    }

    @Override
    public int compareTo(Human o) {
        return this.varsta.compareTo(o.getVarsta());
    }

}
