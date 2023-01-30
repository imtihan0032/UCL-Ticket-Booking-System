/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uclbooking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class fanInfo {
    private String name;
    private int age;
    private String gender;
    private int phoneNo;
    private String match;

    public fanInfo() {
        this.name = null;
        this.age = 0;
        this.gender = null;
        this.phoneNo = 0;
        this.match=null;
    }
    
    public void clear() {
        this.name = null;
        this.age = 0;
        this.gender = null;
        this.phoneNo = 0;
        this.match=null;
    }

    public fanInfo(String name, int age, String gender, int phoneNo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }
    
    public fanInfo(String name, int age, String gender, int phoneNo, String s) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.match=s;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }
    
    

    @Override
    public String toString() {
        return "Name=" + name + "\nAge=" + age + "\nGender=" + gender + "\nPhoneNo=" + phoneNo;
    }
    
    public void exporttoDB(){
        try {
            // TODO add your handling code here:
            FileWriter d = new FileWriter("tickets.txt", true);
            String data = name + "," + age + "," + gender + "," + phoneNo + "," + match;
            d.write(data);
            d.write(System.lineSeparator());
            d.close();
        } catch (IOException ex) {
            Logger.getLogger(InitialScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
