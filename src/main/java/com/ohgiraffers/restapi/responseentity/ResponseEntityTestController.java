package com.ohgiraffers.restapi.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {


    private List<PokemonDTO> pokemons;

    public ResponseEntityTestController() {
        pokemons = new ArrayList<>();

        pokemons.add(new PokemonDTO(1, "pokemon01", "test03", "피카츄", LocalDate.now()));
        pokemons.add(new PokemonDTO(2, "pokemon02", "test03", "꼬부기", LocalDate.now()));
        pokemons.add(new PokemonDTO(3, "pokemon03", "test03", "파이리", LocalDate.now()));

    }

    @GetMapping("/pokemons")
    public ResponseEntity<ResponseMessage> findAllPokemons() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );


        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("pokemons", pokemons);

        ResponseMessage responseMessage = new ResponseMessage(

                900,
                "조회성공!",
                responseMap
        );


        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);

    }

    @GetMapping("/pokemons/{pokemonNo}")
    public ResponseEntity<ResponseMessage> findPokemonByNo(@PathVariable int pokemonNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );

        PokemonDTO foundPokemon = pokemons.stream()
                .filter(pokemon -> pokemon.getNo() == pokemonNo).toList().get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("pokemon", foundPokemon);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(900, "조회성공", responseMap));
    }


    @PostMapping("/pokemons")
    public ResponseEntity<?> regist(@RequestBody PokemonDTO newPokemon) {

        System.out.println("newPokemon = " + newPokemon);

        int latPokemonNo = pokemons.get(pokemons.size() - 1).getNo();

        newPokemon.setNo(latPokemonNo + 1);

        pokemons.add(newPokemon);

        return ResponseEntity
                .created(URI.create("/entity/pokemons/" + pokemons.getLast().getNo()))
                .build();


        @PutMapping("/pokemons/{pokemonNo}")
        public ResponseEntity<?> modifyPokemon ( @PathVariable int PokemonNo, @RequestBody PokemonDTO modifyInfo){

            PokemonDTO foundPokemon = pokemons.stream().filter(pokemon -> pokemon.getNo() == pokemonNo).toList().get(0);

            foundPokemon.setName(modifyInfo.getName());
            foundPokemon.setType(modifyInfo.getType());
            foundPokemon.setName(modifyInfo.getName());

            return ResponseEntity.created(URI.create("/entity/pokemons/" + pokemons)).build();
        }


        @DeleteMapping("/pokemons/{pokemonNo")
        public ResponseEntity<?> removePokemon (@PathVariable int pokemonNo){

            PokemonDTO foundPokemon = pokemons.stream().filter(pokemon -> pokemon.getNo() == pokemonNo).toList().get(0);
            pokemons.remove(foundPokemon);

            return ResponseEntity.noContent().build();
        }
    }
}
