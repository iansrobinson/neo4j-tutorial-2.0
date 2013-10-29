package org.neo4j.tutorial.unmanaged_extensions;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import org.neo4j.server.database.CypherExecutor;

@Path("/similar-skills")
public class ColleagueFinderExtension
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private ColleagueFinder colleagueFinder;

    public ColleagueFinderExtension( @Context CypherExecutor cypherExecutor )
    {
        this.colleagueFinder = new ColleagueFinder( cypherExecutor.getExecutionEngine() );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response getColleagues( @PathParam("name") String name ) throws IOException
    {
        Response response = null;

        String json = MAPPER
                .writeValueAsString( colleagueFinder.findColleaguesFor( name ) );
        response = Response.ok().entity( json ).build();

        return response;
    }
}
