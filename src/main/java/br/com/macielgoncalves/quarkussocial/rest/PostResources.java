package br.com.macielgoncalves.quarkussocial.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResources {

    @POST
    public Response savePost() {

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts() {

        return Response.ok().build();
    }
}
