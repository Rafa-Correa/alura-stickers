import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os tTop 250 Filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.print("\u001b[1mTítulo: ");
            System.out.print("\u001b[0m");
            System.out.println(filme.get("title"));
            System.out.print("\u001b[1mPoster: ");
            System.out.print("\u001b[0m");
            System.out.println(filme.get("image"));
            System.out.print("\u001b[1mClassificação: ");
            System.out.print("\u001b[0m");
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
