package ca.mcgill.ecse321.grocerystore_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentSecondBinding;
import cz.msebera.android.httpclient.Header;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private String error;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //add an action listener to the create account button
        //if successfully creates account, send the user back to the login page
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error = "";
                final TextView tv1 = (TextView) getActivity().findViewById(R.id.new_customer_username);
                final TextView tv2 = (TextView) getActivity().findViewById(R.id.new_customer_password);
                final TextView tv3 = (TextView) getActivity().findViewById(R.id.new_customer_email);
                final TextView tv4 = (TextView) getActivity().findViewById(R.id.new_customer_address);
                HttpUtils.post("customer?username=" + tv1.getText().toString()+"&password="+ tv2.getText().toString()+"&email="+tv3.getText().toString()+"&address="+tv4.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        refreshErrorMessage();
                        System.out.println("success");
                        tv1.setText("");
                        tv2.setText("");
                        tv3.setText("");
                        tv4.setText("");
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FirstFragment);
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers,String errorResponse, Throwable throwable) {
                        System.out.println("failure");
                        try {
                            error += errorResponse;
                        } catch (Exception e) {
                            error += e.getMessage();
                        }
                        System.out.println("ERROR");
                        refreshErrorMessage();
                    }
                });
            }
        });
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) getActivity().findViewById(R.id.error2);
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