package br.com.macielgoncalves.quarkussocial.rest;

import br.com.macielgoncalves.quarkussocial.model.domain.User;
import br.com.macielgoncalves.quarkussocial.model.repository.UserRepository;
import br.com.macielgoncalves.quarkussocial.rest.dto.CreateUserRequest;
import br.com.macielgoncalves.quarkussocial.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    private final UserRepository userRepository;
    private final Validator validator;

    @Inject
    public UserResources(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest request) {

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }

        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        //user.persist();
        userRepository.persist(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    public Response listAllUsers() {
        //PanacheQuery<User> query = User.findAll();
        PanacheQuery<User> query = userRepository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUsers(@PathParam("id") Long id) {
        //User user = User.findById(id);
        User user = userRepository.findById(id);
        if (Objects.nonNull(user)) {
            //user.delete();
            userRepository.delete(user);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUsers(@PathParam("id") Long id, CreateUserRequest body) {
        //User user = User.findById(id);
        User user = userRepository.findById(id);
        if (Objects.nonNull(user)) {
            user.setName(body.getName());
            user.setAge(body.getAge());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
