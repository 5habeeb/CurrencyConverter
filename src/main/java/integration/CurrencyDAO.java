/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import model.Currency;

/**
 *
 * @author Ahmad
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class CurrencyDAO {
    @PersistenceContext(unitName = "currencyPU")
    private EntityManager em;
    
    public Currency findCurrencyByName(String currencyName) {
        Currency currency = em.find(Currency.class, currencyName);
        if (currency == null) {
            throw new EntityNotFoundException("No currency with name " + currency);
        }
        return currency;
    }
    
    public void storeCurrencyInfo(Currency currency) {
        em.persist(currency);
    }
}
