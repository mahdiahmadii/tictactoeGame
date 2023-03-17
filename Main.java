import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.lang.Thread;

public class Main {
    //COLORS
    public static final String ANSI_BLACK_BOLD = "\033[1;30m";
    public static final String ANSI_RED_BACKGROUND_BRIGHT = "\033[0;101m";
    public static final String ANSI_YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";
    public static final String ANSI_CYAN_BACKGROUND_BRIGHT = "\033[0;106m";
    public static final String ANSI_PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BRIGHT = "\033[0;91m";
    public static final String ANSI_WHITE_BOLD = "\033[1;37m";
    public static final String ANSI_CYAN_BOLD = "\033[1;36m";
    public static final String ANSI_WHITE_BACKGROUND = "\033[47m";  //



    //_______________________________________________________________________________________
    //MAIN
    public static void main(String[] args) throws InterruptedException {
        Random rand1 = new Random();
        Scanner input = new Scanner(System.in);
        String command="0";
        show_welcome();
        Thread.sleep(3000);
        while(command.equals("0"))
        {
            cls();
            show_tictactoe();
            show_menu();
            command = input.nextLine();

//_______________________________________________________________________________________


            //PLAY_menu
            while (command.equals("1"))//single_mode
            {


                cls();
                show_play_menu();//single player or multiplayer
                String second_command = input.nextLine();








                //Single_player
                while(second_command.equals("1")||second_command.equals("R"))
                {
                    //preparing the game situation
                    cls();
                    String [][] mat = new String[7][7];
                    int[] true_choice=new int[1];
                    String choice;
                    int winning=0;
                    matrix_maker(mat);
                    cell_block(mat);

                    while(winning==0) {
                        //playerX
                        cls();
                        show_matrix(mat);
                        while (true_choice[0] == 0) {
                            System.out.print("playerX choose: ");
                            choice = input.nextLine();
                            moveX(mat, choice, true_choice);
                            winning = is_won(mat);
                            if(choice.equals("cheat"))
                            {
                                winning=1;
                            }

                        }
                        if(winning!=0)//check if the game has winner or finished equal
                        {
                            cls();
                            show_matrix(mat);
                            if(winning==1)//won
                            {
                                show_win();
                            }
                            else if(winning==2)//equal
                            {
                                System.out.println(ANSI_PURPLE+"    << That is a tie! >>"+ANSI_RESET);
                            }
                            break;
                        }
                        true_choice[0]=0;


                        //playerO
                        cls();
                        show_matrix(mat);
                        while (true_choice[0] == 0) {
                            choice = String.valueOf(rand1.nextInt(17));
                            moveO(mat, choice, true_choice);
                            winning = is_won(mat);

                        }
                        true_choice[0]=0;


                        if(winning!=0)//check if the game has winner or finished equal
                        {
                            cls();
                            show_matrix(mat);
                            if(winning==1)
                            {
                                show_lose();
                            }
                            else if(winning==2)
                            {
                                System.out.println(ANSI_PURPLE+"      <<That is a tie!>>"+ANSI_RESET);
                            }
                            break;
                        }
                        winning=0;
                    }


                    System.out.println("press enter to continue or press R to play again!");
                    second_command=input.nextLine();
                    if(second_command.equals("R"))
                    {
                        continue;
                    }
                    break;

                }














                //_________________________________________________________________________________
                //Multi_player

                while(second_command.equals("2"))//multi mode
                {
                    //making game
                    cls();
                    String [][] mat = new String[7][7];
                    int[] true_choice=new int[1];
                    String choice;
                    int winning=0;
                    matrix_maker(mat);
                    cell_block(mat);

                    while(winning==0) {
                        //playerX
                        cls();
                        show_matrix(mat);
                        while (true_choice[0] == 0) {
                            System.out.print("player X choose: ");
                            choice = input.nextLine();
                            moveX(mat, choice, true_choice);
                            winning = is_won(mat);
                            if(choice.equals("cheat"))
                            {
                                winning=1;
                            }

                        }
                        if(winning!=0)//check if the game has winner or finished equal
                        {
                            cls();
                            show_matrix(mat);
                            if(winning==1)
                            {
                                show_player_x_win();
                            }
                            else if(winning==2)
                            {
                                System.out.println(ANSI_PURPLE+"That is a tie!"+ANSI_RESET);
                            }
                            break;
                        }



                        true_choice[0]=0;
                        //playerO
                        cls();
                        show_matrix(mat);
                        while (true_choice[0] == 0) {
                            System.out.print("player O choose: ");
                            choice = input.nextLine();
                            moveO(mat, choice, true_choice);
                            winning = is_won(mat);
                            if(choice.equals("cheat"))
                            {
                                winning=1;
                            }

                        }
                        true_choice[0]=0;


                        if(winning!=0)//check if the game has winner or finished equal
                        {
                            cls();
                            show_matrix(mat);
                            if(winning==1)
                            {
                                show_player_O_win();
                            }
                            else if(winning==2)
                            {
                                System.out.println(ANSI_PURPLE+"That is a tie!"+ANSI_RESET);
                            }

                            break;
                        }

                        winning=0;
                    }


                    pause();
                    break;
                }
                //_______________________________________________________________
                //Return
                while(second_command.equals("3"))
                {
                    command="0";
                    break;
                }
                while(!(second_command.equals("1")||second_command.equals("2")||second_command.equals("3")||second_command.equals("R")||second_command.equals("")))
                {
                    System.out.println(ANSI_RED_BRIGHT+"wrong command!"+ANSI_RESET);
                    pause();
                    second_command = "0";
                    break;
                }


            }




//____________________________________________________________________________________________
            //HELP
            while(command.equals("2"))
            {

                cls();
                show_help();
                pause();
                command="0";
            }

            //ABOUT
            while(command.equals("3"))
            {
                cls();
                show_about();
                command="0";
                pause();

            }
            //EXIT
            while(command.equals("4"))
            {
                cls();
                show_exit();
                command = input.nextLine();
                if(command.equals("1"))
                {
                    command="Exit";
                }
                else if(command.equals("2"))
                {
                    command="0";
                }
                else
                {
                    command="4";
                }

            }
            //WRONG_COMMAND
            while(!(command.equals("0")||command.equals("Exit")))
            {
                cls();
                command = "0";
                System.out.println(ANSI_RED+"Wrong command!!!"+ANSI_RESET);
            }
        }
    }
























