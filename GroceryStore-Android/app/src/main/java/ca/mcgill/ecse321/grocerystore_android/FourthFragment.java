package ca.mcgill.ecse321.grocerystore_android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentFourthBinding;
import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentThirdBinding;
import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourthFragment} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;
    private String error;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPurchasedItems();
        ToggleButton button = getActivity().findViewById(R.id.order_button);
        if (!MainActivity.orderType.equals("Pickup")) button.setChecked(false);
        else button.setChecked(true);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("checking if pickup");
                System.out.println("orderType = "+ MainActivity.orderType+"   !button.isChecked = "+!button.isChecked());
                if (MainActivity.orderType.equals("Pickup") && !button.isChecked()){
                    HttpUtils.put("convertToDelivery?username="+MainActivity.username+"&shippingAddress="+MainActivity.address+"&accountType="+MainActivity.accountType+"&isOutOfTown=false", new RequestParams(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            MainActivity.orderType="Delivery";
                            System.out.println("Successfully transformed to Delivery Order");
                            getPurchasedItems();
                        }
                        @Override
                        public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable){
                            System.out.println("error convert to delivery1" );
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                        @Override
                        public void onFailure( int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                            System.out.println("error convert to delivery2" );
                            System.out.println(errorResponse);
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                    });
                }
                System.out.println("checking if Delivery");
                System.out.println("orderType = "+MainActivity.orderType+"  and button.isChecked "+button.isChecked());
                if (MainActivity.orderType.equals("Delivery") && button.isChecked()){
                    HttpUtils.put("convertToPickup?username="+MainActivity.username+"&shippingAddress="+MainActivity.address+"&paymentMethod=Cash&accountType="+MainActivity.accountType, new RequestParams(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            MainActivity.orderType="Pickup";
                            System.out.println("Successfully transformed to Pickup Order");
                            getPurchasedItems();
                        }
                        @Override
                        public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable){
                            System.out.println("error convert to pickup1" );
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                            System.out.println("error convert to pickup2" );
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                    });
                }
            }
        });

        Button checkout = getActivity().findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MainActivity.orderType.equals("Pickup")){
                    HttpUtils.put("editPickupOrderStatus/"+MainActivity.confirmationNumber+"/?newPickupStatus=Ordered", new RequestParams(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            System.out.println("pickup checkout success");
                            NavHostFragment.findNavController(FourthFragment.this)
                                    .navigate(R.id.action_fourthFragment_to_FirstFragment);
                        }
                        @Override
                        public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                            System.out.println("pickup checkout error 1");

                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                            System.out.println("pickup checkout error 2");
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                    });
                }
                else if (MainActivity.orderType.equals("Delivery")){
                    HttpUtils.put("/editDeliveryOrderShippingStatus/"+MainActivity.confirmationNumber+ "/?newShippingStatus=Ordered",new RequestParams(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            System.out.println("delivery checkout successful");
                            NavHostFragment.findNavController(FourthFragment.this)
                                    .navigate(R.id.action_fourthFragment_to_FirstFragment);
                        }
                        @Override
                        public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                            System.out.println("delivery checkout error 1");
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                            System.out.println("delivery checkout error 2");
                            try {
                                error += errorResponse.toString();
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                    });
                }
            }
        });
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) getActivity().findViewById(R.id.error1);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    private void getPurchasedItems(){

        LinearLayout cardLayout = getActivity().findViewById(R.id.cart_layout);
        cardLayout.removeAllViews();

        HttpUtils.get(MainActivity.accountType.toLowerCase(Locale.ROOT)+"_order/"+MainActivity.username, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("account had an order");
                JSONArray purchasedItems = new JSONArray();

                try {
                    TextView priceText = getActivity().findViewById(R.id.priceView);
                    priceText.setText(response.getString("totalCost")+" $");
                    MainActivity.confirmationNumber = response.getInt("confirmationNumber");
                    purchasedItems = response.getJSONArray("purchasedItem");
                    System.out.println(response);
                    for (int i = 0; i < purchasedItems.length(); i++) {

                        JSONObject purchasedItem = purchasedItems.getJSONObject(i);
                        JSONObject item = purchasedItem.getJSONObject("item");
                        String name = item.getString("name");
                        String description = item.getString("description");
                        String price = item.getString("price");
                        int purchasedItemID = purchasedItem.getInt("purchasedItemID");
                        int quantity = purchasedItem.getInt("itemQuantity");

                        CardView itemCard = new CardView(getActivity());
                        itemCard.setCardElevation(10*getActivity().getResources().getDisplayMetrics().density);

                        //makes all items in card linear
                        LinearLayout innerLayout = new LinearLayout(getActivity());
                        //lets me line up three text boxes vertically
                        LinearLayout leftLayout = new LinearLayout(getActivity());
                        leftLayout.setOrientation(LinearLayout.VERTICAL);
                        //lets me setup buttons and counter horizontally
                        LinearLayout rightLayout = new LinearLayout(getActivity());
                        rightLayout.setOrientation(LinearLayout.HORIZONTAL);

                        TextView itemName = new TextView(getActivity());
                        TextView itemDescription = new TextView(getActivity());
                        TextView itemPrice = new TextView(getActivity());

                        Button minusButton = new Button(getActivity());
                        TextView purchasedQuantity = new TextView(getActivity());
                        Button plusButton = new Button(getActivity());
                        Button remove = new Button(getActivity());

                        LinearLayout.LayoutParams matchWrap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        matchWrap.setMargins(0,10,0,10);
                        LinearLayout.LayoutParams wrapWrap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        LinearLayout.LayoutParams matchMatch = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        float pixels =  147 * getActivity().getResources().getDisplayMetrics().density;
                        LinearLayout.LayoutParams custom1 = new LinearLayout.LayoutParams((int) pixels, ViewGroup.LayoutParams.MATCH_PARENT);
                        pixels =  48 * getActivity().getResources().getDisplayMetrics().density;
                        LinearLayout.LayoutParams custom2 = new LinearLayout.LayoutParams((int) pixels, ViewGroup.LayoutParams.MATCH_PARENT);
                        pixels =  90 * getActivity().getResources().getDisplayMetrics().density;
                        LinearLayout.LayoutParams custom3 = new LinearLayout.LayoutParams((int) pixels, ViewGroup.LayoutParams.MATCH_PARENT);


                        itemCard.setLayoutParams(matchWrap);
                        leftLayout.setLayoutParams(custom1);
                        rightLayout.setLayoutParams(matchMatch);

                        itemName.setLayoutParams(wrapWrap);
                        itemName.setText(name);
                        itemDescription.setLayoutParams(wrapWrap);
                        itemDescription.setText(description);
                        itemPrice.setLayoutParams(wrapWrap);
                        itemPrice.setText(price+" $");

                        minusButton.setLayoutParams(custom2);
                        minusButton.setText("-");
                        purchasedQuantity.setLayoutParams(wrapWrap);
                        purchasedQuantity.setText(""+quantity);
                        plusButton.setLayoutParams(custom2);
                        plusButton.setText("+");
                        remove.setLayoutParams(custom3);
                        remove.setText("Remove");

                        cardLayout.addView(itemCard);
                        itemCard.addView(innerLayout);
                        innerLayout.addView(leftLayout);
                        innerLayout.addView(rightLayout);

                        leftLayout.addView(itemName);
                        leftLayout.addView(itemDescription);
                        leftLayout.addView(itemPrice);

                        rightLayout.addView(minusButton);
                        rightLayout.addView(purchasedQuantity);
                        rightLayout.addView(plusButton);
                        rightLayout.addView(remove);

                        minusButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int newCount = quantity -1;
                                HttpUtils.put("edit_purchasedItem/"+purchasedItemID+"?itemQuantity="+newCount,new RequestParams(), new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        System.out.println("reduced purchased item quantity");
                                        getPurchasedItems();
                                    }
                                    @Override
                                    public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable){
                                        try {
                                            error += errorResponse.toString();
                                        } catch (Exception e) {
                                            error += e.getMessage();
                                        }
                                        refreshErrorMessage();
                                    }
                                });
                            }
                        });
                        plusButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int newCount = quantity + 1;
                                HttpUtils.put("edit_purchasedItem/"+purchasedItemID+"?itemQuantity="+newCount,new RequestParams(), new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        System.out.println("increased purchased item quantity");
                                        getPurchasedItems();
                                    }
                                    @Override
                                    public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable){
                                        try {
                                            error += errorResponse.toString();
                                        } catch (Exception e) {
                                            error += e.getMessage();
                                        }
                                        refreshErrorMessage();
                                    }
                                });
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                HttpUtils.delete("purchased_item/"+purchasedItemID,new RequestParams(), new JsonHttpResponseHandler(){

                                    @Override
                                    public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable){
                                        getPurchasedItems();
                                    }
                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                                        System.out.println("error with remove item");
                                        try {
                                            error += errorResponse.toString();
                                        } catch (Exception e) {
                                            error += e.getMessage();
                                        }
                                        refreshErrorMessage();
                                    }
                                });
                            }
                        });

                    }

                } catch (Exception e) {
                    System.out.println("there was an error in this thing");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure( int statusCode, Header[] headers, String errorResponse, Throwable throwable){
                try {
                    error += errorResponse.toString();
                } catch (Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}