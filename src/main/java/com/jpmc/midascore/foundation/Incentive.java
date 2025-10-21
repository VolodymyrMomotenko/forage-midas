package com.jpmc.midascore.foundation;

public class Incentive
{
    private float amount;

    public Incentive() {
        System.out.println("created incentive");
    }

    public void setAmount(float amount) {
        this.amount = amount;
        System.out.println("set incentive : " + amount);
    }

    public float getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "Incentive: " + getAmount();
    }
}
