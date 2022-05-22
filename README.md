# ie343-project
1-How does the execution time change when problem size is increased? Report it by running the same algorithm for the given datasets.
Answer: I did not report because the data set was not given. However, since the complexity of the problem we solve and the code we write is in polynomial time, execution time also increases as polynomial as the size of the problem increases.

The part that is written by me:  
First, I set the p-value equal to 3. (to be able to run the for loop) (If the value wants to be changed, it can be easily changed from the first line.)
I created an <Int,Double> pair by using HashMap in order to represent facility index and sum of all distances from points to facility. 
Then, I found the total distance to a facility by summing the distances calculated for each facility in the distanceMatrix method. And I added this value to the hashMap function with the facility index.
I used the necessary functions to put the values that I placed in the HashMap function in ascending order.
To check the correctness of the code, I created a for loop that prints the facility index and total distances. I've checked that the facility with the lowest overall distance is printed first in each attempt.
In the last step, I created a for loop that allows the facility to be opened (added the facility to the openedFacilities arrayList) starting from the lowest value until it reaches the p value.


The parts that are not included in the original code and that I added (the full code is in the master branch):

int p = 3;
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

