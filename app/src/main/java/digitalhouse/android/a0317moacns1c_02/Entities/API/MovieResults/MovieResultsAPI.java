package digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults;

import java.util.ArrayList;

/**
 * Created by Pablo on 22/05/2017.
 */

public class MovieResultsAPI {
    private Integer page;
    private ArrayList<MovieResultsItemAPI> results;
    private Integer total_results;
    private Integer total_pages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<MovieResultsItemAPI> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieResultsItemAPI> results) {
        this.results = results;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }
}
