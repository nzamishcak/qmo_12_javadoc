package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * This class is an extension of AbstractWebEndpoint. It handles operations for User endpoints.
 *
 * @author ChatGPT & Comp.
 */
public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
     * Constructor for UserEndpoint.
     *
     * @param specification (RequestSpecification) - Used for requests in this endpoint.
     */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * This method is for creating a user.
     *
     * @param userDto (UserDto) - Contains the information for the new user.
     * @return (UserDto) - Represents the newly created user.
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
                .extract().as(UserDto.class);
    }

    /**
     * This method is for creating a user and validating the response status.
     *
     * @param userDto (UserDto) - Contains the information for the new user.
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
                this.specification,
                USERS_END,
                userDto)
                .statusCode(status.getCode());
    }

    /**
     * This method is for updating a user.
     *
     * @param id (int) - The id of the user to be updated.
     * @param userDto (UserDto) - Contains the updated information for the user.
     * @return (UserDto) - Represents the updated user.
     */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * This method is for updating a user and validating the response status.
     *
     * @param userDto (UserDto) - Contains the updated information for the user.
     * @param id (int) - The id of the user to be updated.
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
                this.specification,
                USERS_RESOURCE_END,
                userDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * This method is for getting a user by its id.
     *
     * @param id (String) - The id of the user to be retrieved.
     * @return (UserDto) - Represents the retrieved user.
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * This method is for getting a user by its id and validating the response status.
     *
     * @param id (String) - The id of the user to be retrieved.
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
                this.specification,
                USERS_RESOURCE_END,
                id)
                .statusCode(status.getCode());
    }

    /**
     * This method is for getting all users.
     *
     * @return (List<UserDto>) - Represents all the retrieved users.
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * This method is for getting all users and validating the response status.
     *
     * @param status (HttpStatus) - The expected Http status code.
     * @return (ValidatableResponse) - Can be used to further validate the response.
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
