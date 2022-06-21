package models;

public abstract class Split {
    private User user;
    double amount;
    public Split(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amt){
        this.amount = amt;
    }
}
