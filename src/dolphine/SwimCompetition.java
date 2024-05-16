package dolphine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimCompetition {
    private LocalDate date;
    private String competitionName;
    private String location;
    private ArrayList<SwimDiscipline> swimDiscipline;
    private ArrayList<Member> swimMemberList;
    private String category;

    public SwimCompetition(String date, String competitionName, String location, LocalDate localDate) {
        this.date = LocalDate.parse(date);
        this.competitionName = competitionName;
        this.location = location;
        this.swimDiscipline = new ArrayList<>();
        this.swimMemberList = new ArrayList<>();
        this.category = category;
    }

    public void addSwimDiscipline(SwimDiscipline discipline) {
        swimDiscipline.add(discipline);
    }

    public void registerMember(Member member) {
        swimMemberList.add(member);

    }

        public static SwimCompetition opretKonkurrence() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("➤ Enter competition category (Junior/Senior):");
            String category = scanner.nextLine();
            System.out.println(" ➤ Enter the competetion name");
            String name = scanner.nextLine();
            System.out.println("➤ Enter competition location:");
            String location = scanner.nextLine();
            System.out.println("➤ Enter competition date (YYYY-MM-DD):");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            SwimCompetition competition = new SwimCompetition(category, name, location, date);

            System.out.println("Enter swim disciplines for the competition (enter 'done' when finished):");
            while (true) {
                System.out.println("Enter swim discipline:");
                String disciplineName = scanner.nextLine();
                if (disciplineName.equalsIgnoreCase("done")) {
                    break;
                }
                try {
                    SwimDiscipline discipline = SwimDiscipline.valueOf(disciplineName.toUpperCase());
                    competition.addSwimDiscipline(discipline);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid discipline. Please enter a valid discipline.");
                }
            }

            // Tilføj deltagere
            System.out.println("Enter participants for the competition (enter 'done' when finished):");
            while (true) {
                System.out.println("Enter participant name:");
                String id = scanner.nextLine();
                if (id.equalsIgnoreCase("done")) {
                    break;
                }

            }

            scanner.close();
            return competition;
        }

    // Getters for at udskrive konkurrenceoplysninger (valgfrit)
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
        return swimDiscipline;
    }

    public ArrayList<Member> getParticipants() {
        return swimMemberList;



        }

    }

