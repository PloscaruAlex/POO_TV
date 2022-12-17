package functionality;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OutputHelper {
    public static ObjectNode userOutputHelper(User u) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        ObjectNode credentials = objectMapper.createObjectNode();
        credentials.put("name", u.getCredentials().getName());
        credentials.put("password", u.getCredentials().getPassword());
        credentials.put("accountType", u.getCredentials().getAccountType());
        credentials.put("country", u.getCredentials().getCountry());
        credentials.put("balance", u.getCredentials().getBalance());
        node.set("credentials", credentials);

        node.put("tokensCount", u.getTokensCount());
        node.put("numFreePremiumMovies", u.getNumFreePremiumMovies());

        ArrayNode p = objectMapper.createArrayNode();
        for (Movie m : u.getPurchasedMovies()) {
            if (m != null) {
                ObjectNode movieNode = movieOutputHelper(m);
                p.add(movieNode);
            }
        }
        node.set("purchasedMovies", p);
        ArrayNode w = objectMapper.createArrayNode();
        for (Movie m : u.getWatchedMovies()) {
            if (m != null) {
                ObjectNode movieNode = movieOutputHelper(m);
                w.add(movieNode);
            }
        }
        node.set("watchedMovies", w);
        ArrayNode l = objectMapper.createArrayNode();
        for (Movie m : u.getLikedMovies()) {
            if (m != null) {
                ObjectNode movieNode = movieOutputHelper(m);
                l.add(movieNode);
            }
        }
        node.set("likedMovies", l);
        ArrayNode r = objectMapper.createArrayNode();
        for (Movie m : u.getRatedMovies()) {
            if (m != null) {
                ObjectNode movieNode = movieOutputHelper(m);
                r.add(movieNode);
            }
        }
        node.set("ratedMovies", r);

        return node;
    }

    public static ObjectNode movieOutputHelper(Movie m) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.put("name", m.getName());
        node.put("year", m.getYear());
        node.put("duration", m.getDuration());

        ArrayNode g = objectMapper.createArrayNode();
        for (String s : m.getGenres()) {
            g.add(s);
        }
        node.set("genres", g);
        ArrayNode a = objectMapper.createArrayNode();
        for (String s : m.getActors()) {
            a.add(s);
        }
        node.set("actors", a);
        ArrayNode c = objectMapper.createArrayNode();
        for (String s : m.getCountriesBanned()) {
            c.add(s);
        }
        node.set("countriesBanned", c);

        node.put("numLikes", m.getNumLikes());
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(m.getRating());
        node.put("rating", Double.parseDouble(formattedNumber));
        node.put("numRatings", m.getNumRatings());

        return node;
    }

    public static ObjectNode loginInfo(User u) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", null);
        ArrayNode arr = objectMapper.createArrayNode();
        node.set("currentMoviesList", arr);
        node.set("currentUser", userOutputHelper(u));

        return node;
    }

    public static ObjectNode error(CurrentSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", "Error");
        ArrayNode arr = objectMapper.createArrayNode();
        node.set("currentMoviesList", arr);
        node.set("currentUser", null);

        return node;
    }

    public static ObjectNode search(ArrayList<Movie> m, User u) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        ArrayNode arr = objectMapper.createArrayNode();
        for (Movie movie : m) {
            ObjectNode movieNode = movieOutputHelper(movie);
            arr.add(movieNode);
        }
        node.set("error", null);
        node.set("currentMoviesList", arr);
        node.set("currentUser", userOutputHelper(u));

        return node;
    }

    public static ObjectNode moviesInfo(CurrentSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", null);
        ArrayNode arr = objectMapper.createArrayNode();
        for (Movie m : session.getCurrentMovieList()) {
            if (!m.getCountriesBanned().contains(session.getCurrentUser().getCredentials().getCountry())) {
                arr.add(movieOutputHelper(m));
            }
        }
        node.set("currentMoviesList", arr);
        node.set("currentUser", userOutputHelper(session.getCurrentUser()));

        return node;
    }

    public static ObjectNode filter(ArrayList<Movie> movies, User u) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", null);
        ArrayNode arr = objectMapper.createArrayNode();
        for (Movie m : movies) {
            if (!m.getCountriesBanned().contains(u.getCredentials().getCountry())) {
                arr.add(movieOutputHelper(m));
            }
        }
        node.set("currentMoviesList", arr);
        node.set("currentUser", userOutputHelper(u));

        return node;
    }

    public static ObjectNode details(Movie movie, User u) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", null);
        ArrayNode arr = objectMapper.createArrayNode();
        if (movie != null) {
            arr.add(movieOutputHelper(movie));
        }
        node.set("currentMoviesList", arr);
        node.set("currentUser", userOutputHelper(u));

        return node;
    }
}
