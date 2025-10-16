package com.jpmc.midascore;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.prefs.PreferenceChangeEvent;

@Component
public class Listener
{

    @Autowired
    private UserRepository users;

    @Autowired
    private TransactionRepository transactions;

    @KafkaListener(topics = "${general.kafka-topic}")
    public void process(Transaction transaction) {
        System.out.println(transaction);

        // finding/getting the users and the transaction amount from the transaction
        UserRecord sender = users.findById(transaction.getSenderId());
        UserRecord recipient = users.findById(transaction.getRecipientId());
        float amount = transaction.getAmount();

        // early returning if the sender does not have enough balance (transaction invalid)
        if (sender.getBalance() < amount)
            return;

        System.out.println("sender: " + sender);
        System.out.println("recipient: " + recipient);
        System.out.println("amount sent : " + amount + "\n");

        // if transaction IS valid then moving the amount from the sender's to recipient's balance
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        // save the two users in the repo
        users.save(sender);
        users.save(recipient);

        // UserRecord user = new UserRecord(userData[0], Float.parseFloat(userData[1]));

        TransactionRecord transactionRecord = new TransactionRecord(sender, recipient, amount);
        transactions.save(transactionRecord);
    }
}
