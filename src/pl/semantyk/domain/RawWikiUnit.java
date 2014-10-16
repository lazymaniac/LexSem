package pl.semantyk.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RAW_WIKI_UNIT")
public class RawWikiUnit implements Serializable, Cloneable {

    private static final long serialVersionUID = -9142664330049049624L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RAW_UNIT", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAZWA")
    private String title;

    @Column(name = "BODY")
    private String text;

    public RawWikiUnit() {
    }

    /**
     * @param title nazwa jednostki leksykalnej.
     * @param text  nie sparsowana definicja jednostki leksykalnej.
     */
    public RawWikiUnit(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getText() {
        return text;
    }

    public final void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RawWikiUnit{" + "id=" + id + ", title='" + title + '\'' + ", text='" + text + '\'' + '}';
    }
}