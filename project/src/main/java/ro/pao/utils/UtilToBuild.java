package ro.pao.utils;

import ro.pao.dto.ExchangeRateTo;
import ro.pao.dto.TransactionTo;
import ro.pao.entities.ExchangeRate;
import ro.pao.entities.Transaction;

public abstract class UtilToBuild {
    public static ExchangeRateTo buildTo(ExchangeRate src) {
        if (src == null) {
            return null;
        }
        ExchangeRateTo dst = new ExchangeRateTo();
        copyFrom(src, dst);
        return dst;
    }

    public static TransactionTo buildTo(Transaction src) {
        if (src == null) {
            return null;
        }
        TransactionTo dst = new TransactionTo();
        copyFrom(src, dst);
        return dst;
    }

    private static void copyFrom(ExchangeRate src, ExchangeRateTo dst) {
        dst.setId(src.getId());
        dst.setProvider(src.getProvider());
        dst.setRate(src.getRate());
        dst.setPublishingDate(src.getPublishingDate());
        dst.setQueryDate(src.getQueryDate());
        dst.setReference(src.getReference());
        dst.setTo(src.getTo());
    }

    private static void copyFrom(Transaction src, TransactionTo dst) {
        dst.setId(src.getId());
        dst.setDate(src.getDate());
    }

}