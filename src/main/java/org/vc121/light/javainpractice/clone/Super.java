package org.vc121.light.javainpractice.clone;

/**
 * @author Sam Lu
 * @date 2021/7/16
 */
public class Super implements Cloneable {

    private String name;

    private Sub sub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    @Override
    public Super clone() throws CloneNotSupportedException {
        return (Super) super.clone();
    }

}
