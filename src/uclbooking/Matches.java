/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uclbooking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Matches<T extends Comparable<T>> {

    private int size;
    private Match head;
    private Match curr;

    public Matches() {
        size = 0;
        head = null;
    }

    public int length() {
        int count = 0;
        Match countNode = head;
        while (countNode != null) {
            countNode = countNode.getLink();
            count++;
        }
        return count;
    }

    public void clear() {
        head = null;
    }

    public void autoaddMatch(String teamName) {
        clear();
        String[] lines = new String[38];
        try {
            Scanner s = new Scanner(new FileInputStream(teamName + ".txt"));
            int i = 7 - 1; //no of matches in the leagues
            while (s.hasNextLine()) {

                lines[i] = s.nextLine();
                i--;
            }
            s.close();
            for (i = 0; i < 7; i++) {
                Match m = new Match();
                String[] l = lines[i].split(",");
                m.setDetails(teamName, l[3], Integer.parseInt(l[4]), Integer.parseInt(l[5]), Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l[2]), l[6], Integer.parseInt(l[7]));
                addMatch(m);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Matches.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String displayMatches() {
        StringBuilder sb = new StringBuilder();
        sb.append("All upcoming fixtures:-\n" + "1. ").append(head.vstoString());
        Match m = head.getLink();
        int n = 2;
        while (m != null) {
            sb.append("\n" + n + ". " + m.vstoString());
            m = m.getLink();
            n++;
        }
        return sb.toString();
    }

    public String displayspecificMatches() {

        return curr.vstoString();
    }

    public String displayspecificMatchonly() {

        return curr.toString();
    }

    public String displayvenue() {

        return curr.getVenue();
    }

    public int displayprice() {
        return curr.getPrice();
    }

    public void addMatch(Match m) {
        Match currentMatch = head;
        if (head == null) {
            head = m;
        } else {
            while (currentMatch.getLink() != null) {
                currentMatch = currentMatch.getLink();
            }
            currentMatch.setLink(m);
        }
        size++;
    }

    public void setCurr(int n) {
        curr = this.selectspecific(n);
        this.autoaddMatch(curr.getTeam1());
    }

    public Match selectspecific(int m) {
        int n;
        
        Match temp = head;
        for (int i = 1; i < m; i++) {
            temp = temp.getLink();
        }
        return temp;
    }

    public void bookTicket(String name, int age, String gender, int phoneNo) {

        curr.bookticket(name, age, gender, phoneNo);

    }

    public void bookSeasonpass(String name, int age, String gender, int phoneNo) {
        Match temp = head;
        for (int i = 0; i < size; i++) {
            temp.bookticket(name, age, gender, phoneNo);
            temp = temp.getLink();
        }
    }

    public void cancelTicket(String name) {
        curr.cancelTicket(name);
    }

    public void checkStatus(String name) {
        curr.checkStatus(name);
    }

    public void importticketsDB() {
        curr.importfromDB();
    }

    public void exportticketsDB() {
        curr.exportalltoDB();
    }
    
    public void exportticketDB() {
        curr.exportsingletoDB();
    }
}
