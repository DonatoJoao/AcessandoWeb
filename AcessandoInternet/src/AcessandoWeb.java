import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AcessandoWeb {

    public static void main(String[] args) {
        String urlString = "https://www.bibliaonline.com.br/nvi";

        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    content.append(line);
                }
                reader.close();

                System.out.println("Acessando pagina: " + content.toString());
            } else {
                System.out.println("Falha ao se conectar: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
}
