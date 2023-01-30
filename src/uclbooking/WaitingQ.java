/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uclbooking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class WaitingQ {

    private int head;
    private int tail;
    private int size;
    private fanInfo f[];

    public WaitingQ() {
        this.head = -1;
        this.tail = -1;
        size = 50;
        f = new fanInfo[size];
    }

    public WaitingQ(int head, int tail, int size, fanInfo[] f) {
        this.head = head;
        this.tail = tail;
        this.size = size;
        this.f = f;
    }

    public boolean isEmpty() {
        return tail == -1;
    }

    public boolean isFull() {
        return tail >= size;
    }

    public void enqueue(fanInfo input) {
        if (isEmpty()) {
            f[++head] = input;
            tail++;
            //System.out.println("First data " + input + " inserted..");
        } else {
            if (isFull()) {
            } else {
                f[++tail] = input;
            }
        }
        updateDB(input);
    }

    public fanInfo dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            fanInfo temp = f[head];
            for (int i = 0; i < tail; i++) {
                f[i] = f[i + 1];
            }
            tail--;
            return temp;
        }

    }

    public fanInfo dequeue(String name1, String match) {
        if (isEmpty()) {
            return null;
        } else {
            fanInfo temp = f[head];
            for (int i = 0; i < tail; i++) {
                f[i] = f[i + 1];
            }
            tail--;
            return temp;
        }

    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public fanInfo[] getF() {
        return f;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setF(fanInfo[] f) {
        this.f = f;
    }

    public String search(String name) {
        for (int i = 0; i < 20; i++) {
            if (f[i] != null && f[i].getName() != null) {
                if (f[i].getName().equals(name)) {
                    String s1 = f[i].toString() + "Ticket status: Waiting list (Position :" + (i + 1) + ")";
                    return s1;
                }
            }

        }
        return "Name was not found on the waiting list";
    }

    public String remove(String name) {
        for (int i = 0; i < 50; i++) {
            if (f[i] != null && f[i].getName() != null) {
                if (f[i].getName().equalsIgnoreCase(name)) {  //
                    for (int j = i; j < tail; j++) {
                        f[j] = f[j + 1];
                    }
                    tail--;

                }
            }
        }
        return "Name was not found on the waiting list";
    }

    public fanInfo peek() {
        if (head > -1) {
            return (f[head]);
        } else {
            return null;
        }
    }

    public void importDB(String match) {
        try {
            Scanner s = new Scanner(new FileInputStream("waitinglist.txt"));
            String lines[] = new String[100];
            int i = -1;
            while (s.hasNextLine()) {
                i++;
                lines[i] = s.nextLine();
                
            }
            s.close();
            i = 0;
            if (lines[i] != null) {
                for (i = 0; i < 100; i++) {
                    if(lines[i]!=null){
                       String[] l = lines[i].split(",");
                    if (l[4].equalsIgnoreCase(match)) {
                        fanInfo f = new fanInfo(l[0], Integer.parseInt(l[1]), l[2], Integer.parseInt(l[3]), l[4]);
                        enqueue(f);
                    } 
                    }
                    
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WaitingQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDB(fanInfo f) {
        FileWriter d = null;
        try {
            d = new FileWriter("waitinglist.txt", true);
            String data = f.getName() + "," + f.getAge() + "," + f.getGender() + "," + f.getPhoneNo() + "," + f.getMatch();
            d.write(data);
            d.write(System.lineSeparator());
            d.close();
        } catch (IOException ex) {
            Logger.getLogger(WaitingQ.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                d.close();
            } catch (IOException ex) {
                Logger.getLogger(WaitingQ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
