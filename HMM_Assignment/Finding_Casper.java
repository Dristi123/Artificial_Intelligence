package com.company;

import java.text.DecimalFormat;

public class Finding_Casper {
    gridentry [][] room;
    int n;
    int m;
    int k;

    public Finding_Casper(gridentry[][] room, int n, int m,int k) {
        this.room = room;
        this.n = n;
        this.m = m;
        this.k = k;
        double initial=1.0/((n*m)-k);
        //System.out.println(initial);

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(this.room[i][j].getObstacle().equalsIgnoreCase("No")) {
                    this.room[i][j].setVal(initial);
                }
                else this.room[i][j].setVal(0.0);
            }
        }
    }
    public void printgrid() {
        DecimalFormat df=new DecimalFormat("0.0000");
        System.out.println("Grid Denoting probabilities of finding Casper in each cell:");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(df.format(this.room[i][j].getVal())+" ");
            }
            System.out.println();
        }
    }
    int getcellsforedge(int i,int j,gridentry[][]temp) {
        int cell=0;
        if(j!=0) {
            if (temp[i][j - 1].getObstacle().equalsIgnoreCase("No")) cell++;
        }
        if(j!=(m-1)) {
            if (temp[i][j + 1].getObstacle().equalsIgnoreCase("No")) cell++;
        }
        if(i!=0) {
            if (temp[i-1][j].getObstacle().equalsIgnoreCase("No")) cell++;
        }
        if(i!=(n-1)) {
            if (temp[i + 1][j].getObstacle().equalsIgnoreCase("No")) cell++;
        }
        return  cell;
    }
    int getcellsforcorner(int i,int j,gridentry[][] temp) {
        int cell=0;
        if(j!=0) {
            if (i!=(n-1)&&(temp[i+1][j - 1].getObstacle().equalsIgnoreCase("No"))) cell++;
            if(i!=0) {
                if (temp[i-1][j - 1].getObstacle().equalsIgnoreCase("No")) cell++;
            }
        }
        if(i!=(n-1)&& j!=(m-1)) {
            if (temp[i + 1][j + 1].getObstacle().equalsIgnoreCase("No")) cell++;
        }
        if(i!=0) {
            if(j!=(m-1)) {
                if (temp[i - 1][j + 1].getObstacle().equalsIgnoreCase("No")) cell++;
            }
        }
        return  cell;

    }
    public void updatefortime() {
        //System.out.println("here");
        double sideprob=0.9;
        double cornerprob=0.1;
        gridentry[][] temp=new gridentry[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                temp[i][j]=new gridentry();
            }
        }
        //gridentry[][] temp2=new gridentry[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                temp[i][j].setVal(this.room[i][j].getVal());
                temp[i][j].setObstacle(this.room[i][j].getObstacle());

            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                int cellcorner=getcellsforcorner(i,j,temp);
                double each_corner_prob=cornerprob/(cellcorner+1);
                //System.out.println(each_corner_prob);
                if(temp[i][j].getObstacle().equalsIgnoreCase("No")) {
                    double val=(each_corner_prob * temp[i][j].getVal());
                    if(j!=0) {
                        int cellside=getcellsforedge(i,j-1,temp);
                        double each_side_prob=0;
                        if(cellside!=0)each_side_prob=sideprob/cellside;
                        //System.out.println(each_side_prob);
                        val=val+each_side_prob*temp[i][j-1].getVal();
                        if (i!=(n-1)) {
                            int cellcorner2=getcellsforcorner(i+1,j-1,temp);
                            double each_corner_prob2=cornerprob/(cellcorner2+1);
                            val=val+each_corner_prob2*temp[i+1][j-1].getVal();
                        }
                        if(i!=0) {
                            int cellcorner3=getcellsforcorner(i-1,j-1,temp);
                            double each_corner_prob3=cornerprob/(cellcorner3+1);
                            val=val+each_corner_prob3*temp[i-1][j-1].getVal();
                        }
                        //System.out.println("val here2"+val);
                    }
                    if(j!=(m-1)) {
                        int cellside=getcellsforedge(i,j+1,temp);
                        double each_side_prob=0;
                        if(cellside!=0)each_side_prob=sideprob/cellside;
                        val=val+each_side_prob*temp[i][j+1].getVal();
                        if (i!=(n-1)) {
                            int cellcorner2=getcellsforcorner(i+1,j+1,temp);
                            double each_corner_prob2=cornerprob/(cellcorner2+1);
                            val=val+each_corner_prob2*temp[i+1][j+1].getVal();
                        }
                        if(i!=0) {
                            int cellcorner3=getcellsforcorner(i-1,j+1,temp);
                            double each_corner_prob3=cornerprob/(cellcorner3+1);
                            val=val+each_corner_prob3*temp[i-1][j+1].getVal();
                        }
                        //System.out.println("val here3"+val);
                    }
                    if(i!=0) {
                        int cellside=getcellsforedge(i-1,j,temp);
                        double each_side_prob=0;
                        if(cellside!=0)each_side_prob=sideprob/cellside;
                        val=val+each_side_prob*temp[i-1][j].getVal();
                    }
                    if(i!=(n-1)) {
                        int cellside=getcellsforedge(i+1,j,temp);
                        double each_side_prob=0;
                        if(cellside!=0)each_side_prob=sideprob/cellside;
                        val=val+each_side_prob*temp[i+1][j].getVal();
                        //System.out.println("val here4"+val);
                        }
                    //System.out.println(val);
                    this.room[i][j].setVal(val);
                    }

                }

            }
        //printgrid();
        }
    public void updateforevidence(int x,int y, int result) {
        updatefortime();
        double prob;
        double normalize=0.0;
        if(result==1) prob=0.85;
        else prob=0.15;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                double val=this.room[i][j].getVal();
                if(this.room[i][j].getObstacle().equalsIgnoreCase("No")) {
                    if ((i == x && j == y) || (i == x - 1 && j == y) || (i == x + 1 && j == y) || (i == x && j == y - 1) || (i == x - 1 && j == y - 1) || (i == x + 1 && j == y - 1) || (i == x && j == y + 1) || (i == x - 1 && j == y + 1) || (i == x + 1 && j == y + 1)) {
                        val = prob * val;

                    } else val = val * (1-prob);
                }
                else val=0.0;
                this.room[i][j].setVal(val);
                normalize=normalize+val;
            }
        }
        //printgrid();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                double unnorm=this.room[i][j].getVal();
                this.room[i][j].setVal(unnorm/normalize);
                }
            }
        printgrid();
    }
    public void getCasperPosition() {
        int[] casperposition=new int[2];
        double max=-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(this.room[i][j].getVal()>max) {
                    max=this.room[i][j].getVal();
                    casperposition[0]=i;
                    casperposition[1]=j;
                }
            }
        }
        System.out.println("Casper is most probably at ("+casperposition[0]+","+casperposition[1]+")");
    }

}
