package org.vc121.light.javainpractice.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sam Lu
 * @date 2022/08/10
 */
public class TryWithResourceTest {

    public static void main(String[] args) {
        FileReader reader = null;
        try {
            reader = new FileReader("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (
                FileReader reader2 = new FileReader("");
                FileReader reader3 = new FileReader("")
        ) {
            reader2.read();
            reader3.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
