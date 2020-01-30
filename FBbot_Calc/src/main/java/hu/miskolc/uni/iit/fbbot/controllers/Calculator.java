package hu.miskolc.uni.iit.fbbot.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    String operatorRegEx="[+-]";
    String oparandsRegEx="[0-9]+";
    public int calculate(String row){
        System.out.println(row);
        String operators[]=row.split(oparandsRegEx);
        String operands[]=row.split(operatorRegEx);
        int agregate = Integer.parseInt(operands[0]);
        for(int i=1;i<operands.length;i++){
            if(operators[i].equals("+"))
                agregate += Integer.parseInt(operands[i]);
            else
                agregate -= Integer.parseInt(operands[i]);
        }
        return agregate;
    }

    public boolean isValidInput(String row){

        boolean result=false;
        String patternString="(("+oparandsRegEx+")"+operatorRegEx+")*("+oparandsRegEx+")";
        Pattern p = Pattern.compile(patternString);
        Matcher m = p.matcher(row);


        if (m.matches()) {
            result = true;
        }
        return result;
    }


}
