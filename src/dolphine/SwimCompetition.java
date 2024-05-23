package dolphine;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimCompetition implements Serializable {

    private LocalDate date;
    private String competitionName;
    private String location;
    private ArrayList<SwimDiscipline> swimDisciplines;
    private ArrayList<CompetitionMember> participants;
    private String category;

    public SwimCompetition(String category, String competitionName, String location, LocalDate date) {
        this.date = date;
        this.competitionName = competitionName;
        this.location = location;
        this.swimDisciplines = new ArrayList<>();
        this.participants = new ArrayList<>();
        this.category = category;
    }

        // SwimDiscipline filen tilføjes
    public void addSwimDiscipline(SwimDiscipline discipline) {
        swimDisciplines.add(discipline);
    }

    //  Valg af kategori = junior eller senior
    public void registerParticipant(CompetitionMember participant) {
        if ((category.equals("Junior") && participant.getAge() < 18) ||
                (category.equals("Senior") && participant.getAge() >= 18)) {
            participants.add(participant);
        } else {
            System.out.println("Participant does not match the competition category.");
        }
    }

        // oprettelse af konkurrence
    public static SwimCompetition opretKonkurrence(SwimTeam swimTeam) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("➤ Enter competition category (Junior/Senior):");
        String category = scanner.nextLine();
        System.out.println("➤ Enter the competition name:");
        String name = scanner.nextLine();
        System.out.println("➤ Enter competition location:");
        String location = scanner.nextLine();
        System.out.println("➤ Enter competition date (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        SwimCompetition competition = new SwimCompetition(category, name, location, date);

        // valg af disipliner
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

        // valg af deltagerne
        System.out.println("➤ Enter participants for the competition (enter 'done' when finished):");
        while (true) {
            System.out.println("➤ Enter participant name:");
            String nameInput = scanner.nextLine();
            if (nameInput.equalsIgnoreCase("done")) {
                break;
            }
            Member participant = swimTeam.findMemberByName(nameInput);
            if (participant instanceof CompetitionMember) {
                competition.registerParticipant((CompetitionMember) participant);
            } else {
                System.out.println("No competition member found with name: " + nameInput);
            }
        }

        return competition;
    }

    // redigere konkurrence
    public void editCompetition(SwimTeam swimTeam) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editing competition: " + competitionName);

        System.out.println("Enter new competition name (or press Enter to keep current name):");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            competitionName = newName;
        }

        System.out.println("Enter new competition location (or press Enter to keep current location):");
        String newLocation = scanner.nextLine();
        if (!newLocation.isEmpty()) {
            location = newLocation;
        }

        System.out.println("Enter new competition date (YYYY-MM-DD) (or press Enter to keep current date):");
        String newDate = scanner.nextLine();
        if (!newDate.isEmpty()) {
            date = LocalDate.parse(newDate);
        }

        System.out.println("Enter new competition category (Junior/Senior) (or press Enter to keep current category):");
        String newCategory = scanner.nextLine();
        if (!newCategory.isEmpty()) {
            category = newCategory;
        }

        System.out.println("Do you want to edit swim disciplines? (yes/no):");
        String editDisciplines = scanner.nextLine();
        if (editDisciplines.equalsIgnoreCase("yes")) {
            swimDisciplines.clear();
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
                        swimDisciplines.add(disciplines[disciplineIndex]);
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
            participants.clear();
            System.out.println("Enter participants for the competition (enter 'done' when finished):");
            while (true) {
                System.out.println("Enter participant name:");
                String nameInput = scanner.nextLine();
                if (nameInput.equalsIgnoreCase("done")) {
                    break;
                }
                Member participant = swimTeam.findMemberByName(nameInput);
                if (participant instanceof CompetitionMember) {
                    registerParticipant((CompetitionMember) participant);
                } else {
                    System.out.println("No competition member found with name: " + nameInput);
                }
            }
        }

        System.out.println("Competition updated successfully.");
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<SwimDiscipline> getSwimDisciplines() {
        return swimDisciplines;
    }

    public ArrayList<CompetitionMember> getParticipants() {
        return participants;
    }

    public String getCategory() {
        return category;
    }

    public void setCompetitionName(String newName) {
        this.competitionName = newName;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    public void setDate(LocalDate newDate) {
        this.date = newDate;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public void clearSwimDisciplines() {
        this.swimDisciplines.clear();
    }

    public void clearParticipants() {
        this.participants.clear();
    }
}
