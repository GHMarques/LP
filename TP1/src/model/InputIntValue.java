/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Scanner;
/**
 *
 * @author Aluno
 */
public class InputIntValue extends IntValue{
    private String stringValue;

    public InputIntValue(String stringValue, int Line){
        super(Line);
        this.stringValue = stringValue;
    }
    
    @Override
    public Integer value() {
        System.out.println(stringValue);
        Scanner ler = new Scanner(System.in);
        int num = ler.nextInt();
        return num;
    }
    
}
