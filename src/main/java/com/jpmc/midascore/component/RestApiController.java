package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController
{
    @Autowired
    private UserRepository users;

    @GetMapping("/balance")
    public Balance getBalanceById(@RequestParam long userId)
    {
        // first check if the user exists to output 0 if it doesn't
        UserRecord user = users.findById(userId);
        if (user == null) return new Balance(0f);

        // otherwise output the balance
        Balance userBalance = new Balance(user.getBalance());

        return userBalance;
    }
}
