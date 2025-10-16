package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue()
    private long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserRecord sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserRecord recipient;

    private float amount;

    protected TransactionRecord() {
    }

    public TransactionRecord(UserRecord sender, UserRecord recipient, float amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Transaction[id=%d, sender='%s', recipient='%s' amount='%f'", transactionId, sender, recipient, amount);
    }

    public Long getId() {
        return transactionId;
    }

    public UserRecord getSender() {
        return sender;
    }

    public UserRecord getRecipient() {
        return recipient;
    }

    public float getAmount() {
        return amount;
    }

}
