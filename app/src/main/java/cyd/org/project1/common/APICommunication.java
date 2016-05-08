package cyd.org.project1.common;

import cyd.org.project1.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cars on 5/7/16.
 */
public class APICommunication {
  private static APICommunication instance;
  private static APIDefinition service;


  public static APIDefinition getAPICommunication() {
    if (instance == null) {
      instance = new APICommunication();
    }
    return service;
  }

  private APICommunication() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BuildConfig.URL_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    service = retrofit.create(APIDefinition.class);
  }

}
