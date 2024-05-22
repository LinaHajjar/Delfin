package dolphine.ui;

import dolphine.*;
import dolphine.repository.CompetitionMemberRepository;
import dolphine.repository.ResultRepository;
import dolphine.repository.UserRepository;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ResultsUI {

    public static void resultMenu() {
        int choice;
        do {
            System.out.println(" 1: Add new Result");
            System.out.println(" 2: Show Results for Member");
            System.out.println(" 3: Delete Result for Member");
            System.out.println(" 4: Show list of Top 5 Swimmers");
            System.out.println(" 0: Return to Main Menu");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "Wrong input, choose a number between 0 and 4", 0, 5);

            switch (choice) {
                case 0:
                    System.out.println("You chose the option exit \nReturning to Main Menu");
                    break;
                case 1:
                    addResult();
                    break;
                case 2:
                    showResultListForMember();
                    break;
                case 3:
                    deleteResult();
                    break;
                case 4:
                    showTopFive();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        } while (choice != 0);
    }

    public static void addResult() {
        Scanner info = new Scanner(System.in);
        int resultType;
        System.out.println("Enter 1 to add Training Results");
        System.out.println("Enter 2 to add Competition Results");
        System.out.println("Enter 0 to return to the Menu");
        resultType = UserInputUtil.getIntInput("Enter the number from the list: ", "Wrong input, choose a number between 0 and 2", 0, 2);

        switch (resultType) {
            case 0:
                System.out.println("You chose the option exit \nReturning to Main Menu");
                break;
            case 1:
                addTrainingResultsPrompt(info);
                break;
            case 2:
                addCompetitionResultsPrompt(info);
                break;
        }
    }

    public static CompetitionMember findCompetitionMember() {
        boolean findingMember = false;
        ArrayList<CompetitionMember> competitionMemberArrayList;
        do {
            String name = UserInputUtil.getStringInput("Name of the member: ");
            competitionMemberArrayList = CompetitionMemberRepository.getCompetitionMemberListByName(name);
            if (competitionMemberArrayList.isEmpty()) {
                System.out.println("Member not found");
                findingMember = UserInputUtil.getStringInput("Try again? y/n", "Please write y or n", new String[]{"y", "n"}).equals("y");
            }
        } while (findingMember);
        if (competitionMemberArrayList.isEmpty()) {
            return null;
        }
        System.out.println("Select Competition Member");
        return UserInputUtil.selectObject(competitionMemberArrayList);
    }

    public static Result getResultPrompt(Scanner info) {
        CompetitionMember competitionMember = findCompetitionMember();

        System.out.println("Enter Swim Discipline");
        SwimDiscipline discipline = UserInputUtil.selectObject(SwimDiscipline.values());

        System.out.println("Enter Swim Distance");
        int distance = info.nextInt();

        System.out.println("Enter Time (in seconds)");
        int time = info.nextInt();
        info.nextLine();

        LocalDate date = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");

        return new Result(time, distance, date, discipline, competitionMember);
    }

    public static TrainingResult addTrainingResultsPrompt(Scanner info) {
        Result result = getResultPrompt(info);
        ArrayList<User> userList = UserRepository.getUserList();
        Trainer trainer = TrainerMenu.selectTrainer(userList);

        return new TrainingResult(result, trainer);
    }

    public static CompetitionResult addCompetitionResultsPrompt(Scanner info) {
        Result result = getResultPrompt(info);

        System.out.println("Enter Name of Competition");
        SwimCompetition competitionName = null; //TODO Get specific competition

        System.out.println("Enter Placement");
        int placement = info.nextInt();
        info.nextLine();

        return new CompetitionResult(result, competitionName, placement);
    }

    public static void showResultListForMember() {
        CompetitionMember competitionMember = findCompetitionMember();
        ArrayList<Result> resultsForMember = ResultRepository.getResultListForMember(competitionMember); //liste af resultater for medlemmet
//to hashmap, begge indeholder key som swimdiciplin, men det ene har trainingresult og det andet competitionresult
        HashMap<String, TrainingResult> trainingResultHashMap = new HashMap<>();
        HashMap<String, CompetitionResult> competitionResultHashMap = new HashMap<>();

        for (Result result : resultsForMember) {
            String resultKey = result.getSwimDiscipline().toString() + result.getDistance();
            //Set fastest training results
            if (result instanceof TrainingResult) {
                if (trainingResultHashMap.containsKey(resultKey)) { //eksisterer disciplinen og distancen i vores hashmap
                    if (result.getTime() < trainingResultHashMap.get(resultKey).getTime()) {
                        //sammenligner tiderne for at vælge den "mindste" tid
                        trainingResultHashMap.put(resultKey, (TrainingResult) result); //hvis tiden er hurtigere, overskrives i hashmap
                    }
                } else {
                    trainingResultHashMap.put(resultKey, (TrainingResult) result); //hvis ikke den eksisterer tilføjes resultatet i hashmap
                }
            }
            // Set fastest competition results
            else if (result instanceof CompetitionResult) {
                if (competitionResultHashMap.containsKey(resultKey)) { //eksisterer disciplinen og distancen i vores hashmap
                    if (result.getTime() < competitionResultHashMap.get(resultKey).getTime()) {
                        //sammenligner tiderne for at vælge den "mindste" tid
                        competitionResultHashMap.put(resultKey, (CompetitionResult) result); //hvis tiden er hurtigere, overskrives i hashmap
                    }
                } else {
                    competitionResultHashMap.put(resultKey, (CompetitionResult) result); //hvis ikke den eksisterer tilføjes resultatet i hashmap, der er ikke noget at overskrive
                }
            }
        }
        System.out.println("----------Training results-----------");
        for (TrainingResult trainingResult : trainingResultHashMap.values()) {
            System.out.println(trainingResult);
        }
        System.out.println("----------Competition results--------");
        for (CompetitionResult competitionResult : competitionResultHashMap.values()) {
            System.out.println(competitionResult);
        }
    }
    public static void deleteResult() {
        System.out.println("-----Delete Result for Member------");
        CompetitionMember competitionMember = findCompetitionMember();
        ArrayList<Result> resultsForMember = ResultRepository.getResultListForMember(competitionMember);
        Result resultChosen = UserInputUtil.selectObject(resultsForMember);
        ResultRepository.deleteResult(resultChosen);
        System.out.println("Result successfully deleted.");
    }

    public static void showTopFive() {
        ArrayList<Result> resultList = ResultRepository.getResultList(); //liste af resultater

        HashMap<String, ArrayList<Result>> juniorMemberTypeHashMap = new HashMap<>();
        HashMap<String, ArrayList<Result>> seniorMemberTypeHashMap = new HashMap<>();

        //Initial time is the slowest an int can be to always replace it first time around.
        int slowestJuniorTime = Integer.MAX_VALUE;
        int slowestSeniorTime = Integer.MAX_VALUE;
        for (Result result : resultList) {
            String resultKey = result.getSwimDiscipline().toString() + result.getDistance() + result.getCompetitionMember().getMemberType().toString(); // Unique key based of discipline, distance and memberType
            if (result.getCompetitionMember().getMemberType().equals(MemberType.JUNIOR)) {
                if (juniorMemberTypeHashMap.containsKey(resultKey) && result.getTime() < slowestJuniorTime) { //eksisterer disciplinen og distancen i vores hashmap
                    ArrayList<Result> topFiveFastestResultsForJunior = juniorMemberTypeHashMap.get(resultKey);
                    if (topFiveFastestResultsForJunior.size() == 5) {
                        Result slowestResult = findSlowestResult(topFiveFastestResultsForJunior);
                        topFiveFastestResultsForJunior.remove(slowestResult); //fjerne det langsomste
                    }
                    topFiveFastestResultsForJunior.add(result);//tilføje vores eget resultat
                    slowestJuniorTime = findSlowestResult(topFiveFastestResultsForJunior).getTime(); // Update the slowest time for compare
                } else {
                    juniorMemberTypeHashMap.put(resultKey, new ArrayList<>()); // hvis ikke den eksisterer laves et nyt array
                    juniorMemberTypeHashMap.get(resultKey).add(result);//og tilføjes resultatet i hashmap
                    slowestJuniorTime = result.getTime();
                }
            }
            // Set SENIOR results
            else if (result.getCompetitionMember().getMemberType().equals(MemberType.SENIOR)) {
                if (seniorMemberTypeHashMap.containsKey(resultKey) && result.getTime() < slowestSeniorTime) {
                    ArrayList<Result> topFiveFastestResultsForSenior = seniorMemberTypeHashMap.get(resultKey);
                    //eksisterer disciplinen og distancen i vores hashmap
                    if (topFiveFastestResultsForSenior.size() == 5) {
                        Result slowestResult = findSlowestResult(topFiveFastestResultsForSenior);
                        topFiveFastestResultsForSenior.remove(slowestResult);
                    }
                    topFiveFastestResultsForSenior.add(result);
                    slowestSeniorTime = findSlowestResult(topFiveFastestResultsForSenior).getTime();
                } else {
                    seniorMemberTypeHashMap.put(resultKey, new ArrayList<>()); // hvis ikke den eksisterer laves et nyt array
                    seniorMemberTypeHashMap.get(resultKey).add(result);//og tilføjes resultatet i hashmap
                    slowestSeniorTime = result.getTime();
                }
            }
        }
        // Display fastest time for each member type
        System.out.println("----------Top Five Junior results--------");
        for (ArrayList<Result> juniorDisciplineAndDistanceList : juniorMemberTypeHashMap.values()) {
            Collections.sort(juniorDisciplineAndDistanceList); // TODO test and see if they are sorted in the correct order fastest to lowest.sort.comparat
            for (Result result : juniorDisciplineAndDistanceList){
                System.out.println(result.getCompetitionMember().getName());
            }
        }

        System.out.println("----------Top Five Senior results--------");
        for (ArrayList<Result> seniorDisciplineAndDistanceList : seniorMemberTypeHashMap.values()) {
            Collections.sort(seniorDisciplineAndDistanceList); // TODO test and see if they are sorted in the correct order fastest to lowest
            for (Result result : seniorDisciplineAndDistanceList){
                System.out.println(result.getCompetitionMember().getName());
            }
        }
    }
    private static Result findSlowestResult(ArrayList<Result> resultList) {
        Result slowestResult = resultList.getFirst();
        for (int i = 1; i < resultList.size() - 1; i++) {
            // replace slowestResult if current time is slower
            if (resultList.get(i).getTime() > slowestResult.getTime()) {
                slowestResult = resultList.get(i);
            }
        }
        return slowestResult;
    }
}