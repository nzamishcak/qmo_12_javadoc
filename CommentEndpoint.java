package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * This class is an extension of AbstractWebEndpoint. It handles operations for Comment endpoints.
 */
public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    /**
     * Constructor for CommentEndpoint.
     *
     * @param specification (RequestSpecification) - Used for requests in this endpoint.
     */
    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * This method is for creating a comment.
     *
     * @param commentDto (CommentDto) - Contains the information for the new comment.
     * @return (CommentDto) - Represents the newly created comment.
     */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
                .extract().as(CommentDto.class);
    }

    /**
     * This method is for creating a comment and validating the response status.
     *
     * @param commentDto (CommentDto) - Contains the information for the new comment.
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
                this.specification,
                COMMENTS_END,
                commentDto)
                .statusCode(status.getCode());
    }

    /**
     * This method is for updating a comment.
     *
     * @param id (int) - The id of the comment to be updated.
     * @param commentDto (CommentDto) - Contains the updated information for the comment.
     * @return (CommentDto) - Represents the updated comment.
     */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * This method is for updating a comment and validating the response status.
     *
     * @param commentDto (CommentDto) - Contains the updated information for the comment.
     * @param id (int) - The id of the comment to be updated.
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
                this.specification,
                COMMENTS_RESOURCE_END,
                commentDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * This method is for getting a comment by its id.
     *
     * @param id (int) - The id of the comment to be retrieved.
     * @return (CommentDto) - Represents the retrieved comment.
     */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * This method is for getting a comment by its id and validating the response status.
     *
     * @param id (int) - The id of the comment to be retrieved.
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
                this.specification,
                COMMENTS_RESOURCE_END,
                String.valueOf(id))
                .statusCode(status.getCode());
    }

    /**
     * This method is for getting all comments.
     *
     * @return (List of CommentDto objects) - Represents all the retrieved comments.
     */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
     * This method is for getting all comments and validating the response status.
     *
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }
}

