package com.cameldemo.rabbitmq;


import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;

@CsvRecord(separator = ",", skipFirstLine = false)
public class Student implements Serializable{

    @DataField(pos=1)
    private int id;

    @DataField(pos=2)
    private String name;

    @DataField(pos=3)
    private double maths;

    @DataField(pos=4)
    private double chem;

    @DataField(pos=5)
    private double phy;

    @DataField(pos=6)
    private int rollNumber;

    @DataField(pos=7,defaultValue ="0")
    private double percentage;

    public Student(int id, String name, double maths, double chem, double phy, int rollNumber) {
        this.id = id;
        this.name = name;
        this.maths = maths;
        this.chem = chem;
        this.phy = phy;
        this.rollNumber = rollNumber;
    }

    public Student(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaths() {
        return maths;
    }

    public double getChem() {
        return chem;
    }

    public double getPhy() {
        return phy;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaths(double maths) {
        this.maths = maths;
    }

    public void setChem(double chem) {
        this.chem = chem;
    }

    public void setPhy(double phy) {
        this.phy = phy;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public double getAndSetCalculatedPercentage(){
        this.percentage= (maths+phy+chem)/300*100;
        return percentage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maths=" + maths +
                ", chem=" + chem +
                ", phy=" + phy +
                ", percentage=" + percentage +
                ", rollNumber=" + rollNumber +
                '}';
    }
}