    //FUNCTIONS
    //_____________________________________________________________________________________________________

    /**
     * This function clear the console
     */
    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }
    //__________________________________________________________________________________________________


    /**
     * This function show a menu consist of 1-play 2-help 3-about 4-exit
     */
    public static void show_menu(){
        System.out.println(ANSI_CYAN+"1-PLAY\n"+ANSI_YELLOW+"2-HELP\n"+ANSI_PURPLE+"3-ABOUT\n"+ANSI_RED+"4-EXIT");
        System.out.print(ANSI_RESET+">>");
    }
    //____________________________________________________________________________________________________


    /**
     * This function print the description of the game
     */

    public static void show_help(){
        System.out.println(ANSI_RED_BRIGHT+"                    <<tic tac toe>>");
        System.out.println(ANSI_CYAN+"*how to play:\n**the only goal in this game is putting 3 same 'X' or 'O' in a row and win!");
        System.out.println("***The rows could be vertical,horizontal or diagonal.");
        System.out.println("hope you enjoy:))))"+ANSI_RESET);
    }
    //_____________________________________________________________________________________________________

    /**
     * this function print the information in about section
     */

    public static void show_about(){
        System.out.println(ANSI_CYAN_BOLD+"mahdi2003ahmadi82@gmail.com"+ANSI_RESET);
    }
    //__________________________________________________________________________________________________

    /**
     * This function ask the user to exit
     */

    public static void show_exit(){
        System.out.print(ANSI_PURPLE+"Are you sure uou want to exit the game? :(((("+ ANSI_RED+" \n1-yes"+ANSI_YELLOW+"\n2-no"+ANSI_RESET+"\n>>");
    }
    //_________________________________________________________________________________

    /**
     * this function print the menu consist of 1-single player 2-multiplayer 3-return
     */

    public static void show_play_menu()
    {
        System.out.println(ANSI_RED+"1-single mode\n"+ANSI_BLUE+"2-multiplayer mode"+ANSI_GREEN+"\n3-return"+ANSI_RESET);
    }
    //------------------------------------------------------------------------------------
    /**
     * this function stop the program till the time user press enter
     */
    public static void pause2()
    {
        System.out.println("press any kay and press enter to continue...");
        Scanner stop = new Scanner(System.in);
    }
    //________________________________________________________________________________

    /**
     * This function clear the console
     */
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
    }
    //_________________________________________________________________________________

    /**
     * this function stop the program till the time user press enter
     */

    public static final void pause() {
        InputStreamReader converter = new InputStreamReader(System.in);
        char[] c = new char[1];
        System.out.println("press Enter to continue...");
        try {
            converter.read(c);
        } catch (IOException e) {
        }
    }
    //-------------------------------------------------------------------------------

    /**
     * this function fill the matrix with String numbers from 1 to 16
     * @param mat the matrix
     */
    public static void matrix_maker(String[][] mat)
    {
        int l=1;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                mat[i][j]=String.valueOf(l);
                l=l+1;
            }
        }
    }
    //________________________________________________________________________________________

    /**
     * this function print the matrix in  a suitable way
     * @param mat the matrix
     */


    public static void show_matrix(String[][] mat)
    {
        System.out.println(" ____________________");
        for(int i=0;i<4;i++)
        {
            System.out.print("|");
            for(int j=0;j<4;j++)
            {
                if(mat[i][j].equals("#"))
                {
                    System.out.print(ANSI_PURPLE_BACKGROUND_BRIGHT+ANSI_BLACK_BOLD+"  # "+ANSI_RESET+"|");
                }
                else if(mat[i][j].equals("X"))
                {
                    System.out.print(ANSI_RED_BACKGROUND_BRIGHT+ANSI_BLACK_BOLD+"  X "+ANSI_RESET+"|");
                }
                else if(mat[i][j].equals("O"))
                {
                    System.out.print(ANSI_CYAN_BACKGROUND_BRIGHT+ANSI_BLACK_BOLD+"  O "+ANSI_RESET+"|");
                }
                else
                {
                    System.out.printf(ANSI_YELLOW_BACKGROUND_BRIGHT+ANSI_BLACK_BOLD+" %2s "+ANSI_RESET+"|", mat[i][j]);
                }
            }
            if(i!=3)
            {
                System.out.print("\n|----|----|----|----|\n");
            }
            else
            {
                System.out.println("\n -------------------\n");
            }
        }
    }
    //__________________________________________________________________________________________

    /**
     * this function check the cell chose by O player and if it was allowed to fill that cell by 'O' then do it
     * @param mat the matrix which is filling by 'X' and 'O'
     * @param a the choice of O user
     * @param true_choice a simulation of a boolean parameter to check the user choice is acceptable
     */

    public static void moveX(String[][] mat,String a,int[] true_choice)
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(mat[i][j].equals(a))
                {
                    mat[i][j]="X";
                    true_choice[0]=true_choice[0]+1;
                }
                else if(a.equals("cheat"))
                {
                    true_choice[0]=true_choice[0]+1;
                }
            }
        }
    }
    //_____________________________________________________________________________________________

    /**
     * this function block 3 cell by '#' charachter
     * @param mat the matrix which is filling by 'X' and 'O'
     */
    public static void cell_block(String[][] mat)
    {
        Random rand = new Random();
        int block=0;
        while(block !=3)
        {
            String cell =String.valueOf(rand.nextInt(17));
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (mat[i][j].equals(cell))
                    {
                        mat[i][j]="#";
                        block=block+1;
                    }
                }
            }
        }

    }
    //____________________________________________________________________________________________

    /**
     * this function check the cell chose by O player and if it was allowed to fill that cell by 'O' then do it
     * @param mat the matrix which is filling by 'X' and 'O'
     * @param a the choice of O user
     * @param true_choice a simulation of a boolean parameter to check the user choice is acceptable
     */

    public static void moveO(String[][] mat,String a, int[] true_choice)
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(mat[i][j].equals(a))
                {
                    mat[i][j]="O";
                    true_choice[0]=true_choice[0]+1;
                }
                else if(a.equals("cheat"))
                {
                    true_choice[0]=true_choice[0]+1;
                }
            }
        }
    }
    //_________________________________________________________________________________________


    /**
     * This function check the situation of matrix for win and equal
     * @param mat the matrix you are filling by X and Y!
     * @return if any player won , it returns 1   and if there is not any empty cell to choose, it returns 2  and else it returns 0
     */



    public static int is_won(String[][] mat)
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(mat[i][j].equals(mat[i+1][j]) && mat[i][j].equals(mat[i+2][j])&&!(mat[i][j].equals("#")))
                {
                    return 1;
                }
                else if(mat[i][j].equals(mat[i][j+1]) && mat[i][j].equals(mat[i][j+2])&&!(mat[i][j].equals("#")))
                {
                    return 1;
                }
                else if(mat[i][j].equals(mat[i+1][j+1]) && mat[i][j].equals(mat[i+2][j+2])&&!(mat[i][j].equals("#")))
                {
                    return 1;
                }
                else if(i>1 && mat[i][j].equals(mat[i-1][j+1]) && mat[i][j].equals(mat[i-2][j+2])&&!(mat[i][j].equals("#")) )
                {
                    return 1;
                }
            }
        }
        int equal=0;
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if (!(mat[i][j].equals("X") || mat[i][j].equals("O") || mat[i][j].equals("#")))
                {equal = equal + 1;}
            }
        }
        if(equal==0)
        {
            return 2;
        }
        return 0;

    }
    //_________________________________________________________________________________________


    /**
     * This function show a multiline text "YOU WIN"
     */


    public static void show_win()
    {
        System.out.println(ANSI_GREEN);
        System.out.println("                      ╔╗");

        System.out.println("                      ║║");
        System.out.println("╔╗ ╔╦══╦╗╔╗╔╗╔╗╔╦╦══╗ ║║");
        System.out.println("║║ ║║╔╗║║║║║╚╝╚╝╠╣╔╗║ ╚╝");
        System.out.println("║╚═╝║╚╝║╚╝║╚╗╔╗╔╣║║║║ ╔╗");
        System.out.println("╚═╗╔╩══╩══╝ ╚╝╚╝╚╩╝╚╝ ╚╝");
        System.out.println("╔═╝║                   ");
        System.out.println("╚══╝                   "+ANSI_RESET);


    }
    //----------------------------------------------------------------------------------------------

    /**
     * This function show a text that is a multiline tic_tac_toe
     */

    public static void show_tictac() throws InterruptedException {
        System.out.print("\033[H\033[2J");

        System.out.println("________________________________________________________");
        Thread.sleep(200);
        System.out.println(ANSI_WHITE_BOLD+".-----. _         .-----.             .-----.            ");
        Thread.sleep(200);
        System.out.println("`-. .-':_;        `-. .-'             `-. .-'            ");
        Thread.sleep(200);
        System.out.println("  : :  .-. .--.     : : .--.   .--.     : : .--.  .--.   ");
        Thread.sleep(200);
        System.out.println("  : :  : :'  ..'    : :' .; ; '  ..'    : :' :: :' '_.'  ");
        Thread.sleep(200);
        System.out.println("  :_;  :_;`.__.'    :_;`.__,_;`.__.'    :_;`.__.'`.__.'  "+ANSI_RESET);
        Thread.sleep(200);
        System.out.println("________________________________________________________");
    }
    //------------------------------------------------------------------------------------------

    public static void show_tictactoe()
    {
        System.out.println("______________________________________");
        System.out.println(ANSI_CYAN+"    ╔╗     "+ANSI_YELLOW+"  ╔╗       "+ANSI_RED+"  ╔╗       ");
        System.out.println(ANSI_CYAN+"   ╔╝╚╗    "+ANSI_YELLOW+" ╔╝╚╗      "+ANSI_RED+" ╔╝╚╗      ");
        System.out.println(ANSI_CYAN+"   ╚╗╔╬╦══╗"+ANSI_YELLOW+" ╚╗╔╬══╦══╗"+ANSI_RED+" ╚╗╔╬══╦══╗");
        System.out.println(ANSI_CYAN+"    ║║╠╣╔═╝"+ANSI_YELLOW+"  ║║║╔╗║╔═╝"+ANSI_RED+"  ║║║╔╗║║═╣");
        System.out.println(ANSI_CYAN+"    ║╚╣║╚═╗"+ANSI_YELLOW+"  ║╚╣╔╗║╚═╗"+ANSI_RED+"  ║╚╣╚╝║║═╣");
        System.out.println(ANSI_CYAN+"    ╚═╩╩══╝"+ANSI_YELLOW+"  ╚═╩╝╚╩══╝"+ANSI_RED+"  ╚═╩══╩══╝");
        System.out.println(ANSI_RESET+"______________________________________");
    }
    //__________________________________________________________________________________________
    /**
     * this function print "you lose" with a cow:)))))
     */
    public static void show_lose(){
        System.out.println();
        System.out.println(" ________________                           ");
        System.out.println("|"+ANSI_YELLOW_BACKGROUND_BRIGHT+"                "+ANSI_RESET+"|                           ");
        System.out.println("|"+ANSI_YELLOW_BACKGROUND_BRIGHT+ANSI_BLACK_BOLD+"    YOU LOSE!   "+ANSI_RESET+"|                          ");
        System.out.println("|"+ANSI_YELLOW_BACKGROUND_BRIGHT+"________________"+ANSI_RESET+"|"+ANSI_RED+"  ^__^                    ");
        System.out.println("                 \\  (OO)\\_________        ");
        System.out.println("                  \\ (__)\\         )\\/\\/ ");
        System.out.println("      			         ||------||         ");
        System.out.println("                         ||      ||         "+ANSI_RESET);
    }
    //_________________________________________________________________________________________________

    /**
     *this function print a bear saying welcome
     */
    public static void show_welcome()
    {
        System.out.println(ANSI_YELLOW);
        System.out.println("    	       ▄▀▀▀▄▄▄▄▄▄▄▀▀▀▄");
        System.out.println("	           █▒▒░░░░░░░░░▒▒█");
        System.out.println("	            █░░█░░░░░█░░█");
        System.out.println("	         ▄▄  █░░░▀█▀░░░█  ▄▄");
        System.out.println("        	█░░█ ▀▄░░░░░░░▄▀─█░░█");
        System.out.println("        	█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█");
        System.out.println("        	█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█");
        System.out.println("	        █░░║║║╠─║─║─║║║║║╠─░░█");
        System.out.println("	        █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█");
        System.out.println("	        █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█"+ANSI_RESET);
    }
