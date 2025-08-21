import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangMan_Game {
    public static void main(String[] args) {


        String filePath="src\\word.txt";

        ArrayList<String> words=new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader(filePath))){
            String line;
            while((line =reader.readLine()) !=null){
                words.add(line.trim());


            }

        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("Error ");
        }
        Random rand=new Random();
        String word =words.get(rand.nextInt(words.size()));


        Scanner sc = new Scanner(System.in);
        int wrongGuess =0;
        ArrayList<Character> wordState = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("WELCOME TO HANGMAN GAME SERVER");

        while(wrongGuess < 6 ){


            System.out.print(hangManCharArt(wrongGuess));
            System.out.print("word: ");
            for(char c: wordState){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("guess a letter:");
            char guess=sc.next().toLowerCase().charAt(0);

            if(word.indexOf(guess) >= 0){
                System.out.println("correct guess");
                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        wordState.set(i, guess);

                    }
                }
                if(!wordState.contains('_')){
                    System.out.print(hangManCharArt(wrongGuess));
                    System.out.println("YOU WON!");
                    System.out.println("word is : " + word);
                    break;
                }

            }
            else{
                wrongGuess++;
                System.out.println("Wrong guess");
            }
        }
        if(wrongGuess >=6){
            System.out.print(hangManCharArt(wrongGuess));
            System.out.println("GAME OVER!!");
            System.out.println("the word is : " + word );
        }

        sc.close();

    }
    static String hangManCharArt(int wrongGuess){
        return switch (wrongGuess){
            case 0 -> """
                      
                    
                    
                      """;
            case 1 -> """
                       o
                    
                     
                      """;
            case 2 -> """
                       o
                       |
                     
                      """;
            case 3 -> """
                       o
                      /|
                     
                      """;
            case 4 -> """
                       o
                      /|\\
                     
                      """;
            case 5 -> """
                       o
                      /|\\
                      /
                      """;
            case 6 -> """
                       o
                      /|\\
                      / \\
                      """;
            default -> " ";
        };

    }
}
