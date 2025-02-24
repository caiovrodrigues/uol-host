package com.caio.uol.service;

import com.caio.uol.domain.Jogador;
import com.caio.uol.domain.enumeration.Time;
import com.caio.uol.infra.db.JogadorRepository;
import com.caio.uol.service.strategy.CodinomeProvider;
import com.caio.uol.web.dto.JogadorCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class JogadoresService {

    private final JogadorRepository jogadorRepository;

    private final Map<String, CodinomeProvider> codinomeProvider;

    public JogadoresService(JogadorRepository jogadorRepository, Map<String, CodinomeProvider> codinomeProvider) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeProvider = codinomeProvider;
    }

    public List<Jogador> listJogadores(String sort, Integer pageSize, Integer pageNumber){
        return jogadorRepository.findAll(sort, pageSize, pageNumber);
    }

    public Jogador createJogador(JogadorCreateRequest jogadorDTO){
        Time time = Time.getTimeByName(jogadorDTO.time());
        String codinome = codinomeProvider.get(time.getBeanName()).getCodinome(time);
        String heroImageUrl = getImageUrlOfHero(time, codinome);
        Jogador jogador = new Jogador(jogadorDTO.nome(), jogadorDTO.email(), jogadorDTO.telefone(), codinome, heroImageUrl, time);

        return jogadorRepository.save(jogador);
    }

    private String getImageUrlOfHero(Time time, String codinome){

        return switch (time){
            case Time.VINGADORES -> switch (codinome){
                case "Hulk" -> "https://pngimg.com/d/hulk_PNG104.png";
                case "Capitão América" -> "https://e7.pngegg.com/pngimages/75/572/png-clipart-captain-america-captain-america.png";
                case "Pantera Negra" -> "https://www.pngmart.com/files/2/Black-Panther-PNG-File.png";
                case "Homem de Ferro" -> "https://e7.pngegg.com/pngimages/991/612/png-clipart-ironman-ironman.png";
                case "Thor" -> "https://www.freeiconspng.com/uploads/thor-png-18.png";
                case "Feiticeira Escarlate" -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ea196117-0b64-49b7-b13f-79f43cf77e53/dbrojjq-9b1390ab-0bc9-4618-afbe-712e90db55f0.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2VhMTk2MTE3LTBiNjQtNDliNy1iMTNmLTc5ZjQzY2Y3N2U1M1wvZGJyb2pqcS05YjEzOTBhYi0wYmM5LTQ2MTgtYWZiZS03MTJlOTBkYjU1ZjAucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.h-0V9zcl7ZMjPrkvtk-aRQ47xhBqQ2sh7PFIlhT9Z0Y";
                case "Visão" -> "https://w7.pngwing.com/pngs/106/988/png-transparent-vision-ultron-wanda-maximoff-carol-danvers-marvel-cinematic-universe-ultron-marvel-avengers-assemble-avengers-fictional-characters.png";
                default -> "https://cdn-icons-png.flaticon.com/512/2748/2748558.png";
            };
            case Time.LIGA_DA_JUSTICA -> switch (codinome){
                case "Lanterna Verde" -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/6c97c05e-4803-406f-9a76-e27903e6cad2/dfoqhba-3503c7cc-9c0a-48f3-bf02-16ea83acb83d.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzZjOTdjMDVlLTQ4MDMtNDA2Zi05YTc2LWUyNzkwM2U2Y2FkMlwvZGZvcWhiYS0zNTAzYzdjYy05YzBhLTQ4ZjMtYmYwMi0xNmVhODNhY2I4M2QucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.UPC4Gnej_UwNDDNiTSBsd0UM2eUPrESfptHxL9yZk9M";
                case "Flash" -> "https://www.pngplay.com/wp-content/uploads/8/The-Flash-Barry-Allen-Background-PNG-Image.png";
                case "Aquaman" -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/846a9086-8a40-43e0-aa10-2fc7d6d73730/dct5xex-bc5a7570-81b3-432c-8a08-9dd543699956.png/v1/fill/w_1280,h_743/aquaman_2018____arthur_curry_png_by_mintmovi3_dct5xex-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NzQzIiwicGF0aCI6IlwvZlwvODQ2YTkwODYtOGE0MC00M2UwLWFhMTAtMmZjN2Q2ZDczNzMwXC9kY3Q1eGV4LWJjNWE3NTcwLTgxYjMtNDMyYy04YTA4LTlkZDU0MzY5OTk1Ni5wbmciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.hbNcC4gB_iNML5yLHtQFzbHwVDZ2YU324ndsPPiXYDM";
                case "Batman" -> "https://i.pinimg.com/736x/5a/9e/98/5a9e9886c59bdf24166f3eea253087ef.jpg";
                case "Superman" -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/5b8d2b12-21e8-4931-8a6d-fb9ecdd60383/ddkliqc-c3421740-c866-49d2-b808-0e153d2dac09.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzViOGQyYjEyLTIxZTgtNDkzMS04YTZkLWZiOWVjZGQ2MDM4M1wvZGRrbGlxYy1jMzQyMTc0MC1jODY2LTQ5ZDItYjgwOC0wZTE1M2QyZGFjMDkucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.l3ZSWVm6mcWTEMGeAuwNpe22BO_G6Zcz7KIawzDedDE";
                case "Mulher Maravilha" -> "https://pngimg.com/d/wonder_woman_PNG57.png";
                default -> "https://cdn-icons-png.flaticon.com/512/2748/2748558.png";
            };
        };
    }

    public Jogador findJogadorByName(String name) {
        return jogadorRepository.findByName(name).orElseThrow();
    }

    public Jogador editJogador(JogadorCreateRequest jogadorDTO) {
        Jogador jogador = jogadorRepository.findByUuid(jogadorDTO.uuid()).orElseThrow();

        jogador.setNome(jogadorDTO.nome());
        jogador.setEmail(jogadorDTO.email());
        jogador.setTelefone(jogadorDTO.telefone());

        Time timeFromRequest = Time.getTimeByName(jogadorDTO.time());
        if(!Objects.equals(timeFromRequest, jogador.getTime())){
            String newCodinome = codinomeProvider.get(timeFromRequest.getBeanName()).getCodinome(timeFromRequest);
            jogador.setCodinome(newCodinome);
            jogador.setTime(timeFromRequest);
            jogador.setHeroImageURL(getImageUrlOfHero(timeFromRequest, newCodinome));
        }

        return jogadorRepository.save(jogador);
    }

    public Jogador findJogadorByUuid(String uuidPlayer) {
        return jogadorRepository.findByUuid(uuidPlayer).orElseThrow();
    }

    public void delete(String uuid) {
        Jogador jogadorToBeDeleted = findJogadorByUuid(uuid);
        jogadorRepository.deleteByJogador(jogadorToBeDeleted);
    }
}
