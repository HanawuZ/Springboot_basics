package com.example.springboot.controllers;
import java.util.List;
import java.util.Optional;

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

import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.models.Publisher;
import com.example.springboot.models.requests.PublisherRequest;
import com.example.springboot.services.PublisherService;

/**
 * InnerPublisherController
 */
interface IPublisherController {
    ResponseEntity<BaseResponse> getAllPublishers();
    ResponseEntity<BaseResponse> getPublisherById(String id);
    ResponseEntity<BaseResponse> createPublisher(PublisherRequest publisherRequest);
    ResponseEntity<BaseResponse> deletePublisher(String id);
    ResponseEntity<BaseResponse> updatePublisher(String id, PublisherRequest publisherRequest);
    
}


@CrossOrigin
@RequestMapping("/publishers")
@RestController
public class PublisherController implements IPublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllPublishers() {
        try {
            List<Publisher> result = publisherService.getAllPublishers();
            if (result == null) {
                return BaseResponse.NotFound("", "No publishers found", null);
            }
            return BaseResponse.OK("Found publishers", null, result);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage(), null );
        }
    }


    @GetMapping("/by-id")
    public ResponseEntity<BaseResponse> getPublisherById(@RequestParam(name = "id") String id) {
        try {
            Optional<Publisher> publisher = publisherService.getPublisherById(id);
            if (!publisher.isPresent()) {
                return BaseResponse.NotFound("Publisher not found", "Publisher with given ID does not exist", null);
            }
            return BaseResponse.OK("Found publisher", null, publisher);
        } catch(Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage(), null);
        }
    }

    @PostMapping
    public  ResponseEntity<BaseResponse> createPublisher(@RequestBody PublisherRequest publisherRequest) {
        try {
            // If field is empty
            if (publisherRequest == null) {
                return BaseResponse.BadRequest(null, "Publisher request cannot be empty", null);
            }

            if (publisherRequest.getName() == null || publisherRequest.getName().isEmpty()) {
                return BaseResponse.BadRequest(null, "Publisher name cannot be empty", null);
            }


            boolean isComplete = publisherService.createPublisher(publisherRequest);
            if (!isComplete) {
                return BaseResponse.BadRequest(null, "Publisher could not be created", null);
            }
            return BaseResponse.Created("Publisher is created", null, publisherRequest);

        } catch(Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updatePublisher(@RequestParam(name = "id") String id, @RequestBody PublisherRequest publisherRequest) {
        try {
            if (id == null || id.isEmpty() || id == "") {
                return BaseResponse.BadRequest(null, "Publisher ID cannot be empty", null);
            }
            boolean isComplete = publisherService.updatePublisher(id, publisherRequest);
            if (!isComplete) {
                return BaseResponse.BadRequest(null, "Publisher could not be updated", null);
            }
            return BaseResponse.OK("Publisher is updated", null, null);
        } catch(Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage(), null);
        }
    }


    @DeleteMapping
    public ResponseEntity<BaseResponse> deletePublisher(@RequestParam(name = "id") String id) {
        try {
            if (id == null || id.isEmpty() || id == "") {
                return BaseResponse.BadRequest(null, "Publisher ID cannot be empty", null);
            }
            boolean isComplete = publisherService.deletePublisher(id);
            if (!isComplete) {
                return BaseResponse.BadRequest(null, "Publisher could not be deleted", null);
            }
            return BaseResponse.OK("Publisher is deleted", null, null);
        } catch(Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage(), null);
        }
    }
}
