package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int gridsize;
    public static int[][] goalboard;
    public static boolean isSolvable(int[][] board,int gridsize) {
        ArrayList<Integer> a1 =new ArrayList<Integer>();
        int inversion=0;
        for(int i=0;i<gridsize;i++) {
            for (int j=0;j<gridsize;j++) {
                if(board[i][j]!=0) a1.add(board[i][j]);
            }
        }
        for(int i=0;i<a1.size()-1;i++) {
            for(int j=i+1;j<a1.size();j++) {
                if(a1.get(i)>a1.get(j)) inversion++;
            }
        }

        if(gridsize%2==1) {
            if(inversion%2==1) return false;
            else return true;
        }
        else {
            int blankrow=-1;
            for(int i=gridsize-1;i>=0;i--) {
                for(int j=gridsize-1;j>=0;j--) {
                    if(board[i][j]==0) {
                        blankrow=gridsize-i;
                        break;
                    }
                }
            }
            if((blankrow%2==1 && inversion%2==0)||(blankrow%2==0 && inversion%2==1)) return true;
            else return false;
        }

    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please Enter Grid Size");
        gridsize=scanner.nextInt();
        int[][] initalboard=new int[gridsize][gridsize];
        System.out.println("Enter the elements of the initial board");
        for(int i=0;i<gridsize;i++) {
            for(int j=0;j<gridsize;j++) {
                String temp=(scanner.next());
                if(temp.equals("*")) initalboard[i][j]=0;
                else initalboard[i][j]=Integer.valueOf(temp);
            }
        }

        if(isSolvable(initalboard,gridsize)) {
            goalboard=new int[gridsize][gridsize];
            int count=1;
            for(int i=0;i<gridsize;i++) {
                for(int j=0;j<gridsize;j++) {
                    if(i==gridsize-1 && j==gridsize-1) goalboard[i][j]=0;
                    else goalboard[i][j]=count;
                    count++;
                }
            }
            Node startnode=new Node(initalboard,0,null);
            Algorithm A_Star=new Algorithm(startnode);
            A_Star.A_star_Heuristic("Hamming Distance");
            System.out.println();
            A_Star.A_star_Heuristic("Manhattan Distance");
            System.out.println();
            A_Star.A_star_Heuristic("Linear Conflict");

        }
        else System.out.println("The initial board is not solvable");
    }
}
