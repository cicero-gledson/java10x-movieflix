package com.movieflix.controller.docInterface;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes")
public interface MovieControllerDoc {

    @Operation(summary = "Cria filme", description = "Cria um filme com as informações fornecidas no corpo da requisição",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode="201", description = "Filme salvo com sucesso",
            content = @Content(array=@ArraySchema(schema=@Schema(implementation = MovieResponse.class))))
    ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest);


    @Operation(summary = "Busca filmes", description = "Método responsável por retornar todos os filmes cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes cadastrados retornada com sucesso",
            content = @Content(array=@ArraySchema(schema=@Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> findAll();

    @Operation(summary = "Busca filme por ID", description = "Método responsável por retornar um filme pelo seu ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso",
            content = @Content(array=@ArraySchema(schema=@Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado com o ID informado",
            content = @Content())
    ResponseEntity<MovieResponse> findById(@PathVariable Long id);

    @Operation(summary = "Altera filme", description = "Altera as informações de um filme cadastrado, substituindo-as pelas informações fornecidas no corpo da requisição",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode="200", description = "Filme alterado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado com o ID informado", content = @Content())
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest);

    @Operation(summary = "Busca filmes por ID da categoria", description = "Método responsável por retornar um filmes de uma categoria, buscando pelo ID da categoria",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme(s) encontrado(s) com sucesso",
            content = @Content(array=@ArraySchema(schema=@Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado nessa categoria",
            content = @Content())
    public ResponseEntity<List<MovieResponse>> search(@RequestParam Long category);


    @Operation(summary = "Deleta filme por ID", description = "Método responsável por apagar um filme pelo seu ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme apagado com sucesso",
            content =  @Content())
    @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado com o ID informado",
            content = @Content())
    ResponseEntity<Void> delete(@PathVariable Long id);

}
