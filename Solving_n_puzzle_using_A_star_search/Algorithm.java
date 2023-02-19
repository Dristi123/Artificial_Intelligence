package com.company;

import java.util.*;


public class Algorithm {
    int gridsize;
    ArrayList<Node> closedlist;
    PriorityQueue<Node>l;
    Node startnode;
    int explored_nodes;
    int expanded_nodes;
    int flag=0;

    public Algorithm(Node start) {
        this.gridsize = Main.gridsize;
        startnode=start;

    }
    public void A_star_Heuristic(String process) {

        l=new PriorityQueue<Node>(gridsize,new Node_Comparator(process));
        l.add(startnode);
        //map=new HashMap<>();
        closedlist=new ArrayList<>();
        //System.out.println(closedlist.size());
        //closedlist.clear();
        //System.out.println(l.size());
        expanded_nodes=0;
        explored_nodes=1;
        Node promisingnode;
        //System.out.println("Shurute size"+l.size());
        while (!l.isEmpty()) {
            promisingnode=l.poll();
            if(promisingnode.isGoalState()) {
                //System.out.println("Goal State Recahed");
                if(flag==0) {
                    System.out.println("Optimal Cost:" + promisingnode.getf(process));
                    System.out.println("Steps to reach goal state");
                    System.out.println();
                    Node current = promisingnode;
                    ArrayList<Node> finalpath = new ArrayList<>();
                    while (current != null) {
                        //current.printarray();
                        finalpath.add(current);
                        current = current.getParent();
                    }
                    Collections.reverse(finalpath);
                    for (int i = 0; i < finalpath.size(); i++) {
                        System.out.println("Step " + Integer.valueOf(i + 1));
                        finalpath.get(i).printarray();
                        System.out.println();
                    }
                    flag=1;
                }
                System.out.println("For "+process+" Heuristic");
                System.out.println("Number of expanded nodes:"+expanded_nodes);
                System.out.println("Number of explored nodes:"+explored_nodes);
                return;
            }
            closedlist.add(promisingnode);
            expanded_nodes++;
            ArrayList<Node>neighbours=promisingnode.getneighbours();
            for(int i=0;i<neighbours.size();i++) {
                int j;
                for(j=0;j<closedlist.size();j++) {
                    if(Arrays.deepEquals(closedlist.get(j).getBoardposition(),neighbours.get(i).getBoardposition())) {
                        //System.out.println("ekhan");
                        break;
                    }
                }

                if(j==closedlist.size()) {
                    //System.out.println("here");
                    Node n=neighbours.get(i);
                    l.add(n);
                    explored_nodes++;
                }
            }
        }
    }

}
