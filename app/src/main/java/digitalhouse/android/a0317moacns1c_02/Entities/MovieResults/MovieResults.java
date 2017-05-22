package digitalhouse.android.a0317moacns1c_02.Entities.MovieResults;

import java.util.ArrayList;

/**
 * Created by Pablo on 22/05/2017.
 */

public class MovieResults {
    private Integer page;
    private ArrayList<MovieResultsItem> results;
    private Integer total_results;
    private Integer total_pages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<MovieResultsItem> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieResultsItem> results) {
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
