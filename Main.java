import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/solve", (exchange) -> {
            // Enable CORS so the browser can talk to this server
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            // Parse URL parameters
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String formula = params.getOrDefault("formula", "x+y");
            double target = Double.parseDouble(params.getOrDefault("target", "100"));

            // Use your engine logic
            Lexer lexer = new Lexer(formula);
            Parser parser = new Parser(lexer.tokenize());
            Node root = parser.parseExpression();
            Solver solver = new Solver();

            List<String> combinations = new ArrayList<>();
            // Loop through possibilities (adjust limits as needed)
            for (int x = 0; x <= 100; x++) {
                for (int y = 0; y <= 100; y++) {
                    Map<String, Double> vars = new HashMap<>();
                    vars.put("x", (double) x);
                    vars.put("y", (double) y);
                    if (solver.evaluate(root, vars) == target) {
                        combinations.add("{\"x\":" + x + ",\"y\":" + y + "}");
                    }
                }
            }

            String response = "{\"combinations\": [" + String.join(",", combinations) + "]}";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        System.out.println("ALGEBRAIC ENGINE SERVER STARTED ON PORT 8080...");
        server.start();
    }

    // This helper method is required to read your URL parameters
    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }
        }
        return result;
    }
}