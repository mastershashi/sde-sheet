package systemdesign.LLD.DesignPattern.Strategy;

public class PaymentSystem {
    
    public static void main(String[] args) {
        PaymentSystemContext paymentSystemContext = new PaymentSystemContext(new UPIPaymentStrategy());
       System.out.println(paymentSystemContext.completePayment(100.50f));
        
    }
}
