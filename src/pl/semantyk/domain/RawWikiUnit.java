package pl.semantyk.domain;

import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;

@Table(name = "RAW_WIKI_UNIT")
public class RawWikiUnit implements Serializable, Cloneable {

    private static final long serialVersionUID = -9142664330049049624L;

    @Id
    @Column(name = "ID_RAW_UNIT")
    private Integer id = IdGenerator.getId(this.getClass());

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawWikiUnit that = (RawWikiUnit) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RawWikiUnit{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}