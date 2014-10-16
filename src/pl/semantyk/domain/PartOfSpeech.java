package pl.semantyk.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CZESCI_MOWY")
public class PartOfSpeech implements Serializable, Cloneable {

    private static final long serialVersionUID = -3772867366261662688L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CZESC_MOWY", nullable = false, unique = true)
    private Integer id;

    @Column(name = "CZESC_MOWY")
    private String partOfSpeech;

    @OneToMany(mappedBy = "partOfSpeech",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = Importance.class)
    private List<Importance> importance = new ArrayList<>();

    @ManyToOne(targetEntity = WikiUnit.class, optional = false)
    @JoinColumn(name = "ID_JEDNOSTKA")
    private WikiUnit wikiUnit;

    public PartOfSpeech(String partOfSpeech, WikiUnit wikiUnit) {
        this.partOfSpeech = partOfSpeech;
        this.wikiUnit = wikiUnit;
    }

    public PartOfSpeech(String partOfSpeech, List<Importance> importance, WikiUnit wikiUnit) {
        this.partOfSpeech = partOfSpeech;
        this.importance = importance;
        this.wikiUnit = wikiUnit;
    }

    public PartOfSpeech() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Importance> getImportances() {
        return importance;
    }

    public void setImportances(List<Importance> importances) {
        this.importance = importances;
    }

    public WikiUnit getWikiUnit() {
        return wikiUnit;
    }

    public void setWikiUnit(WikiUnit wikiUnit) {
        this.wikiUnit = wikiUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PartOfSpeech)) {
            return false;
        }

        PartOfSpeech that = (PartOfSpeech) o;

        if (partOfSpeech != null ? !partOfSpeech.equals(that.partOfSpeech) : that.partOfSpeech != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        return !(importance != null ? !importance.equals(that.importance) : that.importance != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (partOfSpeech != null ? partOfSpeech.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PartOfSpeech {" + "id=" + id + ", partOfSpeech='" + partOfSpeech + '\'' + ", importance=" + importance + '}';
    }
}
