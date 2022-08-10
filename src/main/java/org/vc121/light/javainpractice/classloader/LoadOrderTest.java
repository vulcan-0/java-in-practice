package org.vc121.light.javainpractice.classloader;

/**
 * @author Sam Lu
 * @date 2022/08/10
 */
public class LoadOrderTest {

    private static class Instance {

        public Instance(String message) {
            System.out.println(message);
        }

    }

    private static class Parent {

        static {
            System.out.println("[Parent] 初始化静态代码块");
        }

        private static Instance instance1 = new Instance("[Parent] 初始化静态成员变量");

        {
            System.out.println("[Parent] 初始化动态代码块");
        }

        private Instance instance2 = new Instance("[Parent] 初始化普通成员变量");

        public Parent() {
            System.out.println("[Parent] 初始化构造函数");
        }

    }

    private static class Child extends Parent {

        public Child() {
            System.out.println("[Child] 初始化构造函数");
        }

        private Instance instance2 = new Instance("[Child] 初始化普通成员变量");

        {
            System.out.println("[Child] 初始化动态代码块");
        }

        private static Instance instance1 = new Instance("[Child] 初始化静态成员变量");

        static {
            System.out.println("[Child] 初始化静态代码块");
        }

    }

    public static void main(String[] args) {
        new Child();
    }

}
