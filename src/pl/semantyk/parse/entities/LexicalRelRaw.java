package pl.semantyk.parse.entities;

public class LexicalRelRaw {

    /**
     * Identyfiaktor rodzica w relacji leksykalnej.
     */
    private Integer parent;

    /**
     * Identyfikator rodzica w relacji leksykalnej.
     */
    private Integer child;

    /**
     * Identyfikator relacji.
     */
    private Integer relation;

    public LexicalRelRaw() {
    }

    /**
     * Konstruktor parametryzowany.
     *
     * @param parent   identyfaktor rodzica.
     * @param child  identyfikator potomka.
     * @param relation identyfikator relacji.
     */
    public LexicalRelRaw(Integer parent, Integer child, Integer relation) {
        this.parent = parent;
        this.child = child;
        this.relation = relation;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LexicalRelRaw that = (LexicalRelRaw) o;

        if (child != null ? !child.equals(that.child) : that.child != null) return false;
        if (relation != null ? !relation.equals(that.relation) : that.relation != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (child != null ? child.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LexicalRelRaw {" + "parent=" + parent + ", child=" + child + ", relation=" + relation + '}';
    }
}
