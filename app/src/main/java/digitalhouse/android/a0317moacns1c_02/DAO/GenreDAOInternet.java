package digitalhouse.android.a0317moacns1c_02.DAO;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.TMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genres;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class GenreDAOInternet {
    private TMDBClient client;
    public GenreDAOInternet() {
        this.client = ServiceGenerator.getInstance().createService(TMDBClient.class, TMDBClient.BASE_URL);
    }

    public void obtainGenres(ResultListener<List<Genre>> resultListener) {
        ObtainGenresTask obtainGenresTask = new ObtainGenresTask(resultListener);
        obtainGenresTask.execute();
    }

    private class ObtainGenresTask extends AsyncTask<String, Void, List<Genre>> {
        private ResultListener<List<Genre>> resultListener;
        public ObtainGenresTask(ResultListener<List<Genre>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Genre> doInBackground(String... params) {
            List<Genre> genreList = new ArrayList<>();
            Call<Genres> callMovies = client.obtainMovieGenres(TMDBClient.API_KEY);
            Call<Genres> callSeries = client.obtainSerieGenres(TMDBClient.API_KEY);
            try {
                Genres genresMovies = callMovies.execute().body();
                Genres genresSeries = callSeries.execute().body();
                if (genresMovies!=null && genresSeries!=null) {
                    List<Genre> genreListMovies = genresMovies.getGenres();
                    List<Genre> genreListSeries = genresSeries.getGenres();
                    for (Genre genre : genreListMovies) {
                        genre.setType(Genre.TYPE_MOVIES);
                    }
                    for (Genre genre : genreListSeries) {
                        genre.setType(Genre.TYPE_SERIES);
                    }
                    genreList.addAll(genreListMovies);
                    genreList.addAll(genreListSeries);
                }
                return genreList;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Genre> genres) {
            super.onPostExecute(genres);
            resultListener.finish(genres);
        }
    }
}
