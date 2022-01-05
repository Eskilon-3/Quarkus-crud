package org.gs.Service;

import org.gs.Entity.Movie;
import org.gs.Repository.MovieRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieService {

    @Inject
    MovieRepository movieRepository;

    @GET
    public Response getmovies(){
        List<Movie> movies = movieRepository.listAll();
        return Response.ok(movies).build();
    }

    @POST
    @Transactional
    public Response createMovie(Movie newMovie){
        movieRepository.persist(newMovie);
        if(movieRepository.isPersistent(newMovie)){
            return Response.created(URI.create("/movies/" + newMovie.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Movie updatedMovie(@PathParam("id") Long id, Movie updatedMovie){
        Movie movie = movieRepository.findById(id);
        movie.setTitle(updatedMovie.getTitle());
        movieRepository.persist(movie);
        return movie;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteMovie(@PathParam("id") Long id){
        boolean deleted = movieRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }


}
