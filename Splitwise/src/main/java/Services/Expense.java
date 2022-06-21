package Services;

import models.Split;
import models.User;

import java.util.List;

public abstract class Expense {
    private double amount;
    private User paidBy;
    private List<Split> splits;

    public Expense(double amount, User paidBy, List<Split> splits){
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public abstract boolean validate();

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amt){
        this.amount = amt;
    }

    public User getPaidBy(){
        return paidBy;
    }

    public List<Split> getSplits(){
        return splits;
    }

    public void setSplits(List<Split> splits){
        this.splits = splits;
    }

    public void setPaidBy(User user){
        this.paidBy = paidBy;
    }


}
