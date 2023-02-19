package com.company;

import java.util.Comparator;

public class Node_Comparator implements Comparator<Node> {
    String process;
    Node_Comparator(String process) {
        this.process=process;
    }
    @Override
    public int compare(Node o1, Node o2) {
          return Integer.compare(o1.getf(process),o2.getf(process));
    }
}
