package Ruslan_7kd;
import java.util.*;
import java.io.*;
public class Hangman {
    public static void main(String[] args) throws IOException{
        System.out.println("****** Hangman *******");
        String[] word = getWord().split("");
//        System.out.println(Arrays.toString(word));
        gamePlay(word);
    }
    public static void gamePlay(String[] word) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("GamePlay *** Begins ***");
        String[] asterisks = new String[word.length];
        Arrays.fill(asterisks,"*");
//        System.out.println(Arrays.toString(asterisks));
        int word_length = word.length;
        int hangCounter = 0;
        while(word_length != 0){
            boolean alreadyExists = false;
            boolean x = false;
            System.out.print("\nEnter guess "+Arrays.toString(asterisks)+": ");
            char guess = input.next().charAt(0);
            for (int i = 0; i < word.length ; i++) {
                if(word[i].equals(guess+"") & !(asterisks[i].equals(guess+"") )){
                    asterisks[i] = word[i];
                    x = true;
                }
                else if (word[i].equals(guess+"") & (asterisks[i].equals(guess+""))){
                    System.out.println("The word already exists. Try other word");
                    alreadyExists = true;
                    break;
                }
            }
            if(alreadyExists) continue;
            else if(x){
                System.out.println(" -- Matched --");
                word_length--;
            }
            else{
                hangCounter += 5;
                System.out.print("|  Wrong word  |");
                displayHangman(hangCounter);
            }
        }
    }
    public static void displayHangman(int hangCounter) throws IOException{
        System.out.println("\n\n");
        File hangMan = new File("./Hangman.txt");
        Scanner s = new Scanner(hangMan);
        for (int i = 0; i < hangCounter+1 ; i++) {
            try{
                System.out.println(s.nextLine());
            }catch (Exception e){
                System.out.println("You are hanged");
                System.exit(0);
            }
        }
        System.out.println("\n\n");
    }
    public static String getWord()throws IOException, FileNotFoundException {
        String s = "pakistan";
        File wordFile = new File("./Words.txt");
        if(!(wordFile.exists())){
            System.out.println("File for word does not exits. Kindly check the files");
            System.exit(0);
        }
        Scanner reader = new Scanner(wordFile);
        int random_number = 1 +(int)( Math.random() * 855 );
        System.out.println("Fetching word.........");
        for (int i = 0; i < random_number; i++) {
            try{
                s = reader.nextLine();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return s;
    }
}