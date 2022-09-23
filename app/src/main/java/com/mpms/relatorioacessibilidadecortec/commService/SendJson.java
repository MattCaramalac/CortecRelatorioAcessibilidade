package com.mpms.relatorioacessibilidadecortec.commService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SendJson {

    //https://ptsv2.com/t/vfv31-1661803917#

    @POST("post")
    Call<SentData> PostData(@Body SentData sentData);
}
