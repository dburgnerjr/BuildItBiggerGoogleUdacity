package com.danielburgnerjr.builditbiggergoogleudacity.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.danielburgnerjr.jokelibrary.Joke;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbiggergoogleudacity.danielburgnerjr.com",
                ownerName = "backend.builditbiggergoogleudacity.danielburgnerjr.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "showJoke")
    public MyBean showJoke() {
        MyBean response = new MyBean();
        Joke jokeProvider = new Joke();
        response.setData(jokeProvider.getJoke());

        return response;
    }

}
