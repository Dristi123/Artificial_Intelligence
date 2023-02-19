package com.company;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Enter the value of n,m and k");
        int n,m,k;
        gridentry [][] grid;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        //System.out.println(n);
        m=sc.nextInt();
        //System.out.println(m);
        grid=new gridentry[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                grid[i][j]=new gridentry();
            }
        }
        k=sc.nextInt();
        //System.out.println(k);
        int u,v;
        System.out.println("Enter "+k+" cells denoting obstacles");
        for(int i=0;i<k;i++) {
            u=sc.nextInt();
            //System.out.println(u);
            v=sc.nextInt();
            //System.out.println(v);
            grid[u][v].setObstacle("Yes");
        }
        String s=null;
        s=sc.nextLine();
        Finding_Casper fc=new Finding_Casper(grid,n,m,k);
        System.out.println("Initial grid is formed!");
        fc.printgrid();
        while (true) {
            s=sc.nextLine();
            System.out.println(s);
            String[] splitted=s.split(" ");
            if(splitted[0].equalsIgnoreCase("R")) {
                int x,y,b;
                x=Integer.parseInt(splitted[1]);
                y=Integer.parseInt(splitted[2]);
                b=Integer.parseInt(splitted[3]);
                fc.updateforevidence(x,y,b);

            }
            else if(splitted[0].equalsIgnoreCase("T")) {
                //System.out.println("Casper!");
                fc.updatefortime();
            }
            else if(splitted[0].equalsIgnoreCase("C")) {
                //System.out.println("Casper!");
                fc.getCasperPosition();
            }
            else if(splitted[0].equalsIgnoreCase("Q")) {
                System.out.println("Bye Casper!");
                System.exit(0);
            }
            else {
                System.out.println("Not a valid command!");
            }
        }
    }
}
