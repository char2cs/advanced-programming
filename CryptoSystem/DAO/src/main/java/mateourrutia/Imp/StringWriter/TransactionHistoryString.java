package mateourrutia.Imp.StringWriter;

import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Imp.TransactionHistoryImp;
import mateourrutia.utils.persistance.StringWriter;

public class TransactionHistoryString extends TransactionHistoryImp<StringWriter<TransactionHistory>> {
    @Override
    protected StringWriter<TransactionHistory> getTransactionHistoryImp() {
        return new StringWriter<>( TransactionHistory.class.getSimpleName() );
    }
}
