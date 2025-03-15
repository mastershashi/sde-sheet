//package PatternWise.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumProblem {
    // here we don't have to check whether subset sum is present or not , we need to print list of all the subset hvaing sum as target
    // we will using backtracking to find it in 2^n complexity
    // in dp we only check whether subset sum is present or not
    void backtrack(int arr[], int target, int start, List<Integer> currentSubset, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(currentSubset));
            return;
        }
        for( int i = start ;i < arr.length ;i++){
            if( target < arr[i]){
                continue;
            }
            currentSubset.add(arr[i]);
            backtrack(arr, target-arr[i], i+1,currentSubset, result);//backtrack 
            currentSubset.remove(currentSubset.size() -1); // remove the element as it is not intended path
        }
    }
    List<List<Integer>> findSubset(int arr[], int target){

        List<List<Integer>> result = new ArrayList<>();

        backtrack(arr, target, 0, new ArrayList<>(), result);

        return result;
    }
    public static void main(String[] args) {
        SubsetSumProblem obj = new SubsetSumProblem();
        System.out.println(obj.findSubset(new int[]{3, 34, 4, 12, 5, 2}, 9));
        
    }
    
}
