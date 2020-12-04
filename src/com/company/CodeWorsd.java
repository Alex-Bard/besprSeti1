package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
class hashTable {
    public int key;
    public int val;
    public boolean itWas = false;
    public hashTable(int key, int val){
        this.key = key;
        this.val = val;
    }
}
public class CodeWorsd {
    //private  ArrayList<Integer> massKey;
    private  ArrayList<Integer> massVall;
    public  CodeWorsd(ArrayList<Integer> C){
        int N = C.size();
        hashTable table[] = new hashTable[N];
        ArrayList<Integer> massVal = new ArrayList<>();
        ArrayList<Integer> massKey = new ArrayList<>();
        int j =0;
        for (Integer i : C) {
            //map.put(i, count(Integer.toBinaryString(i),"1"));
            table[j] = new hashTable(count(Integer.toBinaryString(i),"1"), i);
            massKey.add(count(Integer.toBinaryString(i),"1"));
            j++;
        }
        Collections.sort(massKey);
        for (Integer i: massKey){
            for (int k = 0; k<N; k++){
                if ((i == table[k].key)&&(!table[k].itWas)){
                    massVal.add(table[k].val);
                    table[k].itWas = true;
                }
            }
        }
        this.massVall = massVal;
    }
    public int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }
    public ArrayList getMassKey(){
        return this.massVall;
    }
}
