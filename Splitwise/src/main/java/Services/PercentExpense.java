package Services;

import models.PercentSplit;
import models.Split;
import models.User;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(Double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
    }

    @Override
    public boolean validate() {
        double totalPercent = 100;
        double sumSplit = 0;
        for(Split split: getSplits()){
            if(!(split instanceof PercentSplit)) return false;
            else{
                PercentSplit exactSplit = (PercentSplit) split;
                sumSplit += exactSplit.getPercent();
            }
        }
        if(sumSplit != totalPercent) return false;
        return true;
    }
}
