package com.example.springboot.controllers;

import com.example.springboot.models.Author;
import com.example.springboot.models.DataResult;
import com.example.springboot.models.requests.AuthorRequest;
import com.example.springboot.services.AuthorService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.constants.DataResultStatusCode;
import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.lib.http.StatusCode;

/**
 * InnerAuthorController
 */
interface IAuthorController {
    ResponseEntity<BaseResponse> getAllAuthors();

    ResponseEntity<BaseResponse> getAuthorById(String id);

    ResponseEntity<BaseResponse> createAuthor(AuthorRequest authorRequest);

    ResponseEntity<BaseResponse> deleteAuthor(String id);

    ResponseEntity<BaseResponse> updateAuthor(String id, AuthorRequest authorRequest);
}

@CrossOrigin
@RequestMapping("/authors")
@RestController
public class AuthorController implements IAuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllAuthors() {
        try {
            DataResult<List<Author>> result = authorService.getAllAuthors();
            if (result.getCode() == DataResultStatusCode.NOT_FOUND_DATA) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "No authors found", null));
            } 
            if (result.getCode() == DataResultStatusCode.INTERNAL_SERVER_ERROR) {
                return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, result.getError(), null));
            }

            return BaseResponse.Build(new BaseResponse(StatusCode.OK, "Found authors", result.getData()));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<BaseResponse> getAuthorById(@RequestParam(name = "id") String id) {
        try {
            DataResult<Author> result = authorService.getAuthorById(id);
            if (result.getCode() == DataResultStatusCode.NOT_FOUND_DATA) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "No authors found", null));
            }
            if (result.getCode() == DataResultStatusCode.INTERNAL_SERVER_ERROR) {
                return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, result.getError(), null));
            }
            return BaseResponse.Build(new BaseResponse(StatusCode.OK, "Found author", result.getData()));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
        try {
            // If field is empty
            if (authorRequest == null) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author cannot be empty", null));
            }

            // If firstname is empty
            if (authorRequest.getFirstname() == null || authorRequest.getFirstname().isEmpty() || authorRequest.getFirstname() == "") {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author name cannot be empty", null));
            }

            if (authorRequest.getLastname() == null || authorRequest.getLastname().isEmpty() || authorRequest.getLastname() == "") {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author last name cannot be empty", null));
            }

            // If dob is empty
            if (authorRequest.getDob() == null) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author date of birth cannot be empty", null));
            }

            DataResult<Author> result = authorService.createAuthor(authorRequest);
            if (result.getCode() == DataResultStatusCode.CREATE_DATA_FAILED) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author could not be created", null));
            }
            if (result.getCode() == DataResultStatusCode.INTERNAL_SERVER_ERROR) {
                return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, result.getError(), null));
            }
            return BaseResponse.Build(new BaseResponse(StatusCode.CREATED, "Author is created", result.getData()));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateAuthor(@RequestParam(name = "id") String id, @RequestBody AuthorRequest authorRequest) {
        try {
            if (authorRequest == null) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author cannot be empty", null));
            }

            if (id == null || id.isEmpty() || id == "") {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author ID cannot be empty", null));
            }

            // If firstname is empty
            if (authorRequest.getFirstname() == null || authorRequest.getFirstname().isEmpty()|| authorRequest.getFirstname() == "") {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author name cannot be empty", null));
            }

            if (authorRequest.getLastname() == null || authorRequest.getLastname().isEmpty() || authorRequest.getLastname() == "") {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author last name cannot be empty", null));
            }

            // If dob is empty
            if (authorRequest.getDob() == null) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author date of birth cannot be empty", null));
            }

            DataResult<Author> result = authorService.updateAuthor(id, authorRequest);
            if (result.getCode() == DataResultStatusCode.UPDATE_DATA_FAILED) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author could not be updated", null));
            }
            if (result.getCode() == DataResultStatusCode.INTERNAL_SERVER_ERROR) {
                return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, result.getError(), null));
            }

            return BaseResponse.Build(new BaseResponse(StatusCode.OK, "Author is updated", result.getData()));

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteAuthor(@RequestParam(name = "id") String id) {
        try {
            if (id == null || id.isEmpty() || id == "") {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author ID cannot be empty", null));
            }

            DataResult<Author> result = authorService.deleteAuthor(id);
            if (result.getCode() == DataResultStatusCode.DELETE_DATA_FAILED) {
                return BaseResponse
                        .Build(new BaseResponse(StatusCode.BAD_REQUEST, "Author could not be deleted", null));
            }
            if (result.getCode() == DataResultStatusCode.INTERNAL_SERVER_ERROR) {
                return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, result.getError(), null));
            }

            return BaseResponse.Build(new BaseResponse(StatusCode.OK, "Author is deleted", null));

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

}
