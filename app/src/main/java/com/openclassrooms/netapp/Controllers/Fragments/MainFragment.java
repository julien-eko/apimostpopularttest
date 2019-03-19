package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.netapp.Controllers.Models.MostPopular.MostPopular;
import com.openclassrooms.netapp.Controllers.Models.MostPopular.ResultMostPopular;
import com.openclassrooms.netapp.Controllers.Utils.Calls;
import com.openclassrooms.netapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Calls.Callbacks{

    // FOR DESIGN
    @BindView(R.id.fragment_main_textview) TextView textView;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // -----------------
    // ACTIONS
    // -----------------

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {
        this.executeHttpRequestWithRetrofit();
    }
    // ------------------------------
    //  HTTP REQUEST (Retrofit Way)
    // ------------------------------

    // 4 - Execute HTTP request and update UI
    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
        Calls.fetchMostPopular(this);
    }

    // 2 - Override callback methods

    @Override
    public void onResponse(@Nullable MostPopular article) {
        // 2.1 - When getting response, we update UI
         this.updateUIWithListOfUsers(article);
    }

    @Override
    public void onFailure() {
        // 2.2 - When getting error, we update UI

        this.updateUIWhenStopingHTTPRequest("An error happened !");
    }




    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }

    // 3 - Update UI showing only name of users
    private void updateUIWithListOfUsers(MostPopular article){
        StringBuilder stringBuilder = new StringBuilder();
        for (ResultMostPopular user : article.getResults()){
            stringBuilder.append("-"+user.getTitle()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }
}
