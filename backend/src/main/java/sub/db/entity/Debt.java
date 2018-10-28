package sub.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "debt")
public class Debt {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "nomer_kv")
    private String nomerKv;

    @Column(name = "famili")
    private String famili;

    @Column(name = "ls")
    private String ls;

    @Column(name = "summa")
    private String summa;

    public Debt() {
    }

    public Debt(String street, String nomerKv, String famili, String ls, String summa) {
        this.street = street;
        this.nomerKv = nomerKv;
        this.famili = famili;
        this.ls = ls;
        this.summa = summa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debt debt = (Debt) o;
        return Objects.equals(id, debt.id) &&
                Objects.equals(street, debt.street) &&
                Objects.equals(nomerKv, debt.nomerKv) &&
                Objects.equals(famili, debt.famili) &&
                Objects.equals(ls, debt.ls) &&
                Objects.equals(summa, debt.summa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, nomerKv, famili, ls, summa);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNomerKv() {
        return nomerKv;
    }

    public void setNomerKv(String nomerKv) {
        this.nomerKv = nomerKv;
    }

    public String getFamili() {
        return famili;
    }

    public void setFamili(String famili) {
        this.famili = famili;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }
}
