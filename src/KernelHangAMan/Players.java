package KernelHangAMan;



import GuiHangAMan.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rifux on 20/04/2017.
 */
public class Players {
    private static final String url = "jdbc:sqlite:players.db";

    private static Connection dbConnection(String url){/*connection à la base de données*/
        Connection connection=null;                                                    /*utilisée pour stocker les informations sur les joueurs*/
        try  {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    private static void dbDeconnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static Player alreadyExist(String pseudonyme){/*verifier l'existance d'un pseudonyme*/
        Connection connection=dbConnection(Players.url);
        Statement state ;
        ResultSet result;
        Player player=null;

        try {
            state=connection.createStatement();
            result= state.executeQuery("SELECT * FROM `players` WHERE pseudonyme=\""+pseudonyme+"\" ORDER BY highestscore DESC");
            if (result.next())
                player= new Player(result.getString("pseudonyme"),result.getInt("highestScore"));

            state.close();
            result.close();
            dbDeconnection(connection);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return player;
    }
    public static Player authenticate(String pseudonyme){

        return alreadyExist(pseudonyme);
    }
    public static Player registrate(String pseudonyme) throws PseudonymeException {
        int unicode=((Character)Character.toUpperCase(pseudonyme.charAt(0))).hashCode();
        Player player = null;

        if(64<unicode && unicode<91) { // si le premier caractere est une lettre

            if (alreadyExist(pseudonyme) == null) {
                Connection connection = dbConnection(Players.url);
                Statement state;

                try {
                    state = connection.createStatement();
                    state.executeUpdate("INSERT INTO players (id, pseudonyme, highestScore, words ) VALUES(NULL, '" + pseudonyme + "', " + 0 + " ,'')");
                    player = new Player(pseudonyme, 0);
                    state.close();
                    dbDeconnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else throw new  PseudonymeException();
        return player;
    }
    public static boolean modifyPseudonyme(Player player,String pseudonyme)throws PseudonymeException{
        int unicode=((Character)Character.toUpperCase(pseudonyme.charAt(0))).hashCode();
        boolean toReturn=false;

        if(64<unicode && unicode<91) {// si le premier caractere du nouveau pseudonyme est une lettre
            Connection connection = dbConnection(Players.url);
            Statement state;

            try {
                if (alreadyExist(pseudonyme) == null) {
                    state = connection.createStatement();
                    if (state.executeUpdate("UPDATE `players` SET pseudonyme = '" + pseudonyme + "' WHERE pseudonyme ='" + player.getPseudonyme() + "'") == 1)
                        player.setPseudonyme(pseudonyme);
                    state.close();
                    dbDeconnection(connection);
                    toReturn=true;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        else throw new PseudonymeException();
        return toReturn;
    }
    public static void modifyHighestScore(Player player,int highestScore){

        Connection connection = dbConnection(Players.url);
        Statement state  ;

        try {
            state = connection.createStatement();
            if(state.executeUpdate("UPDATE `players` SET highestScore = '"+highestScore+"' WHERE pseudonyme ='"+player.getPseudonyme()+"'")==1)
                player.setHighestScore(highestScore);
            state.close();
            dbDeconnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Player> getBestScores(int nFirst){
        List<Player> players= new ArrayList<Player>();

        Connection connection=dbConnection(Players.url);
        Statement state ;
        ResultSet result;

        try {
            state=connection.createStatement();
            result= state.executeQuery("SELECT * FROM `players` ORDER BY highestscore DESC LIMIT 0,"+nFirst);
            while (result.next())
            {
                players.add(new Player(result.getString("pseudonyme"),result.getInt("highestScore")));
            }

            state.close();
            result.close();
            dbDeconnection(connection);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return players;
    }

    public static List<Player> getBestScores(String pseudonyme,int nFirst){
        List<Player> players= new ArrayList<Player>();

        Connection connection=dbConnection(Players.url);
        Statement state ;
        ResultSet result;

        try {
            state=connection.createStatement();
            result= state.executeQuery("SELECT * FROM `players`WHERE pseudonyme ='"+pseudonyme+"'" +"ORDER BY highestscore DESC LIMIT 0,"+nFirst);
            while (result.next())
            {
                players.add(new Player(result.getString("pseudonyme"),result.getInt("highestScore")));
            }

            state.close();
            result.close();
            dbDeconnection(connection);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return players;
    }


    public static void dbToFile(){

        Connection connection=dbConnection(Players.url);
        Statement state ;
        ResultSet result;

        try {
            String tempo="";

            state=connection.createStatement();
            BufferedWriter playersFile =new BufferedWriter(new FileWriter("players_file.txt"));
            playersFile.write("ID\tPLAYER\t\tSCORE");
            playersFile.newLine();

            result= state.executeQuery("SELECT * FROM `players` ORDER BY pseudonyme  ASC ");
            int i=1;
            while (result.next())
            {
                tempo += i + " -\t" + result.getString("pseudonyme") + "\t\t   " + result.getInt("highestScore");
                playersFile.write(tempo);
                playersFile.newLine();
                tempo = "";
                i++;
            }

            state.close();
            result.close();
            playersFile.close();
            dbDeconnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addScore(String pseudonyme, int score){
        Connection connection = dbConnection(Players.url);
        Statement state;

        try {
            state = connection.createStatement();
            state.executeUpdate("INSERT INTO players (id, pseudonyme, highestScore, words ) VALUES(NULL, '" + pseudonyme + "', " + score + " ,'')");
            if(score> Main.player.getHighestScore()) Main.player.setHighestScore(score);
            state.close();
            dbDeconnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
