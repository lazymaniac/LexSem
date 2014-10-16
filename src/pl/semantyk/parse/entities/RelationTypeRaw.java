package pl.semantyk.parse.entities;

public class RelationTypeRaw {

    private Integer id;

    private String type;

    private Integer parent;

    private String name;

    private String decription;

    private String posstr;

    private String dispaly;

    private String shortcut;

    private Boolean autoreverse;

    public RelationTypeRaw() {
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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getPosstr() {
        return posstr;
    }

    public void setPosstr(String passtr) {
        this.posstr = passtr;
    }

    public String getDispaly() {
        return dispaly;
    }

    public void setDispaly(String dispaly) {
        this.dispaly = dispaly;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public Boolean getAutoreverse() {
        return autoreverse;
    }

    public void setAutoreverse(Boolean autoreverse) {
        this.autoreverse = autoreverse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationTypeRaw that = (RelationTypeRaw) o;

        if (autoreverse != null ? !autoreverse.equals(that.autoreverse) : that.autoreverse != null) return false;
        if (decription != null ? !decription.equals(that.decription) : that.decription != null) return false;
        if (dispaly != null ? !dispaly.equals(that.dispaly) : that.dispaly != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (posstr != null ? !posstr.equals(that.posstr) : that.posstr != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (shortcut != null ? !shortcut.equals(that.shortcut) : that.shortcut != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (decription != null ? decription.hashCode() : 0);
        result = 31 * result + (posstr != null ? posstr.hashCode() : 0);
        result = 31 * result + (dispaly != null ? dispaly.hashCode() : 0);
        result = 31 * result + (shortcut != null ? shortcut.hashCode() : 0);
        result = 31 * result + (autoreverse != null ? autoreverse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RelationType{" + "id=" + id + ", type='" + type + '\'' + ", parent=" + parent + ", name='" + name + '\'' + ", decription='" + decription + '\'' + ", posstr='" + posstr + '\'' + ", dispaly='" + dispaly + '\'' + ", shortcut='" + shortcut + '\'' + ", autoreverse=" + autoreverse + '}';
    }

}
