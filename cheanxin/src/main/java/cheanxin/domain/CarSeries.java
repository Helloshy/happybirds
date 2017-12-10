package cheanxin.domain;

import java.lang.reflect.Array;

/**
 * Created by 向麒 on 2017/4/19.
 */
public class CarSeries {
    private int id;
    private String name;
    private String full_spell;
    private String import_id;
    private int [] types;

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

    public String getImport_id() {
        return import_id;
    }

    public void setImport_id(String import_id) {
        this.import_id = import_id;
    }

    public int[] getTypes() {
        return types;
    }

    public void setTypes(int[] types) {
        this.types = types;
    }
}
