package org.keyin.workoutclasses;

public class WorkoutClass {
    private int id;
    private String className;
    private String description;
    private int trainerId;
    private String schedule;

    //constructor
    public WorkoutClass(String className, String description, int trainerId, String schedule){
        this.className = className;
        this.description = description;
        this.trainerId = trainerId;
        this.schedule = schedule;
    }

    //getters
    public int getId(){
        return id;
    }

    public String getClassName(){
        return this.className;
    }

    public String getDescription(){
        return this.description;
    }

    public int getTrainerID(){
        return this.trainerId;
    }

    public String getSchedule(){
        return this.schedule;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }
    
    public void setClassName(String className){
        this.className = className;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setTrainerID(int trainerId){
        this.trainerId = trainerId;
    }

    public void setschedule(String schedule){
        this.schedule = schedule;
    }

    //toString
    public String toString(){
        return "Class name : " + className + " Description: " + description + " Trainer ID: " + trainerId + " Schedule: " + schedule ;
    }
    
}
