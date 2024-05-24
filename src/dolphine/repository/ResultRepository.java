package dolphine.repository;

import dolphine.CompetitionMember;
import dolphine.CompetitionResult;
import dolphine.Result;
import dolphine.TrainingResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ResultRepository {
    private static final String filePath = "src/dolphine/repository/resultFile.ser";

    public static void createResult(Result result) {
        ArrayList<Result> resultList = getResultList();
        if (resultList == null) {
            return;
        }
        resultList.add(result);
        overrideResultList(resultList);
    }

    public static void saveResultList(ArrayList<Result> resultList) {
        ArrayList<Result> resultListFromFile = getResultList();
        if (resultListFromFile == null) {
            return;
        }
        resultListFromFile.addAll(resultList);
        overrideResultList(resultListFromFile);
    }

    public static ArrayList<Result> getResultList() {
        try {
            File file = new File(filePath);

            // Check if the file is empty
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Result> resultList = (ArrayList<Result>) objectInputStream.readObject();
            return resultList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void overrideResultList(ArrayList<Result> resultList) {
        try  {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outPutStream.writeObject(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateResult(Result result) {
        ArrayList<Result> resultList = getResultList();
        if (resultList == null) {
            return;
        }
        // Find matching result and update all fields to make sure they are the same.
        for (Result res : resultList) {
            if (Objects.equals(res.getId(), result.getId())) {
                res.setTime(result.getTime());
                res.setDateOfResult(result.getDateOfResult());
                res.setDistance(result.getDistance());
                res.setSwimDiscipline(result.getSwimDiscipline());

                //Updates specific fields based of which subclass the result is.
                if (result instanceof TrainingResult && res instanceof TrainingResult){
                    ((TrainingResult) res).setTrainer(((TrainingResult) result).getTrainer());
                } else if (result instanceof CompetitionResult && res instanceof CompetitionResult){
                    ((CompetitionResult) res).setSwimCompetition(((CompetitionResult) result).getSwimCompetition());
                }
            }
        }
        overrideResultList(resultList);
    }

    public static void deleteResult(Result result) {
        ArrayList<Result> resultList = getResultList();
        if (resultList == null) {
            return;
        }
        resultList.removeIf(res -> Objects.equals(res.getId(), result.getId()));
        overrideResultList(resultList);
    }

    public static ArrayList<Result> getResultListForMember(CompetitionMember competitionMember) {
        ArrayList<Result> resultList = getResultList();
        if (resultList == null) {
            return new ArrayList<>();
        }
        ArrayList<Result> resultListMatchingMember = new ArrayList<>();
        for (Result res : resultList) {
            if (Objects.equals(res.getCompetitionMember().getId(), competitionMember.getId())) {
                resultListMatchingMember.add(res);
            }
        }
        return resultListMatchingMember;
    }
}