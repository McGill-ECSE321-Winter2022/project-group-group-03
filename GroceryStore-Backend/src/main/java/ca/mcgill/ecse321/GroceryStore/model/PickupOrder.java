package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;

@Entity
public class PickupOrder extends Order{
    public enum PaymentMethod { CreditCard, Cash }
    public enum PickupStatus { InCart, Ordered, Prepared, PickedUp }
    private PickupStatus pickupStatus;
    public PickupOrder(){}
    public PickupOrder(int aTotalCost, GroceryStoreSystem aGroceryStoreSystem) {
        super(aTotalCost, aGroceryStoreSystem);
        setPickupStatus(PickupStatus.InCart);
    }
    public String getPickupStatusFullName(){
        return pickupStatus.toString();
    }

    public PickupStatus getPickupStatus()
    {
        return pickupStatus;
    }

    public boolean order()
    {
        boolean wasEventProcessed = false;

        PickupStatus aPickupStatus = pickupStatus;
        switch (aPickupStatus)
        {
            case InCart:
                setPickupStatus(PickupStatus.Ordered);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean prepare()
    {
        boolean wasEventProcessed = false;

        PickupStatus aPickupStatus = pickupStatus;
        switch (aPickupStatus)
        {
            case Ordered:
                setPickupStatus(PickupStatus.Prepared);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean pay()
    {
        boolean wasEventProcessed = false;

        PickupStatus aPickupStatus = pickupStatus;
        switch (aPickupStatus)
        {
            case Prepared:
                setPickupStatus(PickupStatus.PickedUp);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    private void setPickupStatus(PickupStatus aPickupStatus)
    {
        pickupStatus = aPickupStatus;
    }


}
