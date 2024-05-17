package dolphine.ui;
import dolphine.Member;
import dolphine.MemberType;
import dolphine.util.UserInputUtil;
import dolphine.repository.MemberRepository;

import java.util.Scanner;
import static dolphine.ui.MainMenu.HovedMenu;

public class MemberUI {
    public static void MemberMenu(){
        int choice;
        do {
            System.out.println(" 1: Create New Member");
            System.out.println(" 2: Edit existing Member");
            System.out.println(" 3: Delete existing Member");
            System.out.println(" 0: Return to Main Menu");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 4", 0, 3);

            switch (choice) {
                case 1:
                    System.out.println("You chose option 1");
                    createMember();
                    break;
                case 2:
                    System.out.println("You chose option 2");
                    //editMember();
                    break;
                case 3:
                    System.out.println("You chose option 3");
                    //deleteMember();
                    break;

                case 0:
                    HovedMenu();
                    break;
            }//end switch
        }while (choice !=0);
    }//end of MemberMenu

    public static void createMember() {
        Scanner input = new Scanner(System.in);
        //INDSÆT LINAS METODE, UserUI.createUser(); (String name = UserInputUtil.getStringInput("Enter name: ");
        int age = UserInputUtil.getIntInput("Enter age: ", "Invalid age, please enter a valid number.", 0, 100);
        boolean isActive = UserInputUtil.getBooleanInput("Is member active? (true/false): ");

        MemberType memberType;
        if (age > 18) {
            memberType = MemberType.JUNIOR;
        } else if (age <= 18 && age >= 60) {
            memberType = MemberType.SENIOR;
        }

        System.out.println("Is member active? " + isActive);


        //lave et nye Member objekt --> med de specifikke karakteristika/parametre
        Member newMember = new Member(age, isActive, memberType);

        //vil have medlemmet tilføjet til repository
        MemberRepository.addMember(newMember);

        System.out.println("Member added successfully!");
    }
            //enter new member into an arraylist in repository (method)
            //print member information to a new file

    } //end of createMember

      /*  public static void editMember(Member updatedMember, Scanner input) {
      hent members arraylist
      læse indtil medlemmet findes, ID'et
      overskrive , set member parametre



               System.out.println("Enter the name of the Member you would like to edit");
               String memberName = input.nextLine();

               boolean found = false;

               for (int i = 0; i < MemberRepository.memberList.size(); i++) {
                   Member member = memberList.get(i);
                   if (member.getName().equals(memberName)) {
                       memberList.set(i, updatedMember);
                       System.out.println("Member information updated successfully.");
                       found = true;
                       break; // Stop searching once the member is found and updated
                   }
               }
               if (!found) {
                   System.out.println("Member with name \"" + memberName + "\" not found in the repository.");
               }
           }
       }

            //method to print arraylist to console
            //method in MemberRepository to get members by searching for their name
        }
        public static void deleteMember () {
            //call method to print arraylist to console
            //call method to search for members name to delete chosen member
        }

        */

