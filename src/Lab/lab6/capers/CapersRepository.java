package Lab.lab6.capers;

import javax.rmi.CORBA.Util;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

import static Lab.lab6.capers.Utils.*;

/** A repository for Capers 
 * @author Shuhui Lin
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD.getPath(), "Lab", "lab6", ".capers");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() throws IOException {
        CAPERS_FOLDER.mkdir();
        File dogsFile = join(CAPERS_FOLDER.getPath(), "dogs");
        File storyFile = join(CAPERS_FOLDER.getPath(), "story");
        dogsFile.mkdir();
        storyFile.createNewFile();
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        File storyFile = join(CAPERS_FOLDER.getPath(), "story");
        String s = readContentsAsString(storyFile);
        writeContents(storyFile, s, text, "\n");
        System.out.println(readContentsAsString(storyFile));

    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // Assume dog names are unique
        File dogFile = join(CAPERS_FOLDER.getPath(), "dogs", name);
        Dog dog = new Dog(name, breed, age);
        Utils.writeObject(dogFile, dog);
        System.out.println(dog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        File dogFile = join(CAPERS_FOLDER.getPath(), "dogs", name);
        Dog dog = Dog.fromFile(name);
        dog.haveBirthday();
        dog.saveDog();
    }
}
