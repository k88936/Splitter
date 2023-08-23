package org.kvto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class Segment {
    
    public static int GreenScore=200;
    public static int CostScore=1;
    public static int WasteScore=100;
    



    Splittable root;
    List<Splittable> ends;
    List<Splittable> combination;
    HashSet<Splittable> visited;
    Segment(List<Splittable> combination) {

        this.combination=combination;
        root=combination.get(0);
        ends=new ArrayList<Splittable>();
        for(Splittable s:combination) {

            for (Splittable neighbor : s.getNeighbors()) {
                if(combination.contains(neighbor))continue;
                ends.add(neighbor);
            }
        }


       this.visited = new HashSet<>();
        for (Splittable node : combination) {
           visited.add(node);
        }



    }
    public int evaluate() {
        int score=0;

     for (Splittable node : combination) {
        
        List<Splittable> neighbors = node.getNeighbors();

        int count=0;
        for (Splittable neighbor : neighbors) {
            if(neighbor.mode==mode.green)count++;
        }

        if(count>1)score+=(count-1)*count*GreenScore;
        

     }

     int totalCost=0;
     
            for (Splittable node : visited) {
                totalCost+=node.cost;
            }

    score+=totalCost*CostScore;

    for (Splittable end : ends) {
        if(end.depenCost<800){
            score-=(800-end.depenCost)*WasteScore;
        }
    }

    return  score;



    }

}
