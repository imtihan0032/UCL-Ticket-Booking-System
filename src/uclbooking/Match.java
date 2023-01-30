/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uclbooking;

/**
 *
 * @author user
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Match<T> {

    private String team1;
    private String team2;
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
    private String venue;
    private int price;
    private int totaltickets;
    private fanInfo[] t;
    private WaitingQ wq;

    private Match link;

    public Match(String team1, String team2, int hour, int minute, int day, int month, int year, String venue, int price, int totaltickets, fanInfo[] t, Match link) {
        this.team1 = team1;
        this.team2 = team2;
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        this.venue = venue;
        this.price = price;
        this.totaltickets = totaltickets;
        this.t = t;
        this.link = link;
    }

    public Match() {
        this.team1 = null;
        this.team2 = null;
        this.venue = null;
        this.hour = 0;
        this.minute = 0;
        this.day = 0;
        this.month = 0;
        this.year = 0;
        this.price = 0;
        link = null;
        totaltickets = 0;
        t = new fanInfo[3];
        wq = new WaitingQ();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Match getLink() {
        return link;
    }

    public void setLink(Match link) {
        this.link = link;
    }

    public Match getMatch() {
        return this;
    }

    public void setMatch(Match m) {
        this.team1 = m.getTeam1();
        this.team2 = m.getTeam2();
        this.venue = m.getVenue();
        this.hour = m.getHour();
        this.minute = m.getMinute();
        this.day = m.getDay();
        this.month = m.getMonth();
        this.year = m.getYear();
    }

    public void setDetails(String team1, String team2, int hour, int minute, int day, int month, int year, String venue, int price) {
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        this.price = price;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getVenue() {
        return venue;
    }

    public String vstoString() {
        return "" + team1 + " vs " + team2 + ", " + day + "/" + month + "/" + year + ", " + hour + ":" + minute + "\n" + "Venue: " + venue;
    }

    public String toString() {
        return "" + team1 + " vs " + team2 + ", " + day + "/" + month + "/" + year + ", " + hour + ":" + minute;
    }

    public String toStringDB() {
        return "" + team1 + "vs" + team2;
    }

    public void displaymatch() {

    }

    public void bookticket(String name, int age, String gender, int phoneNo) {
        if (totaltickets < 3) {
            t[totaltickets] = new fanInfo(name, age, gender, phoneNo, toStringDB());
            exportsingletoDB();
            totaltickets++;

            JOptionPane.showMessageDialog(null, "Ticket has been booked for \n" + this.toString());
        } else {
            JOptionPane.showMessageDialog(null, "There are no more tickets available for \n" + this.toString() + "\nAdding to waiting list");
            wq.enqueue(new fanInfo(name, age, gender, phoneNo, toStringDB()));
        }
    }

    public void cancelTicket(String name1) {
        String name, gender;
        int i, age, number;
        boolean cond = false;
        for (i = 0; i < 3; i++) {
            if (t[i] != null && t[i].getName() != null) {
                if (t[i].getName().equalsIgnoreCase(name1)) {
                    cond = true;
                    t[i].clear();
                    totaltickets--;
                    break;
                }
            }
        }
        if (i < 2) {
            while (i < 2) {
                if (t[i + 1] != null) {
                    t[i] = new fanInfo(t[i + 1].getName(), t[i + 1].getAge(), t[i + 1].getGender(), t[i + 1].getPhoneNo());
                    t[i + 1] = null;
                }
                i++;

            }

        }
        if (cond) {
            this.deletesinglefromDB(name1);
            fanInfo a = wq.dequeue();
            if (a != null) {
                this.bookticket(a.getName(), a.getAge(), a.getGender(), a.getPhoneNo());
            }

            JOptionPane.showMessageDialog(null, "Ticket has been cancelled for \n" + this.toString());

        } else {
            JOptionPane.showMessageDialog(null, "Ticket with name " + name1 + " was not found.");
        }

    }

    public void checkStatus(String name1) {
        int i;
        boolean cond = false;
        for (i = 0; i < 3; i++) {
            if (t[i] != null && t[i].getName() != null) {
                if (t[i].getName().equalsIgnoreCase(name1)) {
                    cond = true;
                    break;
                }
            }
        }
        if (cond) {
            JOptionPane.showMessageDialog(null, "Ticket is booked for " + name1 + " for \n" + this.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Ticket with name " + name1 + " was not found.");
        }

    }

    public void exportsingletoDB() {
        try {
            // TODO add your handling code here:
            FileWriter d = new FileWriter("tickets.txt", true);
            int i = totaltickets;
            String data = t[i].getName() + "," + t[i].getAge() + "," + t[i].getGender() + "," + t[i].getPhoneNo() + "," + this.toStringDB();
            d.write(data);
            d.write(System.lineSeparator());

            d.close();
        } catch (IOException ex) {
            Logger.getLogger(InitialScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportalltoDB() {
        try {
            // TODO add your handling code here:
            FileWriter d = new FileWriter("tickets.txt", true);
            for (int i = 0; i < totaltickets; i++) {
                String data = t[i].getName() + "," + t[i].getAge() + "," + t[i].getGender() + "," + t[i].getPhoneNo() + "," + this.toStringDB();
                d.write(data);
                d.write(System.lineSeparator());
            }

            d.close();
        } catch (IOException ex) {
            Logger.getLogger(InitialScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletesinglefromDB(String name1) {
        try {
            Scanner s = new Scanner(new FileInputStream("tickets.txt"));
            String[] lines = new String[100];

            int i = -1;
            while (s.hasNextLine()) {
                i++;
                lines[i] = s.nextLine();
            }
            s.close();
            try {
                // TODO add your handling code here:
                FileWriter d = new FileWriter("tickets.txt", false);
                for (i = 0; i < 100; i++) {
                    if (lines[i] != null) {
                        String[] l = lines[i].split(",");
                        if (l[0].equalsIgnoreCase(name1)) {
                            ;
                        } else {
                            String data = l[0] + "," + l[1] + "," + l[2] + "," + l[3] + "," + l[4];
                            d.write(data);
                            d.write(System.lineSeparator());
                        }
                    }
                }

                d.close();
            } catch (IOException ex) {
                Logger.getLogger(InitialScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void importfromDB() {
        wq.importDB(this.toStringDB());
        try {
            Scanner s = new Scanner(new FileInputStream("tickets.txt"));
            String[] lines = new String[100];

            int i = -1;
            while (s.hasNextLine()) {
                i++;
                lines[i] = s.nextLine();
            }
            s.close();
            if (i == -1) {
                ;
            } else {
                i=0;
                if (lines[i] != null) {
                    String[] l = lines[i].split(",");
                    while (totaltickets < 3 && i >= 0) {
                        if (l[4].equalsIgnoreCase(this.toStringDB())) {
                            t[totaltickets] = new fanInfo(l[0], Integer.parseInt(l[1]), l[2], Integer.parseInt(l[3]));
                            totaltickets++;
                        }
                        i--;
                    }
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
