package com.openclassrooms.netapp.Controllers.Utils;

import android.support.annotation.Nullable;

import com.openclassrooms.netapp.Controllers.Models.MostPopular.MostPopular;
import com.openclassrooms.netapp.Controllers.Models.TopStories.TopStories;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calls {

    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable MostPopular article);
        void onFailure();
    }

    // 2 - Public method to start fetching users following by Jake Wharton
    public static void fetchMostPopular(Callbacks callbacks){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        Service service = Service.retrofit.create(Service.class);

        // 2.3 - Create the call on Github API
        Call<MostPopular> call = service.getMostPopular();
        // 2.4 - Start the call
        call.enqueue(new Callback<MostPopular>() {

            @Override
            public void onResponse(Call<MostPopular> call, Response<MostPopular> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<MostPopular> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
