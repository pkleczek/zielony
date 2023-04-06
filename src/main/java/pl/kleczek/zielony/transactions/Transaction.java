package pl.kleczek.zielony.transactions;

import java.math.BigDecimal;

public record Transaction(String debitAccount, String creditAccount, BigDecimal amount) {
}
