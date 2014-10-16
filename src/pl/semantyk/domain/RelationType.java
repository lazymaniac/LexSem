package pl.semantyk.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Encja przechowuje informacje o typach relacji pomiędzy jednostkami słownikowymi i synsetami.
 */
@Entity
@Table(name = "TYP_RELACJI")
public class RelationType implements Serializable, Cloneable {

    private static final long serialVersionUID = -8785373060142981328L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TYP_RELACJI", nullable = false, unique = true)
    private Integer id;

    /**
     * Typ relacji.
     */
    @Column(name = "TYP")
    private String type;

    /**
     * Rodzic relacji. Relacje mogą po sobie dziedziczyć.
     */
    @Column(name = "RODZIC")
    private Integer parent;

    /**
     * Nazwa relacji.
     */
    @Column(name = "NAZWA", nullable = false, length = 255)
    private String name;

    /**
     * Opis relacji.
     */
    @Column(name = "OPIS", nullable = false, length = 2048)
    private String description;

    /**
     * Pole przchowuje informacje jakich rodzajów jednostek dotyczy (rzeczownik, przymiotnik, czasownik itd.)
     */
    @Column(name = "POSSTR", nullable = true, length = 45)
    private String posstr;

    /**
     * Dane do wyświetlenia.
     */
    @Column(name = "WYSWIETL", nullable = true, length = 255)
    private String display;

    /**
     * Skrót relacji.
     */
    @Column(name = "SKROT", nullable = true, length = 45)
    private String abbreviation;

    /**
     * Czy jest relacją zwrotną.
     */
    @Column(name = "AUTOODWRACANIE")
    private Boolean autorevert;

    /*@OneToMany(mappedBy = "relacja")
    private List<RelacjaLeksykalna> relacjeLeksykalne = new ArrayList<>();

    @OneToMany(mappedBy = "relacja")
    private List<RelacjaSynsetu> relacjeSynsetu = new ArrayList<>();*/

    public RelationType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public String getPosstr() {
        return posstr;
    }

    public void setPosstr(String passtr) {
        this.posstr = passtr;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String dispaly) {
        this.display = dispaly;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String shortcut) {
        this.abbreviation = shortcut;
    }

    public Boolean getAutoOdwracanie() {
        return autorevert;
    }

    public void setAutoOdwracanie(Boolean autoreverse) {
        this.autorevert = autoreverse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationType that = (RelationType) o;

        if (autorevert != null ? !autorevert.equals(that.autorevert) : that.autorevert != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (display != null ? !display.equals(that.display) : that.display != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (posstr != null ? !posstr.equals(that.posstr) : that.posstr != null) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (posstr != null ? posstr.hashCode() : 0);
        result = 31 * result + (display != null ? display.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (autorevert != null ? autorevert.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RelationType{" + "id=" + id + ", type='" + type + '\'' + ", parent=" + parent + ", name='" + name + '\'' + ", decription='" + description + '\'' + ", posstr='" + posstr + '\'' + ", dispaly='" + display + '\'' + ", shortcut='" + abbreviation + '\'' + ", autoreverse=" + autorevert + '}';
    }

}
