package mateourrutia.Imp.FileWriter;

import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Imp.TransactionHistoryImp;
import mateourrutia.utils.persistance.FileWriter;

public class TransactionHistoryFile extends TransactionHistoryImp<FileWriter<TransactionHistory>> {

    @Override
    protected FileWriter<TransactionHistory> getTransactionHistoryImp() {
        return new FileWriter<>( TransactionHistory.class.getSimpleName() );
    }

}
