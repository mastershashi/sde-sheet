package systemdesign.LLD.DesignPattern.Strategy;

public class UPIPaymentStrategy implements PaymentStrategy{

    @Override
    public int pay(float amount) {
        if( amount >0){
            return 1;
        }
        return 0; 
    }
    
}
