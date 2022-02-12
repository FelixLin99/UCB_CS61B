package Lab.lab6.capers;

import java.io.File;
import java.io.IOException;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: TODO
 * @date 2022/2/1213:17
 */
public class Test {
    public static void main(String[] args) throws IOException {
        CapersRepository.setupPersistence();
        CapersRepository.writeStory("Nice to meet you");
    }
}
