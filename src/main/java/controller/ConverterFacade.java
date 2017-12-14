/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import integration.CurrencyDAO;
import model.Currency;

/**
 *
 * @author Ahmad
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ConverterFacade {
   @EJB CurrencyDAO currencyDB;
   
   private boolean init = true;
   
   public void initialize (){
       currencyDB.storeCurrencyInfo(new Currency("SEK", 1 ));
       currencyDB.storeCurrencyInfo(new Currency("EUR", 9.91323));
       currencyDB.storeCurrencyInfo(new Currency("USD", 8.42518 ));
       currencyDB.storeCurrencyInfo(new Currency("GBP", 11.2504 ));
       currencyDB.storeCurrencyInfo(new Currency("CAD", 6.55451 ));
   }
   
   public Double convert (String fromCurrencyName , String toCurrencyName , double amount){
         
        if(init){
            initialize();
            init = false;
        }
       
       Currency current;
       current = currencyDB.findCurrencyByName(fromCurrencyName);
       double fromCurrencyValue = current.getRateValue();
       current = currencyDB.findCurrencyByName(toCurrencyName);
       double toCurrencyValue = current.getRateValue();
      
       return (amount * (fromCurrencyValue/toCurrencyValue));

   }
}
