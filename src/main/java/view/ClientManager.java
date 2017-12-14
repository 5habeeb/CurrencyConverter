/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import controller.ConverterFacade;
import model.Currency;

/**
 *
 * @author Ahmad
 */
@Named("clientManager")
@ConversationScoped
public class ClientManager implements Serializable {
    @EJB
    private ConverterFacade converterFacade;
    private String currencyFrom;
    private String currencyTo;
    private Double sentAmount;
    private Double receivedAmount;
    private Exception conversionFailure;
    @Inject
    private Conversation conversation;
    
        private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        conversionFailure = e;
    }
     
    public void convert() {
          try {
            startConversation();
            conversionFailure = null;
            this.receivedAmount = converterFacade.convert(this.currencyFrom, this.currencyTo , this.sentAmount);
        } catch (Exception e) {
            handleException(e);
        }
    }
    
    public void setCurrencyFrom (String currency){
        this.currencyFrom = currency;
    }
    
    public String getCurrencyFrom (){
        return this.currencyFrom;
    }
    
    public void setCurrencyTo (String currency){
        this.currencyTo = currency;
    }
    
    public String getCurrencyTo (){
        return this.currencyTo;
    }
    
    public void setSentAmount (Double Amount){
        this.sentAmount = Amount;
    }
    
    public Double getSentAmount (){
        return this.sentAmount;
    }
    
    public void setReceivedAmount (Double Amount){
        this.receivedAmount = Amount;
    }
    
    public Double getReceivedAmount (){
        return this.receivedAmount;
    }
}
