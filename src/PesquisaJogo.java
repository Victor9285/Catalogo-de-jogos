import java.util.List;

public record PesquisaJogo(
        String name,
        String released,
        List<Genero> genres) {
}
