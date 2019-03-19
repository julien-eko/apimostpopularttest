package com.openclassrooms.netapp.Controllers.Utils;

import com.openclassrooms.netapp.Controllers.Models.MostPopular.MostPopular;
import com.openclassrooms.netapp.Controllers.Models.TopStories.TopStories;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Service {

    @GET("svc/topstories/v2/food.json?api-key=JyNdblyOMqqNNknG0iu5INNajfu1wAyj")
    Call<TopStories> getTopStories();

    @GET("svc/mostpopular/v2/viewed/1.json?api-key=JyNdblyOMqqNNknG0iu5INNajfu1wAyj")
    Call<MostPopular> getMostPopular();


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
