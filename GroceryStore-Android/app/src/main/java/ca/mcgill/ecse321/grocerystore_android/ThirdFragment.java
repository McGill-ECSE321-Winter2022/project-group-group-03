package ca.mcgill.ecse321.grocerystore_android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentSecondBinding;
import ca.mcgill.ecse321.grocerystore_android.databinding.FragmentThirdBinding;
import cz.msebera.android.httpclient.Header;

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

        LinearLayout itemLayout = (LinearLayout) getActivity().findViewById(R.id.ItemsLayout);
        HttpUtils.get("item/", new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonobject = response.getJSONObject(i);
                        String name = jsonobject.getString("name");
                        boolean purchasable = jsonobject.getBoolean("purchasable");
                        int price = jsonobject.getInt("price");
                        String description = jsonobject.getString("description");
                        int stock = jsonobject.getInt("stock");
                        String url = jsonobject.getString("image");
                        System.out.println(name+"\n"+purchasable+"\n"+price+"\n"+description+"\n"+stock+"\n"+url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers,String errorResponse, Throwable throwable){
                System.out.println(errorResponse);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}