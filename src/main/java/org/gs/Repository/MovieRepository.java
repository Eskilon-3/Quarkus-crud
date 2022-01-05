package org.gs.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.gs.Entity.Movie;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {
}
