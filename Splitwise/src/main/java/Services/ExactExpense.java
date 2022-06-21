package Services;

import models.ExactSplit;
import models.Split;
import models.User;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(Double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
    }

    @Override
    public boolean validate(){
        double totAmt = getAmount();
        double totalSplitAmount = 0;
        for(Split split: getSplits()){
            if(!(split instanceof ExactSplit)){
                return false;
            }else{
                ExactSplit exactSplit = (ExactSplit) split;
                totalSplitAmount += exactSplit.getAmount();
            }
        }
        if(totAmt != totalSplitAmount) return false;
        return true;

    }
}
