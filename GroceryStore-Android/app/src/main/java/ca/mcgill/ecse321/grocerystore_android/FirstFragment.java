package ca.mcgill.ecse321.grocerystore_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentFirstBinding;
import cz.msebera.android.httpclient.Header;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private String error = null;
    private String accountType = "Customer";


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreateSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton toggle = (ToggleButton) getActivity().findViewById(R.id.customer_button);
                final Boolean isChecked = toggle.isChecked();

                if (isChecked) MainActivity.accountType= "Customer";
                else MainActivity.accountType="Employee";

                error = "";
                final TextView tv1 = (TextView) getActivity().findViewById(R.id.login_username);
                final TextView tv2 = (TextView) getActivity().findViewById(R.id.login_password);


                if (MainActivity.accountType.equals("Customer")) {
                    HttpUtils.get("customer_login?username=" + tv1.getText().toString()+"&password="+ tv2.getText().toString()+"&email=", new RequestParams(), new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        refreshErrorMessage();
                            System.out.println("customer login success");
                            MainActivity.username=tv1.getText().toString();
                            MainActivity.accountType="Customer";
                            try {
                                MainActivity.address=response.getString("address");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tv1.setText("");
                            tv2.setText("");


                            NavHostFragment.findNavController(FirstFragment.this)
                                    .navigate(R.id.action_FirstFragment_to_thirdFragment);
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                            System.out.println("customer login failure");
                            try {
                                error += errorResponse;
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            System.out.println("ERROR");
                            refreshErrorMessage();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            System.out.println("customer login failure");
                            try {
                                error += errorResponse;
                            } catch (Exception e) {
                                error += e.getMessage();
                            }
                            refreshErrorMessage();
                        }
                    });
                }
                else{
                    HttpUtils.get("employee_login?username=" + tv1.getText().toString()+"&password="+ tv2.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        refreshErrorMessage();
                            System.out.println("Employee login success");
                            MainActivity.username=tv1.getText().toString();
                            MainActivity.accountType="Employee";
                            try {
                                MainActivity.address=response.getString("address");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tv1.setText("");
                            tv2.setText("");

                            NavHostFragment.findNavController(FirstFragment.this)
                                    .navigate(R.id.action_FirstFragment_to_thirdFragment);
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers,String errorResponse, Throwable throwable) {
                            System.out.println("employee login failure");
                            try {
                                error += errorResponse;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}