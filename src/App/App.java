package App;
import java.lang.Math;
import java.util.*;

import medianProblem.*;
public class App {
    public static void main(String[] args){

        int p = 3;
        Operators operator = new Operators();

        double[][] facs_coordinates = operator.createDistanceMatrix(5,2);
        double[][] points_coordinates = operator.createDistanceMatrix(20,2);
        Facility[] facs = new Facility[5];
        Point[] points = new Point [20];

        for (int i=0; i<facs.length;i++){
            Facility f1 = new Facility(i, facs_coordinates[i][0], facs_coordinates[i][1], 20*Math.random()+50);
            facs[i] = f1;
        }
        for (int i=0; i<points.length;i++){
            Point p1 = new Point (i, points_coordinates[i][0], points_coordinates[i][1], 2*Math.random()+1);
            points[i] = p1;
        }

        double[][] distanceMatrix = operator.distanceMatrix(facs,points);

        ArrayList<Facility> openedFacilities = new ArrayList<>();

        // creates a <Integer, Double> pair.
        // Integer represents the facility index.
        // Double represents the sum of all the distances from facility to points.
        Map<Integer, Double> facilityIndexAndSumOfTheDistancesToEachCustomer = new HashMap<>();

        // create pair item for each facility. And Assign sum of the distances from facility to points as a value to related key.
            for(int i = 0; i < distanceMatrix.length; i++)
            {
                double distanceForFacility = 0;
                for(int j = 0; j < distanceMatrix[0].length; j++)
                {
                    distanceForFacility += distanceMatrix[i][j];
                }
                facilityIndexAndSumOfTheDistancesToEachCustomer.put(i,distanceForFacility);
            }

        // sort the key value pairs in an ascending order
        HashMap<Integer, Double> sourtedFacilityIndexAndSumOfTheDistancesToEachCustomer = new LinkedHashMap<>();
        facilityIndexAndSumOfTheDistancesToEachCustomer.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sourtedFacilityIndexAndSumOfTheDistancesToEachCustomer.put(x.getKey(), x.getValue()));

        // print the sorted hashmap. It is not necessary just used for checking purposes
        for (Map.Entry<Integer, Double> en : sourtedFacilityIndexAndSumOfTheDistancesToEachCustomer.entrySet()) {
            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());
        }

        //Starting from the minimum sum of distances, open the facilities until you reach the value of p.
            for (Map.Entry<Integer, Double> en : sourtedFacilityIndexAndSumOfTheDistancesToEachCustomer.entrySet()) {
                if(openedFacilities.size() != p)
                    openedFacilities.add(facs[en.getKey()]);
            }
    }
}