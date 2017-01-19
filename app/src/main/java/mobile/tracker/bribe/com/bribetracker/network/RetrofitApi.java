package mobile.tracker.bribe.com.bribetracker.network;

import mobile.tracker.bribe.com.bribetracker.models.api.ApiResponseModel;
import mobile.tracker.bribe.com.bribetracker.models.api.responses.InfoResultModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by giorgi on 4/14/2016.
 */
public interface RetrofitApi {

    @POST("/api/form/response")
    Call<ApiResponseModel> sendAnswer(@Body JsonQuestionResponseModel response);
//
    @GET("/api/form/responses")
    Call<InfoResultModel> getResults(@Query("lang") String lang, @Query("form_id") int formId);


}

