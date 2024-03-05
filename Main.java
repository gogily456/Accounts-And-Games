import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
//import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {

        boolean login = checkLogin();

        if(login)
        {
            Scanner in = new Scanner(System.in);
            int go = 0;
            int chooseGame = 0;
            boolean antherGame = true;
            while(antherGame)
            {

                do
                {
                    System.out.println("do you want to play a game? \n 1 - yes \n 2 - no");
                    go = in.nextInt();
                }
                while(go < 1 || go > 2);
                {
                    if(go == 2)
                    {
                        antherGame = false;
                    }
                    if(go == 1)
                    {
                        do
                        {
                            System.out.println("what game do you want to play? \n 1 - guess the word  \n 2 - the last match");
                            chooseGame = in.nextInt();
                        }
                        while(chooseGame < 1 || chooseGame > 2);
                        {
                            if(chooseGame == 2)
                            {
                                manageGame();
                            }
                            if(chooseGame == 1)
                            {
                                guessTheWord();
                            }
                        }
                    }
                }
            }
        }
    }

    public static int initializeGame(int numOfmatches)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("enter the number of matches in the game");
        numOfmatches = in.nextInt();
        return numOfmatches;
    }

    public static void displayGame(int numOfmatches)
    {
        for(int i = 0; i<5; i++)
        {
            System.out.print("-");
        }

        for(int i = 0; i<numOfmatches; i++)
        {
            System.out.print("|");
        }

        for(int i = 0; i<5; i++)
        {
            System.out.print("-");
        }
    }

    public static boolean gameOver(int numOfmatches)
    {
        if(numOfmatches == 0)
        {
            return false;
        }

        return true;
    }

    public static int userPlay(int numOfmatches,int matchesToRemove)
    {
        Scanner in = new Scanner(System.in);

        do
        {
            System.out.println("your turn. \n enter the number of matches to remove.\n must be between 1 to 4 and less then the max amount of matches on the table ");
            matchesToRemove = in.nextInt();
        }
        while(matchesToRemove < 1 || matchesToRemove > 4 && matchesToRemove <= numOfmatches);
        {
            numOfmatches = numOfmatches - matchesToRemove;
            System.out.println("the number of matches left." + numOfmatches + " matches");
            displayGame(numOfmatches);

            return numOfmatches;
        }

    }

    public static int computerPlay(int numOfmatches, int matchesToRemove)
    {

        Random r = new Random();

        if(numOfmatches < 5)
        {
            matchesToRemove = numOfmatches;
            numOfmatches = numOfmatches - matchesToRemove;
        }
        else if(numOfmatches % 5 != 0)
        {
            matchesToRemove = numOfmatches % 5;
            numOfmatches = numOfmatches - matchesToRemove;
        }
        else if (numOfmatches % 5 == 0)
        {
            matchesToRemove = r.nextInt(4) + 1;
            numOfmatches = numOfmatches - matchesToRemove;
        }

        System.out.println("the computer removed " + matchesToRemove + " matches");
        System.out.println("the number of matches left." + numOfmatches + " matches");
        displayGame(numOfmatches);
        return numOfmatches;

    }

    public static void manageGame()
    {
        int numOfmatches = 0;
        int matchesToRemove = 0;
        /*
        int player = 1;
        int computer = 2;
        */
        Random r = new Random();
        int turn = r.nextInt(2) + 1;

        numOfmatches = initializeGame(numOfmatches);
        displayGame(numOfmatches);

        if(turn == 1)
        {
            System.out.println("you start");
            numOfmatches = userPlay(numOfmatches, matchesToRemove);
        }

        if(turn == 2)
        {
            System.out.println("the computer starts");
            numOfmatches = computerPlay(numOfmatches, matchesToRemove);
        }

        while(gameOver(numOfmatches))
        {
            turn = (turn == 1) ? 2 : 1;
            if(turn == 1)
            {

                numOfmatches = userPlay(numOfmatches, matchesToRemove);

                if(numOfmatches == 0)
                {
                    System.out.println("you won");
                }
            }

            if(turn == 2)
            {

                numOfmatches = computerPlay(numOfmatches, matchesToRemove);

                if(numOfmatches == 0)
                {
                    System.out.println("the computer won");
                }
            }
        }
    }

    public static void guessTheWord()
    {
        Scanner in = new Scanner(System.in);

        String putWord = "";

        while(putWord.length() < 2)
        {

            System.out.println("Enter a word. must contain at least 2 letter" );
            putWord = in.nextLine();

        }

        StringBuilder word = new StringBuilder(putWord);
        StringBuilder line = new StringBuilder();
        Set<Character> usedChars = new HashSet<>();  // To keep track of used characters
        List<Integer> indices = new ArrayList<>();  // To store the indices of matching characters

        String guess;

        int count = 4;

        System.out.println("the word is: " + word + "     ***this is for debugging***");// Can remove this line

        for(int i= 0; i<word.length(); i++)
        {
            if(word.charAt(i) == ' ')
            {
                line.append(" ");

            }
            else
            {
                line.append("_");
            }
        }

        while(count != 0)
        {
            do
            {
                System.out.println("Guess the word: " + line + " you have " + count + " tries\nenter a letter");
                guess = in.nextLine().toLowerCase(); // Convert the guess to lowercase

                if (usedChars.contains(guess.charAt(0)))
                {
                    System.out.println("You already used that character. Try again.");
                }
            }
            while(guess.isEmpty() || guess.length() > 1 || usedChars.contains(guess.charAt(0)) || guess.charAt(0) == ' ');
            {
                indices.clear();

                for(int i= 0; i<putWord.length(); i++)
                {
                    if(Character.toLowerCase(guess.charAt(0)) == Character.toLowerCase(putWord.charAt(i)))
                    {
                        indices.add(i);  // Store the index of the matching character
                    }
                }

                if (!indices.isEmpty() && !usedChars.contains(guess.charAt(0)))
                {
                    for (int index : indices)
                    {
                        line.setCharAt(index,guess.charAt(0));  // Replace all matching characters
                    }
                    System.out.println("Good you guessed a letter " /*+ line*/); // optional

                }

                if (indices.isEmpty() && !usedChars.contains(guess.charAt(0)))
                {

                    count = count -1;
                    System.out.println("Wrong letter.");
                    if(count == 0)
                    {
                        System.out.println("You lost. The word was " + putWord.toLowerCase());
                    }

                }
                usedChars.add(guess.charAt(0));
            }

            if(line.toString().equals(putWord.toLowerCase()))
            {
                System.out.println("Good job you guessed the word. the word was: " + putWord.toLowerCase());
                count = 0;
            }
        }

    }

    public static boolean checkLogin()
    {
        Scanner in = new Scanner(System.in);

        String path = "C:\\Accounts\\access";
        String username;
        String password;
        int login = 0;
        int count = 0;

        do
        {
            System.out.println("do you have an account? 1-yes 2-no");
            login = in.nextInt();
            in.nextLine();
        }
        while(login < 1 || login > 2);
        {
            if(login == 1)
            {

                try
                {
                    do
                    {
                        count++;
                        System.out.println("enter username: ");
                        username = in.nextLine();
                        System.out.println("enter password: ");
                        password = in.nextLine();

                        if(count >= 2)
                        {
                            System.out.println("you want to sigh in? 1-no 2-yes");
                            login = in.nextInt();
                            in.nextLine();
                            break;
                        }
                    }
                    while(fileUtiles.found(username, password, path) == false);
                    {
                        System.out.println("you can start the game ");
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }

            if(login == 2)
            {
                System.out.println("you are now registering to the system: ");
                System.out.println("enter username and password");
                System.out.println("enter username: ");
                username = "---------" + "\n" + "username: " + in.nextLine() + "\n";
                System.out.println("enter password: ");
                password ="password: " + in.nextLine() + "\n" + "---------";

                try
                {
                    fileUtiles.writeFile(path, username, password);
                    System.out.println("you registered Successfully");

                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }

        }

        return true;
    }
}
