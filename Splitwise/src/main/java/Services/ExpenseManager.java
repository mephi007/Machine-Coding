package Services;

import models.ExpenseType;
import models.Split;
import models.User;

import java.util.*;

public class ExpenseManager {
    List<Expense> expenses;
    private Map<String, User> userMap;
    private Map<String, Map<String, Double>> balanceSheet;
    
    public ExpenseManager(){
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
        expenses = new ArrayList<>();
    }

    public Map<String, User> getUserMap(){
        return userMap;
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<String, Double>());  //user owing to whom
    }

    public void showBalances() {
        boolean isEmpty = true;
        for(Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()){
            for(Map.Entry<String,Double> userBalance : allBalances.getValue().entrySet()){
                if(userBalance.getValue() > 0){
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
        if(isEmpty){
            System.out.println("No Balances");
        }
    }

    public void showBalance(String userId) {

        boolean isEmpty = true;
        for(Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()){
            if(userBalance.getValue() > 0){
                isEmpty = true;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }
    }

    private void printBalance(String user1, String user2, Double amount) {
        String name1 = userMap.get(user1).getName();
        String name2 = userMap.get(user2).getName();
        if(amount < 0){
            System.out.println(name1 + " owes " + name2 + " " + Math.abs(amount));
        }else{
            System.out.println(name2 + " owes " + name1 + " " + Math.abs(amount));
        }
    }


    public void addExpense(ExpenseType expenseType, Double amount, String paidBy, List<Split> splits) {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits);
        expenses.add(expense);
        for(Split split : expense.getSplits()){
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = balanceSheet.get(paidBy);
            if(!balances.containsKey(paidTo)){
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo)+ split.getAmount());

            balances = balanceSheet.get(paidTo);
            if(!balances.containsKey(paidBy)){
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }
}
