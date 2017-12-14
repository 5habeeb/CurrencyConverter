/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ahmad
 */
@Entity
public class Currency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String currencyName;
    private double rateValue;

  
    public Currency() {
    }
  
    public Currency (String currencyName, double rateValue) {
        this.currencyName = currencyName;
        this.rateValue = rateValue;
    }
    
    public String getCurrencyName (){
        return this.currencyName;
    }
    
    public void setCurrencyName (String currencyName){
        this.currencyName = currencyName;
    }
    
    public double getRateValue (){
        return this.rateValue;
    }
    
    public void setRateValue (Double rateValue){
        this.rateValue = rateValue;
    }
    
        @Override
    public int hashCode() {
        int hash = 0;
        return new Integer(currencyName).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        return this.currencyName == other.currencyName;
    }

    @Override
    public String toString() {
        return "converter.model.Currency[name=" + currencyName + "]";
    }
}
