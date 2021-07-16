package org.vulcan.light.javainpractice.clone;

/**
 * @author Sam Lu
 * @date 2021/7/16
 */
public class Client {

    /**
     * <li>
     * Super必须实现Cloneable，否则会抛出CloneNotSupportedException异常：<br>
     * Exception in thread "main" java.lang.CloneNotSupportedException: org.vulcan.light.javainpractice.clone.Super<br>
     * at java.lang.Object.clone(Native Method)<br>
     * at org.vulcan.light.javainpractice.clone.Super.clone(Super.java:21)<br>
     * at org.vulcan.light.javainpractice.clone.Client.main(Client.java:16)
     * </li>
     * <li>
     * JDK默认的clone方法为浅拷贝
     * </li>
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Super sup = new Super();
        sup.setName("sup1");
        Sub sub = new Sub();
        sub.setName("sub1");
        sup.setSub(sub);
        System.out.println("=============================");
        System.out.println("before clone ==>");
        System.out.println("sup name: " + sup.getName());
        System.out.println("sup.sub name: " + sup.getSub().getName());

        Super sup2 = sup.clone();
        sup2.setName("sup2");
        sup2.getSub().setName("sub2");
        System.out.println("=============================");
        System.out.println("after clone ==>");
        System.out.println("sup name: " + sup.getName());
        System.out.println("sup.sub name: " + sup.getSub().getName());
        System.out.println("sup2 name: " + sup2.getName());
        System.out.println("sup2.sub name: " + sup2.getSub().getName());
    }

}
