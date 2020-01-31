package hu.miskolc.uni.iit.fbbot.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
/**
 * a+b*c-d/e
 * 1.   b*c
 * 2.   d/e
 * 3.   a+1.
 * 4.   3.-2.
 * Előszőr szétválasztani a + és - mentén
 * Aztán vizsgálni, hogy a rész tartalmaz-e * vagy / -t
 * Azt elvégezni
 * és annak az értékét vissza adni
 */

    String allOperatorRegEx ="[+-/*/]";
    String additiveOperatorRegEx="[+-]";
    String multiplicativeOperatorRegEx="[/*/]";
    String oparandsRegEx="[0-9]+";

    public double calculate(String row){
        String additivMembers[] =separateAdditivMember(row);
        String additivOperators[]=row.split(oparandsRegEx+
                "("+multiplicativeOperatorRegEx+oparandsRegEx+")*");
        double result = calculateMultiplicativMember(additivMembers[0]);
        for(int i=1; i<additivMembers.length;i++)
        {
            if(additivOperators[i].equals("+"))
                result += calculateMultiplicativMember(additivMembers[i]);
            else
                result -= calculateMultiplicativMember(additivMembers[i]);
        }


        return result;
    }

    public boolean isValidInput(String row){

        boolean result=false;
        String patternString="(("+oparandsRegEx+")"+ allOperatorRegEx +")*("+oparandsRegEx+")";
        Pattern p = Pattern.compile(patternString);
        Matcher m = p.matcher(row);

        if (m.matches()) {
            result = true;
        }
        return result;
    }

    public boolean hasDividebyZero(String row){
        boolean result=false;
        String patternString="[/][0]";
        Pattern p = Pattern.compile(patternString);
        Matcher m = p.matcher(row);

        if (m.find()) {
            result = true;
        }
        return result;
    }


    private String[] separateAdditivMember(String row){
        String additivMembers[]=row.split(additiveOperatorRegEx);

                return additivMembers;
    }

    private double calculateMultiplicativMember(String row)
    {
        String operators[]=row.split(oparandsRegEx);
        String operands[]=row.split(multiplicativeOperatorRegEx);
        double result = Integer.parseInt(operands[0]);
        for(int i=1;i<operands.length;i++){
            if(operators[i].equals("*"))
                result *= Integer.parseInt(operands[i]);
            else
                result /= Integer.parseInt(operands[i]);
        }
        return result;
    }
}
