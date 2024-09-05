package br.com.lelis.controllers;

import br.com.lelis.data.vo.BookVO;
import br.com.lelis.data.vo.PersonVO;
import br.com.lelis.data.vo.v2.PersonVOV2;
import br.com.lelis.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name="Books", description = "Endpoints for Managing Books")
public class BookController {

    @Autowired
    private BookServices service;

    @GetMapping(produces = {
            br.com.lelis.util.MediaType.APPLICATION_JSON,
            br.com.lelis.util.MediaType.APPLICATION_XML,
            br.com.lelis.util.MediaType.APPLICATION_YML
    }
    )
    @Operation(
            summary = "Finds all books",
            description = "Finds all books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public List<BookVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    @Operation(
            summary = "Finds a book",
            description = "Finds a book by ID",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(
                                    schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public BookVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            },
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    @Operation(
            summary = "Creates a book",
            description = "Creates a book by passing in a JSON, XML or YML representation of People ",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(
                                    schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public BookVO create(@RequestBody BookVO book) {
        return service.create(book);
    }

    @PutMapping(
            consumes = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            },
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    @Operation(
            summary = "Updates a book",
            description = "Updates a book by passing in a JSON, XML or YML representation of People ",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(
                                    schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    public BookVO update(@RequestBody BookVO book) {
        return service.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deletes a book",
            description = "Deletes a book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            })
    // adding ResponseEntity<?> we can return the right error code
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        // returns 204 code
        return ResponseEntity.noContent().build();
    }
}
