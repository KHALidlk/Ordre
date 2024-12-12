package com.Application.DAO;

import java.math.BigDecimal;
import java.util.Date;

public interface OrdreDAO {
    public void ajouter_ordr(Date date, BigDecimal amount, int customerId);
}
