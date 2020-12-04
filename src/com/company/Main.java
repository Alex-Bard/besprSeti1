package com.company;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


final class MyResult {
    public final int error;
    public final int sindrom;

    public MyResult(int error, int sindrom) {
        this.error = error;
        this.sindrom = sindrom;
    }
}

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        int gx,k,e,m,r,cx,a,b,s,d;

        System.out.print("Введите вектор g(x):");
        gx = Integer.parseInt(in.nextLine(),2);
        System.out.print("Введите k:");
        k = Integer.parseInt(in.nextLine(),10);
        System.out.print("Введите d:");
        d = Integer.parseInt(in.nextLine(),10);
       //System.out.print("Введите вектор e:");
        //e = Integer.parseInt(in.nextLine(),2);
        System.out.print("Введите вектор m:");
        m = Integer.parseInt(in.nextLine(),2);
        //System.out.println("Вычисление С(x):");

        r = Integer.toBinaryString(gx).length() - Integer.toBinaryString(gx).indexOf("1") - 1;
        createE Emas =  new createE(k+r, d);
        ArrayList<Integer> E =  Emas.getEmas();
        for (Integer i : E){
            MyResult result = coderDecoder(gx, k, i, m, r);
            if (verdict(result,false)){
                System.out.println(toVectorLen(i,k+r));
            }
        }
        printCodeWords(createCodeWords(k+r,gx),k+r);



    }

    public static MyResult coderDecoder(int gx,int k,int e,int m, int r){

        int cx,a,b,s;
        cx = mod((m << r), gx);
        //cx = verifyStep("cx", cx, r, true);
        //System.out.println("Вычисление вектора а:");
        a = (m << r) + cx;
        //a = verifyStep("a", a, k+r, true);
       // System.out.println("Вычисление вектора b:");
        b = a ^ e;
        //b = verifyStep("b", b,k+r,false);

        //System.out.println("Вычисление синдрома s:");
        s = mod(b, gx);
        //s = verifyStep("s", s,0,true);
        MyResult result = new MyResult(e, s);

        return result;
    }

    public static boolean verdict(MyResult result, boolean mode){
        boolean ver = false;
        if (mode) {
            if (result.sindrom == 0)
                System.out.println("Вердикт декодера: ошибок не произошло");//Вердикт декодера: ошибок не произошло
            else System.out.println("Вердикт декодера: ошибка");//Вердикт декодера: ошибка

            if (result.sindrom == 0) {
                if (result.error == 0) System.out.println("Ошибок не произошло");//Ошибок не произошло
                else System.out.println("Ошибка декодером не обнаружена");//Ошибка декодером не обнаружена
            }
            if (result.sindrom != 0) System.out.println("Ошибка декодером обнаружена");//Ошибка декодером обнаружена
        }
        if (result.sindrom == 0) {
            if (result.error != 0) ver = true;//Ошибок не произошло
        }
        return ver;
    }

    public static int verifyStep(String step, int func, int len, boolean onlyPrint) {
        System.out.println("был вычислен " + step + ", равный " +  toVectorLen(func, len));
        if (!onlyPrint) {
            System.out.println(" хотите изменить? (д/н)");
            if (in.nextLine().toLowerCase().equals("д")) {
                System.out.println("введите " + step);
                func = Integer.parseInt(in.nextLine(), 2);
            }
        }
        return func;
    }
    public static String toVectorLen(int vec, int len){
        String vector = Integer.toBinaryString(vec);
        if (len != 0){
            while (vector.length()<len){
                vector = "0" + vector;
            }
        }
        return vector;
    }
    public static int mod(int a, int b){
        int tmp;
        int degA = Integer.toBinaryString(a).length() - Integer.toBinaryString(a).indexOf("1") - 1;
        int degB = Integer.toBinaryString(b).length() - Integer.toBinaryString(b).indexOf("1") - 1;
        if (degA < degB){
            return a;
        }
        tmp = b << (degA - degB);
        a = mod(a ^ tmp , b);
        return a;
    }
    public static ArrayList createCodeWords(int len, int gx){
        ArrayList<Integer> ListCodeWords = new ArrayList();
        for(int i = 0; i < Math.pow(2, len); i++){
            if (mod(i,gx) == 0){
                ListCodeWords.add(i);
            }
        }
        CodeWorsd c = new CodeWorsd(ListCodeWords);
        return c.getMassKey();
    }
    public static void printCodeWords(ArrayList<Integer> ListCodeWords, int len){
        System.out.println("Все кодовые слова:");
        for (Integer i : ListCodeWords) {
            System.out.println(toVectorLen(i,len));
        }
    }
}
