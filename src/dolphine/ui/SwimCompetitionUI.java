package dolphine.ui;

import dolphine.CompetitionMember;
import dolphine.SwimCompetition;
import dolphine.SwimDiscipline;
import dolphine.SwimTeam;
import dolphine.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimCompetitionUI {
    private static SwimTeam swimTeam = new SwimTeam(new ArrayList<>(), new ArrayList<>());
    private static ArrayList<SwimCompetition> competitions = new ArrayList<>();

    public static void main(String[] args) {
        showMainMenu();
        editCompetition();
    }



    private static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Create Competition");
            System.out.println("2. Edit Competition");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createCompetition();
                    break;
                case "2":
                    editCompetition();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createCompetition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("➤ Enter competition category (Junior/Senior):");
        String category = scanner.nextLine();
        System.out.println("➤ Enter competition name:");
        String competitionName = scanner.nextLine();
        System.out.println("➤ Enter competition location:");
        String location = scanner.nextLine();
        System.out.println("➤ Enter competition date (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        SwimCompetition competition = new SwimCompetition(category, competitionName, location, date);

        // Select swim disciplines
        System.out.println("➤ Select swim disciplines for the competition (enter 'done' when finished):");
        SwimDiscipline[] disciplines = SwimDiscipline.values();
        for (int i = 0; i < disciplines.length; i++) {
            System.out.println((i + 1) + ": " + disciplines[i]);
        }

        while (true) {
            System.out.println("➤ Enter the number of the swim discipline:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                int disciplineIndex = Integer.parseInt(input) - 1;
                if (disciplineIndex >= 0 && disciplineIndex < disciplines.length) {
                    competition.addSwimDiscipline(disciplines[disciplineIndex]);
                } else {
                    System.out.println("Invalid number. Please select a valid discipline number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Add participants
        System.out.println("➤ Enter participants for the competition (enter 'done' when finished):");
        while (true) {
            System.out.println("➤ Enter participant name:");
            String participantName = scanner.nextLine();
            if (participantName.equalsIgnoreCase("done")) {
                break;
            }
            Member member = swimTeam.findMemberByName(participantName);
            if (member instanceof CompetitionMember) {
                competition.registerParticipant((CompetitionMember) member);
            } else {
                System.out.println("No competition member found with name: " + participantName);
            }
        }

        competitions.add(competition);

        // Print competition details
        System.out.println("Competition created successfully!");
        System.out.println("Name: " + competition.getCompetitionName());
        System.out.println("Location: " + competition.getLocation());
        System.out.println("Date: " + competition.getDate());
        System.out.println("Category: " + category);
        System.out.println("Disciplines: " + competition.getSwimDisciplines());
        System.out.println("Participants: " + competition.getParticipants());
    }

    private static void editCompetition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the competition you want to edit:");
        String competitionName = scanner.nextLine();
        SwimCompetition competition = null;

        for (SwimCompetition comp : competitions) {
            if (comp.getCompetitionName().equalsIgnoreCase(competitionName)) {
                competition = comp;
                break;
            }
        }

        if (competition == null) {
            System.out.println("Competition not found.");
            return;
        }

        System.out.println("Editing competition: " + competition.getCompetitionName());

        System.out.println("Enter new competition name (or press Enter to keep current name):");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            competition.setcompetitionName(newName);
        }

        System.out.println("Enter new competition location (or press Enter to keep current location):");
        String newLocation = scanner.nextLine();
        if (!newLocation.isEmpty()) {
            competition.setlocation(newLocation);
        }

        System.out.println("Enter new competition date (YYYY-MM-DD) (or press Enter to keep current date):");
        String newDate = scanner.nextLine();
        if (!newDate.isEmpty()) {
            competition.setdate(LocalDate.parse(newDate));
        }

        System.out.println("Enter new competition category (Junior/Senior) (or press Enter to keep current category):");
        String newCategory = scanner.nextLine();
        if (!newCategory.isEmpty()) {
            competition.setcategory(newCategory);
        }

        System.out.println("Do you want to edit swim disciplines? (yes/no):");
        String editDisciplines = scanner.nextLine();
        if (editDisciplines.equalsIgnoreCase("yes")) {
            competition.clearSwimDiscipline();
            System.out.println("Select swim disciplines for the competition (enter 'done' when finished):");
            SwimDiscipline[] disciplines = SwimDiscipline.values();
            for (int i = 0; i < disciplines.length; i++) {
                System.out.println((i + 1) + ": " + disciplines[i]);
            }

            while (true) {
                System.out.println("Enter the number of the swim discipline:");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("done")) {
                    break;
                }
                try {
                    int disciplineIndex = Integer.parseInt(input) - 1;
                    if (disciplineIndex >= 0 && disciplineIndex < disciplines.length) {
                        competition.addSwimDiscipline(disciplines[disciplineIndex]);
                    } else {
                        System.out.println("Invalid number. Please select a valid discipline number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }

        System.out.println("Do you want to edit participants? (yes/no):");
        String editParticipants = scanner.nextLine();
        if (editParticipants.equalsIgnoreCase("yes")) {
            competition.clearparticipants();
            System.out.println("Enter participants for the competition (enter 'done' when finished):");
            while (true) {
                System.out.println("Enter participant name:");
                String participantName = scanner.nextLine();
                if (participantName.equalsIgnoreCase("done")) {
                    break;
                }
                Member member = swimTeam.findMemberByName(participantName);
                if (member instanceof CompetitionMember) {
                    competition.registerParticipant((CompetitionMember) member);
                } else {
                    System.out.println("No competition member found with name: " + participantName);
                }
            }
        }

        System.out.println("Competition updated successfully.");
    }
}

