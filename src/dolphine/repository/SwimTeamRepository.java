package dolphine.repository;

import dolphine.SwimTeam;
import dolphine.User;

import java.io.*;
import java.util.ArrayList;

public class SwimTeamRepository {

    private static final String filePath = "src/dolphine/repository/swimTeamFile.ser";

    //loads an ArrayList of SwimTeams from file "src/dolphine/repository/swimTeamFile.ser"
    public static ArrayList<SwimTeam> getSwimTeamList() {
        try {
            File file = new File(filePath);
            // Check if the file is empty
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<SwimTeam> loadedSwimTeams = (ArrayList<SwimTeam>) objectInputStream.readObject();
            return loadedSwimTeams;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    //saves an ArrayList of SwimTeams to file "src/dolphine/repository/swimTeamFile.ser"
    public static void saveSwimTeamList(ArrayList<SwimTeam> swimTeamList) {
        try  {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outPutStream.writeObject(swimTeamList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //update SwimTeam in file by finding and deleting original and adding new
    public static void updateSwimTeam(SwimTeam swimTeam){
        ArrayList<SwimTeam> swimTeamArraylist = getSwimTeamList();
        SwimTeam teamToUpdate = null;
        for(SwimTeam swimteamInArray : swimTeamArraylist){
            if(swimteamInArray.getID().equalsIgnoreCase(swimTeam.getID())){
                teamToUpdate = swimteamInArray;//team Found
                break;
            }
        }
        if(teamToUpdate != null){
            swimTeamArraylist.remove(teamToUpdate);
            swimTeamArraylist.add(swimTeam);
        } else {
            System.out.println("Unable to find SwimTeam in File");
        }
        saveSwimTeamList(swimTeamArraylist);
    }

    //save a SwimTeam to file
    public static void saveSwimTeamToFile(SwimTeam swimTeam){
        ArrayList<SwimTeam> swimTeamSFromFile = getSwimTeamList();
        swimTeamSFromFile.add(swimTeam);
        saveSwimTeamList(swimTeamSFromFile);
    }

    //delete a SwimTeam from File
    public static void deleteSwimTeam(SwimTeam swimTeamToDelete) {
        ArrayList<SwimTeam> swimTeamSFromFile = getSwimTeamList();
        SwimTeam swimTeamtoDeleteInFile = null;
        for(SwimTeam swimTeam : swimTeamSFromFile){
            if(swimTeamToDelete.getID().equalsIgnoreCase(swimTeam.getID())){
                swimTeamtoDeleteInFile = swimTeam;
                break;//swimTeam found in file, exit for loop
            }
        } if (swimTeamtoDeleteInFile != null){
            swimTeamSFromFile.remove(swimTeamtoDeleteInFile);
            System.out.println("Swim Team deleted from file");
        } else {
            System.out.println("Unable to find SwimTeam to delete in File");
        }
        saveSwimTeamList(swimTeamSFromFile);
    }
}
