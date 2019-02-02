package com.mobiquityinc.packer;

/* Class containing the details of each package **/
public class PackageDetail  {

    private int packageSequence;
    private double weight;
    private int cost;

    public PackageDetail(int packageSequence, double weight, int cost){
        this.packageSequence = packageSequence;
        this.weight = weight;
        this.cost = cost;
    }

    public int getPackageSequence() {
        return packageSequence;
    }

    public void setPackageSequence(int packageSequence) {
        this.packageSequence = packageSequence;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }




}

