package com.example.olayiwola.dissertation;

/**
 * Created by OLAYIWOLA on 19/07/2016.
 */
public class syllabus{
    int id;
    private String subjectName;
    private int session;

    public syllabus(int id,String subject, int session){
        this.id  = id;
        this.subjectName = subject;
        this.session = session;
    }
    public String toString(){
        return "Teacher [ID: "+ id +"Subject Name: "+ subjectName +"Session: " + session + "]";
    }
}
