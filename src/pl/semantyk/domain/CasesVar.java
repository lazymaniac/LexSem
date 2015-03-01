package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.enums.CasesType;

import java.io.Serializable;

@Table(name = "PRZYPADKI")
public class CasesVar implements Serializable, Cloneable {

    private static final long serialVersionUID = 2963924627986179495L;

    @Id
    @Column(name = "ID_PRZYPADEK")
    private Integer id = IdGenerator.getId(this.getClass());

    @Column(name = "TYP_ODMIANY")
    private CasesType typ;

    @Column(name = "MIANOWNIK")
    private String mianownik;

    @Column(name = "DOPELNIACZ")
    private String dopelniacz;

    @Column(name = "CELOWNIK")
    private String celownik;

    @Column(name = "BIERNIK")
    private String biernik;

    @Column(name = "NARZEDNIK")
    private String narzednik;

    @Column(name = "MIEJSCOWNIK")
    private String miejscownik;

    @Column(name = "WOLACZ")
    private String wolacz;

    @Column(name = "ID_RZECZOWNIK_ODM")
    private Integer nounVar = -1;

    @Column(name = "ID_PRZYM_ODM")
    private Integer adjectiveDegreeVar = -1;

    public CasesVar() {
    }

    public CasesVar(CasesVar casesVar) {
        this.typ = casesVar.getTyp();
        this.mianownik = casesVar.getMianownik();
        this.dopelniacz = casesVar.getDopelniacz();
        this.celownik = casesVar.getCelownik();
        this.biernik = casesVar.getBiernik();
        this.narzednik = casesVar.getNarzednik();
        this.miejscownik = casesVar.getMiejscownik();
        this.wolacz = casesVar.getWolacz();
        this.nounVar = casesVar.getNounVar();
        this.adjectiveDegreeVar = casesVar.getAdjectiveDegreeVar();
    }

    public CasesVar(CasesType typ) {
        this.typ = typ;
    }

    /**
     * @param mianownik   mianownik.
     * @param dopelniacz  dopelniacza.
     * @param celownik    celownik.
     * @param biernik     biernik.
     * @param narzednik   narzednik.
     * @param miejscownik miejscownik.
     * @param wolacz      wolcza.
     */
    public CasesVar(String mianownik, String dopelniacz, String celownik,
                     String biernik, String narzednik, String miejscownik,
                     String wolacz) {
        this.mianownik = mianownik;
        this.dopelniacz = dopelniacz;
        this.celownik = celownik;
        this.biernik = biernik;
        this.narzednik = narzednik;
        this.miejscownik = miejscownik;
        this.wolacz = wolacz;
    }

    public CasesType getTyp() {
        return typ;
    }

    public void setTyp(CasesType typ) {
        this.typ = typ;
    }

    public final String getMianownik() {
        return mianownik;
    }

    public final void setMianownik(String mianownik) {
        this.mianownik = mianownik;
    }

    public final String getDopelniacz() {
        return dopelniacz;
    }

    public final void setDopelniacz(String dopelniacz) {
        this.dopelniacz = dopelniacz;
    }

    public final String getCelownik() {
        return celownik;
    }

    public final void setCelownik(String celownik) {
        this.celownik = celownik;
    }

    public final String getBiernik() {
        return biernik;
    }

    public final void setBiernik(String biernik) {
        this.biernik = biernik;
    }

    public final String getNarzednik() {
        return narzednik;
    }

    public final void setNarzednik(String narzednik) {
        this.narzednik = narzednik;
    }

    public final String getMiejscownik() {
        return miejscownik;
    }

    public final void setMiejscownik(String miejscownik) {
        this.miejscownik = miejscownik;
    }

    public final String getWolacz() {
        return wolacz;
    }

    public final void setWolacz(String wolacz) {
        this.wolacz = wolacz;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNounVar() {
        return nounVar;
    }

    public void setNounVar(Integer nounVar) {
        this.nounVar = nounVar;
    }

    public Integer getAdjectiveDegreeVar() {
        return adjectiveDegreeVar;
    }

    public void setAdjectiveDegreeVar(Integer adjectiveDegreeVar) {
        this.adjectiveDegreeVar = adjectiveDegreeVar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasesVar przypadki = (CasesVar) o;

        if (biernik != null ? !biernik.equals(przypadki.biernik) : przypadki.biernik != null) return false;
        if (celownik != null ? !celownik.equals(przypadki.celownik) : przypadki.celownik != null) return false;
        if (dopelniacz != null ? !dopelniacz.equals(przypadki.dopelniacz) : przypadki.dopelniacz != null) return false;
        if (id != null ? !id.equals(przypadki.id) : przypadki.id != null) return false;
        if (mianownik != null ? !mianownik.equals(przypadki.mianownik) : przypadki.mianownik != null) return false;
        if (miejscownik != null ? !miejscownik.equals(przypadki.miejscownik) : przypadki.miejscownik != null)
            return false;
        if (narzednik != null ? !narzednik.equals(przypadki.narzednik) : przypadki.narzednik != null) return false;
        if (typ != przypadki.typ) return false;
        return !(wolacz != null ? !wolacz.equals(przypadki.wolacz) : przypadki.wolacz != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (typ != null ? typ.hashCode() : 0);
        result = 31 * result + (mianownik != null ? mianownik.hashCode() : 0);
        result = 31 * result + (dopelniacz != null ? dopelniacz.hashCode() : 0);
        result = 31 * result + (celownik != null ? celownik.hashCode() : 0);
        result = 31 * result + (biernik != null ? biernik.hashCode() : 0);
        result = 31 * result + (narzednik != null ? narzednik.hashCode() : 0);
        result = 31 * result + (miejscownik != null ? miejscownik.hashCode() : 0);
        result = 31 * result + (wolacz != null ? wolacz.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CasesVar{");
        sb.append("id=").append(id);
        sb.append(", typ=").append(typ);
        sb.append(", mianownik='").append(mianownik).append('\'');
        sb.append(", dopelniacz='").append(dopelniacz).append('\'');
        sb.append(", celownik='").append(celownik).append('\'');
        sb.append(", biernik='").append(biernik).append('\'');
        sb.append(", narzednik='").append(narzednik).append('\'');
        sb.append(", miejscownik='").append(miejscownik).append('\'');
        sb.append(", wolacz='").append(wolacz).append('\'');
        sb.append(", nounVar=").append(nounVar);
        sb.append(", adjectiveDegreeVar=").append(adjectiveDegreeVar);
        sb.append('}');
        return sb.toString();
    }
}
