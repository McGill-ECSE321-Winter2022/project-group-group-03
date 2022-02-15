package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeliveryOrder extends Order{
    @Id
    public static final int SHIPPINGFEE = 30;
    private boolean inTown;
    public enum ShippingStatus { InCart, Ordered, Prepared, Delivered }
    private ShippingStatus shippingStatus;

    public DeliveryOrder(int aTotalCost, GroceryStoreSystem aGroceryStoreSystem, boolean aInTown) {
        super(aTotalCost, aGroceryStoreSystem);
        inTown = aInTown;
        setShippingStatus(ShippingStatus.InCart);
    }
    public DeliveryOrder(){}

    public boolean getInTown()
    {
        return inTown;
    }
    public void setInTown(boolean aInTown) {
        inTown = aInTown;
    }
    public ShippingStatus getShippingStatus()
    {
        return shippingStatus;
    }
    private void setShippingStatus(ShippingStatus aShippingStatus)
    {
        shippingStatus = aShippingStatus;
    }

    public boolean order()
    {
        boolean wasEventProcessed = false;

        ShippingStatus aShippingStatus = shippingStatus;
        // Other states do respond to this event
        if (aShippingStatus == ShippingStatus.InCart) {
            setShippingStatus(ShippingStatus.Ordered);
            wasEventProcessed = true;
        }

        return wasEventProcessed;
    }

    public boolean pay() {
        boolean wasEventProcessed = false;

        ShippingStatus aShippingStatus = shippingStatus;
        // Other states do respond to this event
        if (aShippingStatus == ShippingStatus.Ordered) {
            setShippingStatus(ShippingStatus.Prepared);
            wasEventProcessed = true;
        }

        return wasEventProcessed;
    }

    public boolean deliver() {
        boolean wasEventProcessed = false;

        ShippingStatus aShippingStatus = shippingStatus;
        // Other states do respond to this event
        if (aShippingStatus == ShippingStatus.Prepared) {
            setShippingStatus(ShippingStatus.Delivered);
            wasEventProcessed = true;
        }

        return wasEventProcessed;
    }

    public String toString()
    {
        return super.toString() + "["+
                "inTown" + ":" + getInTown()+ "]";
    }
}
