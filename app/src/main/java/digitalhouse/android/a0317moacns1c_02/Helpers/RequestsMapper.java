package digitalhouse.android.a0317moacns1c_02.Helpers;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Requests.MovieSearchRequest;

/**
 * Created by Gregorio Martin on 27/5/2017.
 */

public class RequestsMapper {

    public static Map<String, String> map(MovieSearchRequest movieSearchRequest, Map<String, String> target){
        if(movieSearchRequest.getLenguage() != null)
        target.put("lenguage", movieSearchRequest.getLenguage());

        target.put("query", movieSearchRequest.getQuery());

        if(movieSearchRequest.getPage() != null)
        target.put("page", movieSearchRequest.getPage().toString());

        if(movieSearchRequest.getInclude_adult() != null)
        target.put("include_adult", movieSearchRequest.getInclude_adult().toString());

        if(movieSearchRequest.getRegion() != null)
        target.put("region", movieSearchRequest.getRegion().toString());

        if( movieSearchRequest.getYear() != null)
        target.put("year", movieSearchRequest.getYear().toString());

        if(movieSearchRequest.getPrimary_release_year() != null)
        target.put("primary_release_year", movieSearchRequest.getPrimary_release_year().toString());

        return target;
    }
}
