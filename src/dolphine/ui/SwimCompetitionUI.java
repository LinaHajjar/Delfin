package dolphine.ui;

import dolphine.SwimCompetition;
import dolphine.repository.SwimCompetitionRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class SwimCompetitionUI {
    // hovedemenuen
    public static void swimCompetitionMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Create Competition");
            System.out.println("2. Edit Competition");
            System.out.println("3. View Competitions");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    opretKonkurrence();
                    break;
                case "2":
                    editCompetition();
                    break;
                case "3":
                    viewCompetitions();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

        // opret konkurrence
    private static void opretKonkurrence() {
        SwimCompetition competition = SwimCompetition.opretKonkurrence();
        SwimCompetitionRepository.createCompetition(competition);
        System.out.println("Competition created successfully.");
    }

    // redigere konkurrence
    private static void editCompetition() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<SwimCompetition> competitions = SwimCompetitionRepository.getCompetitionList();

        if (competitions == null || competitions.isEmpty()) {
            System.out.println("No competitions available to edit.");
            return;
        }

        System.out.println("Select a competition to edit:");
        for (int i = 0; i < competitions.size(); i++) {
            System.out.println((i + 1) + ": " + competitions.get(i).getCompetitionName());
        }

        int index = -1;
        while (true) {
            try {
                index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index >= 0 && index < competitions.size()) {
                    break;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + competitions.size() + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        }

        SwimCompetition competition = competitions.get(index);
        competition.editCompetition();
        SwimCompetitionRepository.updateCompetition(competition);
        System.out.println("Competition updated successfully.");
    }

    // se liste af konkurrence
    private static void viewCompetitions() {
        ArrayList<SwimCompetition> competitions = SwimCompetitionRepository.getCompetitionList();

        if (competitions == null || competitions.isEmpty()) {
            System.out.println("No competitions available.");
            return;
        }

        System.out.println("List of Competitions:");
        for (SwimCompetition competition : competitions) {
            System.out.println("Name: " + competition.getCompetitionName());
            System.out.println("Date: " + competition.getDate());
            System.out.println("Location: " + competition.getLocation());
            System.out.println("Category: " + competition.getCategory());
            System.out.println("Swim Disciplines: " + competition.getSwimDisciplines());
            System.out.println("Participants: " + competition.getParticipants());
            System.out.println("-------------------------");
        }
    }
}
