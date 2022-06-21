import Services.ExpenseManager;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addUser(new User("u1", "sumit"));
        expenseManager.addUser(new User("u2", "shree"));
        expenseManager.addUser(new User("u3", "kavin"));
        expenseManager.addUser(new User("u4", "gaurav"));

        Scanner sc = new Scanner(System.in);

        while(true){
            String command = sc.nextLine();
            String[] commands = command.split(" ");
            String comndType = commands[0];
            switch(comndType.toUpperCase()){
                case "SHOW":
                    if(commands.length == 1) expenseManager.showBalances();
                    else expenseManager.showBalance(commands[1]);
                    break;
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers].toUpperCase();
                    List<Split> splits = new ArrayList<>();
                    switch(expenseType){
                        case "EQUAL":
                            for(int i=0; i<noOfUsers; i++){
                                splits.add(new EqualSplit(expenseManager.getUserMap().get(commands[4+i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits);
                            break;
                        case "EXACT":
                            for(int i=0; i<noOfUsers; i++){
                                splits.add(new ExactSplit(expenseManager.getUserMap().get(commands[4+i]),Double.parseDouble(commands[5+noOfUsers+i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits);
                            break;
                        case "PERCENT":
                            for(int i=0; i<noOfUsers; i++){
                                splits.add(new PercentSplit(expenseManager.getUserMap().get(commands[4+i]),Double.parseDouble(commands[5+noOfUsers+i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits);
                            break;
                    }
                break;
            }
        }
    }
}
