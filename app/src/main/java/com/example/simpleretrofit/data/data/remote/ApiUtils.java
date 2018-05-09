package com.example.simpleretrofit.data.data.remote;

import com.example.simpleretrofit.data.data.remote.RetrofitClient;
import com.example.simpleretrofit.data.data.remote.SOService;

public class ApiUtils {
    public static final String BASE_URL = "https://apistackexchange.com/2.2/";

    public static SOService getSOService(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
