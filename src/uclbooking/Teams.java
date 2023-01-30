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
public class Teams {
    
    public String teamname;
    public Matches<String> fixtures=new Matches<>();

    public Teams() {
        this.teamname=null;
    }

    public Teams(String teamname) {
        this.teamname = teamname;
        fixtures.autoaddMatch(teamname);
    }

    public String getTeamname() {
        return teamname;
    }

    public Matches<String> getFixtures() {
        return fixtures;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
        fixtures.autoaddMatch(teamname);
    }

    public void setFixtures(Matches<String> fixtures) {
        this.fixtures = fixtures;
    }
    
    public String showFixtures(){
        return fixtures.displayMatches();
    }
    
    public String showspecificFixture(){
        return fixtures.displayspecificMatches();
    }
    public String showspecificFixtureonly(){
        return fixtures.displayspecificMatchonly();
    }
    
    public String showVenue(){
        return fixtures.displayvenue();
    }
    
    public int showPrice(){
        return fixtures.displayprice();
    }
    
    public void setCurr(int n){
        fixtures.setCurr(n);
    }
    
    public void bookTicket(String name, int age, String gender, int number){
        fixtures.bookTicket(name, age, gender, number);
        
    }
    
    public void cancelTicket(String name){
        fixtures.cancelTicket(name);
    }
    
    public void checkStatus(String name){
        fixtures.checkStatus(name);
    }
    
    public void importticketsDB(){
        fixtures.importticketsDB();
    }
    
    public void exportticketsDB(){
        fixtures.exportticketsDB();
    }
    
    public void exportticketDB(){
        fixtures.exportticketDB();
    }
}
