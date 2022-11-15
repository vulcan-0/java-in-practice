package org.vc121.light.javainpractice.annotation;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;

/**
 * @author Sam Lu
 * @date 2022/11/15
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Sub sub = AnnotationUtils.findAnnotation(A.class, Sub.class);
        System.out.println("AnnotationUtils.findAnnotation: " + sub);
        Super sup = AnnotationUtils.findAnnotation(A.class, Super.class);
        System.out.println("AnnotationUtils.findAnnotation: " + sup);

        Annotation[] as = A.class.getAnnotations();
        for (Annotation a : as) {
            System.out.println("class.getAnnotations: " + a);
        }

        Annotation[] as2 = A.class.getDeclaredAnnotations();
        for (Annotation a : as2) {
            System.out.println("class.getDeclaredAnnotations: " + a);
        }

        Sub sub2 = A.class.getAnnotation(Sub.class);
        System.out.println("class.getAnnotation: " + sub2);
        Super sup2 = A.class.getAnnotation(Super.class);
        System.out.println("class.getAnnotation: " + sup2);

        Sub sub3 = A.class.getDeclaredAnnotation(Sub.class);
        System.out.println("class.getDeclaredAnnotation: " + sub3);
        Super sup3 = A.class.getDeclaredAnnotation(Super.class);
        System.out.println("class.getDeclaredAnnotation: " + sup3);
    }

}
