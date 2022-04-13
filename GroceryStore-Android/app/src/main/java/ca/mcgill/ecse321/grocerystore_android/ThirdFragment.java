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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentThirdBinding;
import cz.msebera.android.httpclient.Header;


/**
 * @author Sebastien Cantin
 */

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    private String error;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout cardLayout = getActivity().findViewById(R.id.card_layout);
        getOrder();

        //gets all the items and creates cards that add each of them to the page in any order
        HttpUtils.get("item/", new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonobject = response.getJSONObject(i);
                        String name = jsonobject.getString("name");
                        String priceOfItem = jsonobject.getString("price");
                        String description = jsonobject.getString("description");
                        int stock = jsonobject.getInt("stock");


                        //Creating the cards
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
                        Button addToCart = new Button(getActivity());

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
                        itemPrice.setText(priceOfItem+" $");

                        minusButton.setLayoutParams(custom2);
                        minusButton.setText("-");
                        purchasedQuantity.setLayoutParams(wrapWrap);
                        purchasedQuantity.setText("1");
                        plusButton.setLayoutParams(custom2);
                        plusButton.setText("+");
                        addToCart.setLayoutParams(custom3);
                        addToCart.setText("Add to Cart");

                        //create action listeners for the generated buttons
                        minusButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence num = purchasedQuantity.getText();
                                int counter = Integer.parseInt(num.toString());
                                if (counter - 1 >0 ) counter--;
                                purchasedQuantity.setText(""+counter);
                            }
                        });

                        plusButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence num = purchasedQuantity.getText();
                                int counter = Integer.parseInt(num.toString());
                                if (counter + 1 <= stock ) counter++;
                                purchasedQuantity.setText(""+counter);
                            }
                        });

                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence num = purchasedQuantity.getText();
                                int counter = Integer.parseInt(num.toString());
                                System.out.println("purchased_item?item="+name+"&aItemQuantity="+counter+"&confirmationNumber="+MainActivity.confirmationNumber+"&orderType="+MainActivity.orderType);
                                HttpUtils.post("purchased_item?item="+name+"&aItemQuantity="+counter+"&confirmationNumber="+MainActivity.confirmationNumber+"&orderType="+MainActivity.orderType,new RequestParams(), new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        System.out.println("item successfully added to cart");
                                        System.out.println(response);
                                    }
                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                                        try {
                                            error += errorResponse;
                                        } catch (Exception e) {
                                            error += e.getMessage();
                                        }
                                        refreshErrorMessage();
                                    }
                                });
                            }
                        });

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
                        rightLayout.addView(addToCart);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers,String errorResponse, Throwable throwable){
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });

        //adds and action listener for the go to checkout button
        binding.goToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_fourthFragment);
            }
        });

    }

    /**
     * gets the active order of the user that is logged in, fixing all the info
     * adding the order type and confirmation number to the "session Storage"
     *
     * If the user does not already have an order that is in cart, creates a new
     * pickup order
     */
    private void getOrder(){
        HttpUtils.get(MainActivity.accountType.toLowerCase(Locale.ROOT)+"_order/"+MainActivity.username, new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("account had an order");
                System.out.println(response);
                try {
                    MainActivity.orderType=response.getString("orderType");
                    MainActivity.confirmationNumber=response.getInt("confirmationNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                System.out.println("Account had no order");
                //creates new order if customer has no orders in the inCart state
                HttpUtils.post("pickupOrder?username="+MainActivity.username+"&paymentMethod=Cash&accountType="+MainActivity.accountType,new RequestParams(), new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println("Created new order");
                        MainActivity.orderType="Pickup";
                        try {
                            MainActivity.confirmationNumber = (int) response.get("confirmationNumber");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(response);
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        System.out.println("Failed to create new account");
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
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                System.out.println("Account had no order");
                //creates new order if customer has no orders in the inCart state
                HttpUtils.post("pickupOrder?username="+MainActivity.username+"&paymentMethod=Cash&accountType="+MainActivity.accountType,new RequestParams(), new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println("Created new order");
                        MainActivity.orderType="Pickup";
                        try {
                            MainActivity.confirmationNumber = (int) response.get("confirmationNumber");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(response);
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        System.out.println("Failed to create new account");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}