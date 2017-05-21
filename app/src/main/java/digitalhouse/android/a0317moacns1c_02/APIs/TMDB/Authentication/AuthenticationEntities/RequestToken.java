package digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticationEntities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class RequestToken {

    private Boolean success;
    private String expires_at;
    private Date expirationDate;
    private String request_token;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public Date getExpirationDate() throws ParseException {
        if(expirationDate == null) {
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            Date date = format.parse(this.expires_at);
            this.expirationDate = date;
        }
        return expirationDate;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
