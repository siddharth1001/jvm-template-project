package com.example.app.resources

import com.google.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.container.AsyncResponse
import javax.ws.rs.container.Suspended
import javax.ws.rs.core.MediaType

@Path("/")
class RootResource @Inject constructor() {
    @Path("/ok")
    @GET
    fun test(@Suspended asyncResponse: AsyncResponse): Boolean {
        // https://dzone.com/articles/jax-rs-20-asynchronous-server-and-client
        // https://eclipse-ee4j.github.io/jersey.github.io/documentation/2.11/async.html
        val result = "Response OK!"
        return asyncResponse.resume(result)
    }

    /**
     *  Add child api resources here
     */
    // @Path("users")
    // fun getUsersResource() = userResource
}