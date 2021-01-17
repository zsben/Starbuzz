package bjfu.cs.zhouenjie.utils;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.Bean.Count;
import bjfu.cs.zhouenjie.Bean.User;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

interface RetrofitApi {

    @GET
    Observable<Response<Coffee>> getGoods();

    @GET
    Observable<Response<User>> load();

    @GET
    Observable<Response<Count>> findCountsByUser();

}
