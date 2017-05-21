package digitalhouse.android.a0317moacns1c_02.Entities;

import java.util.List;

/**
 * Created by Pablo on 21/05/2017.
 */

public class ImageConfig {
    private String baseUrl;
    private String secureBaseUrl;
    private List<String> backdropSizes;
    private List<String> logoSizes;
    private List<String> posterSizes;
    private List<String> profileSizes;
    private List<String> stillSizes;
    public ImageConfig() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public String getPosterSize(Integer i) {
        return posterSizes.get(i);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public void setStillSizes(List<String> stillSizes) {
        this.stillSizes = stillSizes;
    }
}
