package dolphine.repository;

import dolphine.SwimCompetition;

import java.io.*;
import java.util.ArrayList;

public class SwimCompetitionRepository {

    private static final String filePath = "src/dolphine/repository/competitionFile.ser";

    // skriver konkurrence til en fil
    public static void createCompetition(SwimCompetition competition) {
        ArrayList<SwimCompetition> competitionList = getCompetitionList();
        competitionList.add(competition);
        saveCompetitionList(competitionList);
    }

    // Opdatere konkurrence-listen
    public static void updateCompetition(SwimCompetition updatedCompetition) {
        ArrayList<SwimCompetition> competitionList = getCompetitionList();
        for (SwimCompetition sc : competitionList) {
            if (sc.getCompetitionName().equals(updatedCompetition.getCompetitionName())) {
                sc.setDate(updatedCompetition.getDate());
                sc.setLocation(updatedCompetition.getLocation());
                sc.clearSwimDisciplines();
                sc.getSwimDisciplines().addAll(updatedCompetition.getSwimDisciplines());
                sc.clearParticipants();
                sc.getParticipants().addAll(updatedCompetition.getParticipants());
                sc.setCategory(updatedCompetition.getCategory());
            }
        }
        saveCompetitionList(competitionList);
    }

    public static void deleteCompetition(SwimCompetition competitionToDelete) {
        ArrayList<SwimCompetition> competitionList = getCompetitionList();
        competitionList.removeIf(sc -> sc.getCompetitionName().equals(competitionToDelete.getCompetitionName()));
        saveCompetitionList(competitionList);
    }

    public static ArrayList<SwimCompetition> getCompetitionList() {
        try {
            File file = new File(filePath);

            // Checker hvis filen er tom
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<SwimCompetition> loadedCompetitions = (ArrayList<SwimCompetition>) objectInputStream.readObject();
            return loadedCompetitions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // gemmer opdateringerne
    public static void saveCompetitionList(ArrayList<SwimCompetition> competitionList) {
        try {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outPutStream.writeObject(competitionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SwimCompetition> getCompetitionListByName(String name) {
        ArrayList<SwimCompetition> competitionList = getCompetitionList();
        ArrayList<SwimCompetition> competitionListByName = new ArrayList<>();
        for (SwimCompetition sc : competitionList) {
            if (sc.getCompetitionName().toLowerCase().contains(name.toLowerCase())) {
                competitionListByName.add(sc);
            }
        }
        return competitionListByName;
    }
}
