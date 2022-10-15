package com.example.moneycare.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UserItem {
    private String userId;
    private String userEmail;
    private int availBalance;
    private int totalEarnings;
    private List<BasicUserEntity> userTeam;
    private Map<String,BankAccount> userBankAccount;

    public UserItem(String userId, String userEmail, int availBalance, int totalEarnings, List<BasicUserEntity> userTeam, Map<String,BankAccount> userBankAccount) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.availBalance = availBalance;
        this.totalEarnings = totalEarnings;
        this.userTeam = userTeam;
        this.userBankAccount = userBankAccount;
    }

    public UserItem() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(int availBalance) {
        this.availBalance = availBalance;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public List<BasicUserEntity> getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(List<BasicUserEntity> userTeam) {
        this.userTeam = userTeam;
    }

    public Map<String, BankAccount> getUserBankAccount() {
        return userBankAccount;
    }

    public void setUserBankAccount(Map<String, BankAccount> userBankAccount) {
        this.userBankAccount = userBankAccount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(Objects.toString(userId, "")).append('\"');
        sb.append(",\"userEmail\":\"")
                .append(Objects.toString(userEmail, "")).append('\"');
        sb.append(",\"availBalance\":")
                .append(availBalance);
        sb.append(",\"totalEarnings\":")
                .append(totalEarnings);
        sb.append(",\"userTeam\":");
        if ((userTeam) != null && !(userTeam).isEmpty()) {
            sb.append("[");
            final int listSize = (userTeam).size();
            for (int i = 0; i < listSize; i++) {
                final Object listValue = (userTeam).get(i);
                if (listValue instanceof CharSequence) {
                    sb.append("\"").append(Objects.toString(listValue, "")).append("\"");
                } else {
                    sb.append(Objects.toString(listValue, ""));
                }
                if (i < listSize - 1) {
                    sb.append(",");
                } else {
                    sb.append("]");
                }
            }
        } else {
            sb.append("[]");
        }
        sb.append(",\"userBankAccount\":");
        if ((userBankAccount) != null && !(userBankAccount).isEmpty()) {
            sb.append("{");
            final Set<?> mapKeySet = (userBankAccount).keySet();
            for (Object mapKey : mapKeySet) {
                final Object mapValue = (userBankAccount).get(mapKey);
                sb.append("\"").append(mapKey).append("\":\"").append(Objects.toString(mapValue, "")).append("\",");
            }
            sb.replace(sb.length() - 1, sb.length(), "}");
        } else {
            sb.append("{}");
        }
        sb.append('}');
        return sb.toString();
    }
}
