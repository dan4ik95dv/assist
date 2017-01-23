package ru.dvfu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.dvfu.assist.model.Auth;
import ru.dvfu.assist.model.AuthSuccess;
import ru.dvfu.assist.model.ThemeSuccess;

/**
 * Created by user on 23.01.2017.
 */
public interface AssistApi {
    @POST("/auth")
    Call<AuthSuccess> auth(@Body Auth auth);

    @GET("/themes")
    Call<ThemeSuccess> themes(@Query("token") String token);
}

