package com.example.mac.bmi;

/**
 * Created by mac on 2/10/18.
 */

public class BMIResult {
    private double height=1;
    private double weight=1;

    public BMIResult(double height,double weight)
    {
        this.height=height;
        this.weight=weight;

    }
    public double getHeight(){return height;}
    public void setHeight(double height) {this.height=height;}
    public double getWeight(){return weight;}
    public void setWeight(double weight) {this.weight=weight;}
    public double getResult(){
        return weight/Math.pow(height,2);
    }
    public String toString(){return String.valueOf(getResult());}
}
