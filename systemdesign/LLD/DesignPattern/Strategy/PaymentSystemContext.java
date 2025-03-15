package systemdesign.LLD.DesignPattern.Strategy;

public class PaymentSystemContext {
    PaymentStrategy strategy;
    public PaymentSystemContext(PaymentStrategy strategy){
        this.strategy = strategy;
    }
    public String completePayment(float amount){
        int x = strategy.pay(amount);
        if( x > 0){
            return "Payment Done";
        }
        return "Payment Failed";
    }
}
