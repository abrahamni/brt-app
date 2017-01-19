package mobile.tracker.bribe.com.bribetracker.network;


import android.app.ProgressDialog;
import android.content.Context;

import mobile.tracker.bribe.com.bribetracker.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by giorgi on 4/14/2016.
 */
public class RetrofitSingletone {
//    public static final String KEY_TOKEN = "KEY_TOKEN";
//    private static final String HEADER_KEY = "Token";
    public static final String BASE_URL = "http://baseurl/api/";
    private static RetrofitSingletone ourInstance = null;
    private static final String TAG = "RetrofitSingletone";
//    public static final String TOKEN_KEY = "TOKEN_KEY";


    public static final String LANGUAGE = "en";
    public static final int FORM_ID = 1;


    private RetrofitApi api;

    private RetrofitSingletone() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        Interceptor headerInterceptor = new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Log.e(TAG, "intercept: " + BribeTrackerApplication.getInstance().getSavedString(TOKEN_KEY, ""));
//                String token = BribeTrackerApplication.getInstance().getSavedString(TOKEN_KEY, "");
//                Request request = chain.request().newBuilder().addHeader(HEADER_KEY, token).build();
//                return chain.proceed(request);
//            }
//        };

//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(RetrofitApi.class);
    }

    public static RetrofitSingletone getInstance() {
        if (ourInstance == null) {
            ourInstance = new RetrofitSingletone();
        }
        return ourInstance;
    }

//
//    public <T extends GeneralResponse> void sendRequest(final Context context, Call<T> call, final Callback<T> callback) {
//
//        call.enqueue(new Callback<T>() {
//            @Override
//            public void onResponse(Call<T> call, Response<T> response) {
////                ================ token expired pull out
//                if (response.body() != null && response.body().getCode() == MyConstants.TOKEN_CAPUT) {
//                    AlertDialog.Builder popupBuilder = new AlertDialog.Builder(context);
//                    popupBuilder.setMessage(MyConstants.SESSION_EXPIRED);
//                    popupBuilder.show();
//
//                    Connections.clearDatabase();
//                    FisherApplication.getInstance().clearPrefs();
//                    Intent intent = new Intent(context, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    context.startActivity(intent);
//                    return;
//                }
//
//                if (response.body() != null && response.body().getData() != null) {
//                    //FisherApplication.getInstance().saveSharedString(KEY_TOKEN, response.body().getToken());
//                    callback.onResponse(call, response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<T> call, Throwable t) {
//                callback.onFailure(call, t);
//            }
//        });
//    }
//
//    //=======================================
//
    public <T> void sendRequestWithLoader(final Context context, Call<T> call, final Callback<T> callback) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.loading));
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                progressDialog.dismiss();

                if (response.body() != null) {
                    callback.onResponse(call, response);
                }else {
                    callback.onFailure(call, null);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                progressDialog.dismiss();
                callback.onFailure(call, t);
            }
        });
    }

    public RetrofitApi getRetrofitApi() {
        return this.api;
    }
}
