import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class API {
    private String json;

    Gson gson = new Gson();

    public String requisicao(String nome) {
        try {
            if (nome.contains(" ")) {
                nome = nome.replace(" ", "+");
            }
            String apiKey = System.getenv("RAWG_API_KEY");
            String endereco = "https://api.rawg.io/api/games?key=" + apiKey + "&search=" + nome
                    + "&search_precise=true";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
            if (filtraJogo().name().isBlank()) {
                return "Jogo nao encontrado!";
            }
            return json;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PesquisaJogo filtraJogo() {
        RespostaJogo resposta = gson.fromJson(json, RespostaJogo.class); //vai entrar no gson, e pegar somente o results
        PesquisaJogo jogo = resposta.results().get(0); //vai entrar nesse results, e vai pegar somente o nome e released que a gente quer
        return jogo;
    }

}