//____________________________________________________________________________________________________
    /**
     * this function print player x win
     */
    public static void show_player_x_win() {
        System.out.println(ANSI_RED+"   ╔╗             ╔═╗╔═╗           ╔╗╔╗                 ╔╗");
        System.out.println("   ║║             ╚╗╚╝╔╝          ╔╝╚╣║                 ║║");
        System.out.println("╔══╣║╔══╦╗ ╔╦══╦═╗ ╚╗╔╝ ╔╗╔╗╔╦╦═╗ ╚╗╔╣╚═╦══╗ ╔══╦══╦╗╔╦══╣║");
        System.out.println("║╔╗║║║╔╗║║ ║║║═╣╔╝ ╔╝╚╗ ║╚╝╚╝╠╣╔╗╗ ║║║╔╗║║═╣ ║╔╗║╔╗║╚╝║║═╬╝");
        System.out.println("║╚╝║╚╣╔╗║╚═╝║║═╣║ ╔╝╔╗╚╗╚╗╔╗╔╣║║║║ ║╚╣║║║║═╣ ║╚╝║╔╗║║║║║═╬╗");
        System.out.println("║╔═╩═╩╝╚╩═╗╔╩══╩╝ ╚═╝╚═╝ ╚╝╚╝╚╩╝╚╝ ╚═╩╝╚╩══╝ ╚═╗╠╝╚╩╩╩╩══╩╝");
        System.out.println("║║      ╔═╝║                                 ╔═╝║          ");
        System.out.println("╚╝      ╚══╝                                 ╚══╝          "+ANSI_RESET);
    }
    //_______________________________________________________________________________________________
    /**
     * this function print player O win
     */
    public static void show_player_O_win()
    {
        System.out.println(ANSI_CYAN_BOLD+"   ╔╗               ╔═══╗            ╔╗╔╗                  ╔╗");
        System.out.println("   ║║               ║╔═╗║           ╔╝╚╣║                  ║║");
        System.out.println("╔══╣║╔══╦╗ ╔╦══╦═╗  ║║ ║║ ╔╗╔╗╔╦╦═╗ ╚╗╔╣╚═╦══╗ ╔══╦══╦╗╔╦══╗║║");
        System.out.println("║╔╗║║║╔╗║║ ║║║═╣╔╝  ║║ ║║ ║╚╝╚╝╠╣╔╗╗ ║║║╔╗║║═╣ ║╔╗║╔╗║╚╝║║═╣╚╝");
        System.out.println("║╚╝║╚╣╔╗║╚═╝║║═╣║   ║╚═╝║ ╚╗╔╗╔╣║║║║ ║╚╣║║║║═╣ ║╚╝║╔╗║║║║║═╣╔╗");
        System.out.println("║╔═╩═╩╝╚╩═╗╔╩══╩╝   ╚═══╝  ╚╝╚╝╚╩╝╚╝ ╚═╩╝╚╩══╝ ╚═╗╠╝╚╩╩╩╩══╝╚╝");
        System.out.println("║║      ╔═╝║                                   ╔═╝║           ");
        System.out.println("╚╝      ╚══╝                                   ╚══╝           "+ANSI_RESET);

    }
}