package np.cnblabs.accountdemo.webservice;

import np.cnblabs.accountdemo.model.ImdbModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sanjogstha on 11/23/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public interface WebService {
    @GET(UrlConstant.POPULAR_MOVIE)
    Call<ImdbModel> getMovieList();
}
