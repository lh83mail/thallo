package org.halo.thallo.authenserver.model;

import java.util.List;

public interface Subject {
    String getUid();
    List<Account> getAccounts();
}
