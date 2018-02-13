package com.devbruno.fastshop.infraestruture;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.net.Uri;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bsilvabr on 11/02/2018.
 */

public class AccountUtils {

    public static String getGoogleUsername(Activity activity) {
        AccountManager manager = AccountManager.get(activity);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            possibleEmails.add(account.name);
        }

        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");
            if (parts.length > 0 && parts[0] != null)
                return parts[0];
            else
                return "Guest";
        } else
            return "Guest";
    }
}
