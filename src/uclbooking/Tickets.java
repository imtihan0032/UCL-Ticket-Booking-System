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
public class Tickets {
    private fanInfo f;

    public Tickets() {
    }

    public Tickets(fanInfo f) {
        this.f = f;
    }

    public void setDetails(String name, int age, String gender, int phoneNo) {
        f=new fanInfo(name, age, gender, phoneNo);
    }

    

    
}
