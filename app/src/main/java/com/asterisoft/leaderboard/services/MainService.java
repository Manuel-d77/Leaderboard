package com.asterisoft.leaderboard.services;

import com.asterisoft.leaderboard.LearnerInfo;
import com.asterisoft.leaderboard.SkillIqInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MainService {
    @GET("/api/{hours}")
    Call<List<LearnerInfo>> getLeadingLearners(@Path("hours") String learners);

    @GET("/api/{skilliq}")
    Call<List<SkillIqInfo>> getSkillIqLeaders(@Path("skilliq") String skilliq);

    @POST
    @FormUrlEncoded
    Call<Void> submitForm(
            @Url String url,
            @Field("entry.1824927963") String email,
            @Field("entry.1877115667") String name,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String gitLink
    );
}
