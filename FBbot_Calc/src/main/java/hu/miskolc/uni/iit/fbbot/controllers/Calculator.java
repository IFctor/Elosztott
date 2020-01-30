package hu.miskolc.uni.iit.fbbot.controllers;

public class Calculator {

    public int calculate(String row){

        int result=0;
        Character lastOperation=' ';
        String lastNumber="";
        for(int index =0; index<row.length();index++){
            Character actualChar=row.charAt(index);
            if(Character.isDigit(actualChar))
            {
                lastNumber+=actualChar;
            }else if (actualChar.equals('+') || actualChar.equals('-')){
                if(lastOperation.equals(' ')){
                    lastOperation=actualChar;
                }
                else{
                    //error - művelet halmozás
                }
            }
            else{
                //Error - nem megfelelő karakter
            }
        }

        return result;
    }

    private boolean charIsNumeric(char character){

        return false;
    }


}
