package com.company;

public class gridentry {
    public double getVal() {
        return val;
    }

    public String getObstacle() {
        return obstacle;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public void setObstacle(String obstacle) {
        this.obstacle = obstacle;
    }

    double val;
    String obstacle;

    public gridentry() {
        this.val = 0;
        this.obstacle = "No";
    }
}
