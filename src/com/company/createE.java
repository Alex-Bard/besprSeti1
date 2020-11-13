package com.company;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class createE {
    private HashSet<Integer> E;
    public  createE(int len){
        int start = 1;
        HashSet<Integer> Emas = new HashSet<Integer>();
        for (int i = 0; i < len; i++){
            int e =  (int)Math.pow(2,i);
            Emas.add(e);
        }
        for (int i = 0; i < len-1; i++){
            for (int k = start; k < len; k++){
                int e =  (int)(Math.pow(2,i) + Math.pow(2,k));
                Emas.add(e);
            }
            start++;
        }
        this.E = Emas;
    }
    public ArrayList getEmas(){
        ArrayList<Integer> sortedList = new ArrayList(this.E);
        Collections.sort(sortedList);
        return sortedList;
        /*for (Integer i : sortedList) {
            System.out.println(toVectorLen(i,7));
        }*/
    }
    public String toVectorLen(int vec, int len){
        String vector = Integer.toBinaryString(vec);
        if (len != 0){
            while (vector.length()<len){
                vector = "0" + vector;
            }
        }
        return vector;
    }
}
