package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private int[][] boardposition;
    private int moves;
    private Node parent;
    private ArrayList<Node> neighbours;

    public Node(int[][] boardposition,int g,Node parent) {
        this.boardposition = boardposition;
        this.moves=g;
        this.parent=parent;
        neighbours=new ArrayList<>();
    }

    public int[][] getBoardposition() {
        return boardposition;
    }

    public int getMoves() {
        return moves;
    }

    public Node getParent() {
        return parent;
    }
    public int getf(String process) {
        if(process.equalsIgnoreCase("Hamming Distance")) return this.getMoves()+this.getHammingdistance();
        else if(process.equalsIgnoreCase("Manhattan Distance")) return this.getMoves()+this.getmanhattan();
        else return this.getMoves()+this.getlinearconflict();
    }
    public int getHammingdistance() {
        int gridsize=Main.gridsize;
        int misplaced=0;
        int k=1;
        for(int i=0;i<gridsize;i++) {
            for(int j=0;j<gridsize;j++) {
                if(this.boardposition[i][j]!=0 && this.boardposition[i][j]!=k) misplaced++;
                k++;
            }
        }
        return misplaced;
    }
    public int getmanhattan() {
        int k=Main.gridsize;
        int sumdistance=0;
        for(int i=0;i<k;i++) {
            for(int j=0;j<k;j++) {
                if(this.boardposition[i][j]!=0) {
                    int targetX=(this.boardposition[i][j]-1)/k;
                    int targetY=(this.boardposition[i][j]-1)%k;
                    sumdistance=sumdistance+Math.abs(i-targetX)+Math.abs(j-targetY);
                }
            }
        }
        return sumdistance;
    }
    public int getlinearconflict() {
        int k=Main.gridsize;
        int count=1;
        int linear_conflict=0;
        HashMap<Integer,Integer> rowmap=new HashMap<>();
        for(int i=0;i<k;i++) {
            for(int j=0;j<k;j++)  {
                rowmap.put(count,i);
                count++;
            }
        }
        for(int i=0;i<k;i++) {
            for(int j=0;j<k;j++) {
                if(boardposition[i][j]!=0 && i==rowmap.get(boardposition[i][j])) {
                    for(int l=j+1;l<k;l++) {
                        if(boardposition[i][l]!=0 && i==rowmap.get(boardposition[i][l]) && boardposition[i][j]>boardposition[i][l]) linear_conflict++;
                    }
                }
            }
        }
        return getmanhattan()+(2*linear_conflict);
    }
    public void printarray() {
        for(int j=0;j<Main.gridsize;j++) {
            for(int k=0;k<Main.gridsize;k++) {
                if(this.boardposition[j][k]==0)System.out.print("* ");
                else System.out.print(this.boardposition[j][k]+" ");
            }
            System.out.println();
        }
    }
    public ArrayList<Node> getneighbours() {
        neighbours.clear();
        int k=Main.gridsize;
        for(int i=0;i<k;i++) {
            for(int j=0;j<k;j++) {
                if(boardposition[i][j]==0) {
                    if(i>=1) {
                        int[][] temparray=new int[k][k];
                        boardposition[i][j]=boardposition[i-1][j];
                        boardposition[i-1][j]=0;
                        for(int m=0;m<k;m++) {
                            for(int n=0;n<k;n++) {
                                temparray[m][n]=boardposition[m][n];
                            }
                        }
                        Node newnode=new Node(temparray,this.moves+1,this);
                        neighbours.add(newnode);
                        boardposition[i-1][j]=boardposition[i][j];
                        boardposition[i][j]=0;

                    }
                    if(i<k-1) {
                        int[][] temparray=new int[k][k];
                        boardposition[i][j]=boardposition[i+1][j];
                        boardposition[i+1][j]=0;
                        for(int m=0;m<k;m++) {
                            for(int n=0;n<k;n++) {
                                temparray[m][n]=boardposition[m][n];
                            }
                        }
                        Node newnode=new Node(temparray,this.moves+1,this);
                        neighbours.add(newnode);
                        boardposition[i+1][j]=boardposition[i][j];
                        boardposition[i][j]=0;

                    }
                    if(j>=1) {
                        int[][] temparray=new int[k][k];
                        boardposition[i][j]=boardposition[i][j-1];
                        boardposition[i][j-1]=0;
                        for(int m=0;m<k;m++) {
                            for(int n=0;n<k;n++) {
                                temparray[m][n]=boardposition[m][n];
                            }
                        }
                        Node newnode=new Node(temparray,this.moves+1,this);
                        neighbours.add(newnode);
                        boardposition[i][j-1]=boardposition[i][j];
                        boardposition[i][j]=0;

                    }
                    if(j<k-1) {
                        int[][] temparray=new int[k][k];
                        boardposition[i][j]=boardposition[i][j+1];
                        boardposition[i][j+1]=0;
                        for(int m=0;m<k;m++) {
                            for(int n=0;n<k;n++) {
                                temparray[m][n]=boardposition[m][n];
                            }
                        }
                        Node newnode=new Node(temparray,this.moves+1,this);
                        neighbours.add(newnode);
                        boardposition[i][j+1]=boardposition[i][j];
                        boardposition[i][j]=0;

                    }
                }
            }
        }
        return neighbours;
    }
    public boolean isGoalState() {
        int count=1;
        int k=Main.gridsize;
        for(int i=0;i<k;i++) {
            for(int j=0;j<k;j++) {
                if(this.boardposition[i][j]!=Main.goalboard[i][j]) return false;

            }
        }
        return true;
    }
}
