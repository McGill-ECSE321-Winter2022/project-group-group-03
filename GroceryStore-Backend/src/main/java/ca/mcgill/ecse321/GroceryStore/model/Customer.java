package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;

@Entity
public class Customer extends NamedUser{
    public Customer(String aUsername, String aPassword, String aEmail, GroceryStoreSystem aGroceryStoreSystem, String aName, String aAddress)
    {
        super(aUsername, aPassword, aEmail, aGroceryStoreSystem, aName, aAddress);
    }
    public Customer(){}

}
