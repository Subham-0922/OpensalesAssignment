package org.example;

import java.util.*;

public class EventMap {
//    Attribute ,We are using a map interface to save the Events and Probability
//    It's a LinkedHashMap so that we can track the insertion order
    private Map<String ,Double> events=new LinkedHashMap<>();
//_______________________________________________

//Getter and Setter for the attribute events
    public void setEventsMap(Map<String, Double> events) {
        this.events = events;
    }
    public Map<String,Double> getEventsMap(){
        return events;
    }
//------------------------------------------------------------------------
//Helping methods
//    addEvent method to add a new event in the working map
    public boolean addEvent(String event, double probability){
        if(isEventPresent(event)){
            return false;
        }else{
            return events.put(event,probability)==null;
        }
    }
//    isEventPresent method is for checking if the Event is present to avoid adding same
//    event again in the working map
    public boolean isEventPresent(String event){
        return events.containsKey(event);
    }
//    getNumbersOfEvent method is to count the number of events present here
    public int getNumbersOfEvent(){
        return events.size();
    }
//    getEvents method is form a arraylist of events to produce a structured result
    public List<String> getEvents(){
       return new ArrayList<>(events.keySet());
    }
//    getProbability method which is to convert the probability to percentage term and
//    to convert it to an array
    public double[] getProbabilityArray(){
        List<Double> probabilityGiven=events.values().stream().toList();
        double total=probabilityGiven.stream().mapToDouble(value -> value).sum();
        double []probabilityConvertedToPercentage=probabilityGiven.stream().mapToDouble(a->a/total*100.0).toArray();
        return probabilityConvertedToPercentage;
    }
//    getResult is final method where we are the doing the operations needed to get the
//    desired result,
    public Map<String,Integer> getResult(int times){
//        here creating an array of size of number of events to store the occurance of it
        int [] countsOfEvent=new int [getNumbersOfEvent()];
//        here creating another array to store the percentage array of probability given
//        the user
        double [] probabilityArray= getProbabilityArray();
//        intiating a for loop to get the event for the number of times given
        for(int i=0;i<times;i++){
//            Here ,generating a random number between 0.00 to 1.00
//            now multiplying it to 100 to convert it into percentage
            double randDouble= Math.random()*100.0;
//            here taking a variable to store the addition of probability
//            we get by adding certain portion of probabilities present in
//            probabilityArray , refer line 65
            double summedProbability=0.0;
//            taking an inner loop to to summing the percentage present in the
//            probabilityArray , refer line 65
            for(int j=0;j<getNumbersOfEvent();j++){
//                simple summing of probability
                summedProbability+=probabilityArray[j];
//                Now checking if the random double(which is also in percentage form) value
//                is become less than summedProbability
                if(randDouble<summedProbability){
//                    if yes, then we got that this time we get an event  present in j
//                    index event and doing an increment
                    countsOfEvent[j]++;
//                    we get the occurrence in this time so no need to run further

                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(countsOfEvent));
//        creating a result to the caller for better presentation
//        again using linked hash map to prevent insertion order
        Map<String,Integer> result=new LinkedHashMap<>();
        for(int i=0;i<countsOfEvent.length;i++){
            result.put(getEvents().get(i),countsOfEvent[i]);
        }
        return result;

    }



}
