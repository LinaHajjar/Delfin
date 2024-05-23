package dolphine.repository;

import dolphine.SwimCompetition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SwimCompetitionRepository {

    private static final String FILE_NAME = "src/dolphine/repository/competitions.ser";

    public static void saveCompetitions(List<SwimCompetition> competitions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(competitions);
            System.out.println("Competitions have been saved to file: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while saving competitions: " + e.getMessage());
        }
    }

    public static List<SwimCompetition> loadCompetitions() {
        List<SwimCompetition> competitions = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            competitions = (List<SwimCompetition>) ois.readObject();
            System.out.println("Competitions have been loaded from file: " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading competitions: " + e.getMessage());
        }
        return competitions;
    }
}

