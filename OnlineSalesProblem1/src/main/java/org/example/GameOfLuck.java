package org.example;

import java.util.Map;
import java.util.Scanner;

public class GameOfLuck {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        EventMap eventMap=new EventMap();
        System.out.println("Welcome! To Close the program ,type 'CLOSE' all caps");
        String input="";
        String event="";
        double probability=0.0;
        do{
            System.out.println("Enter the Event name");
            event=(input=sc.next());
            if(eventMap.isEventPresent(event)){
                System.out.println("Event Already Present");
            }else{
                System.out.println("Enter the Probability of getting "+event);
                probability=Double.parseDouble(input=sc.next());
                eventMap.addEvent(event,probability);
            }
            System.out.println("Do you more event to handle ,type any for yes " +
                    "'N' for No");
            if(sc.next().equals("N"))break;
        }while(true);
        System.out.println("Enter the times of rolling or tossing");
        int times=Integer.parseInt(sc.next());
        Map<String ,Integer> resultMap=eventMap.getResult(times);
        for( String eventResult:resultMap.keySet()){
            System.out.println(eventResult +" has Occured "+resultMap.get(eventResult)+" that is approx "+((double)(resultMap.get(eventResult)*100.00/(double)times)));
        }
    }
}