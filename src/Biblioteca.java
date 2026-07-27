import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Biblioteca {
    List<Jogo> listaDeJogos = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void adicionarJogo(Jogo jogo){
        listaDeJogos.add(jogo);
    }

    public void mostrarBiblioteca(){
        for (Jogo jogo : listaDeJogos) {
            System.out.println(jogo.toString());
        }
    }

    public void salvarJson(){
        try{
        FileWriter texto = new FileWriter("Biblioteca.json");
        texto.write(gson.toJson(listaDeJogos));
        texto.close();
        System.out.println("Salvo!");
        }catch(IOException e){
            System.out.println("Erro em criar o arquivo");
        }
    }


}
