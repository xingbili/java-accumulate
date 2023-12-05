/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghuolin
 * @date 2023/3/13 15:25
 */
class Solution {

  private List<List<Integer>> result = new ArrayList<>();
  private ArrayList<Integer> solution = new ArrayList<>();

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    backtracking(candidates, target, 0);
    return result;
  }

  public void backtracking(int[] candidates, int residual, int start) {
    if (residual < 0) {
      return;
    }

    if (residual == 0) {

      result.add((List<Integer>) solution.clone());
      return;
    }
    for (int i = start; i < candidates.length; i++) {
      solution.add(candidates[i]);
      backtracking(candidates, residual - candidates[i], i);
      solution.remove(solution.size() - 1);
    }
  }

  public static void main(String[] args) {
    int[] a = {2, 3, 6, 7};

    Solution solution = new Solution();
    System.out.println( solution.combinationSum(a, 7));
  }

}