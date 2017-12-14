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
   
   public void initialize (){
       currencyDB.storeCurrencyInfo(new Currency("SEK", 1 ));
       currencyDB.storeCurrencyInfo(new Currency("EUR", 9.54));
       currencyDB.storeCurrencyInfo(new Currency("USD", 8.56 ));
       currencyDB.storeCurrencyInfo(new Currency("GBP", 11.27 ));  
   }
   
   public Double convert (String fromCurrencyName , String toCurrencyName , double amount){
       Currency current;
       
       current = currencyDB.findCurrencyByName(fromCurrencyName);
       double fromCurrencyValue = current.getRateValue();
       
       current = currencyDB.findCurrencyByName(toCurrencyName);
       double toCurrencyValue = current.getRateValue();
      
       return (amount * (fromCurrencyValue/toCurrencyValue));

   }
}
