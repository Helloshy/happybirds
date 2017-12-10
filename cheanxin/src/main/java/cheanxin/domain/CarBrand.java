package cheanxin.domain;

/**
 * Created by 向麒 on 2017/4/19.
 */
public class CarBrand {
    private int id;
    private String name;
    private String full_spell;
    private String first_spell;
    private String icon_path;
    private String types;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_spell() {
        return full_spell;
    }

    public void setFull_spell(String full_spell) {
        this.full_spell = full_spell;
    }

    public String getFirst_spell() {
        return first_spell;
    }

    public void setFirst_spell(String first_spell) {
        this.first_spell = first_spell;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
