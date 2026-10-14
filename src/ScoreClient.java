import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Talks to the score-service REST API over HTTP.
 * Every method fails quietly (returns false / empty list) if the server is down,
 * so the game still works offline.
 */
public class ScoreClient {

    private static final String BASE_URL = System.getProperty("scores.url", "http://localhost:8080");
    private static final String API_KEY = System.getenv().getOrDefault("SCORES_API_KEY", "dev-key-change-me");
    private static final int MAX_NAME_LENGTH = 20;

    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    /** One leaderboard row. */
    public static class Entry {
        public final String name;
        public final int score;

        public Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    /** Sends a score to the server. Returns true if it was saved (HTTP 201). */
    public boolean submitScore(String name, int score) {
        try {
            if (name.length() > MAX_NAME_LENGTH) {
                name = name.substring(0, MAX_NAME_LENGTH);
            }
            String json = "{\"name\":\"" + escape(name) + "\",\"score\":" + score + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/scores"))
                    .timeout(Duration.ofSeconds(5))
                    .header("Content-Type", "application/json")
                    .header("X-API-Key", API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201;
        } catch (Exception e) {
            return false;
        }
    }

    /** Fetches the top 10 scores, highest first. Empty list if the server can't be reached. */
    public List<Entry> getTopScores() {
        List<Entry> entries = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/scores/top"))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return entries;
            }

            // The response looks like: [{"id":1,"name":"Jack","score":120,...}, ...]
            // Pull out each {...} object, then read name and score from it.
            Matcher obj = Pattern.compile("\\{[^{}]*\\}").matcher(response.body());
            Pattern namePat = Pattern.compile("\"name\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"");
            Pattern scorePat = Pattern.compile("\"score\"\\s*:\\s*(\\d+)");

            while (obj.find()) {
                String text = obj.group();
                Matcher n = namePat.matcher(text);
                Matcher s = scorePat.matcher(text);
                if (n.find() && s.find()) {
                    entries.add(new Entry(unescape(n.group(1)), Integer.parseInt(s.group(1))));
                }
            }
        } catch (Exception e) {
            // server down or bad response: just return what we have
        }
        return entries;
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String unescape(String s) {
        return s.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
