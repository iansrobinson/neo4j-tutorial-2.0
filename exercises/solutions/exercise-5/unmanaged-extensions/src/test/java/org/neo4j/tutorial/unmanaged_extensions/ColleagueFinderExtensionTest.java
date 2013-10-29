package org.neo4j.tutorial.unmanaged_extensions;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.neo4j.server.CommunityNeoServer;
import org.neo4j.server.helpers.CommunityServerBuilder;
import org.neo4j.tutorial.unmanaged_extensions.utilities.DatabaseFixture;
import org.neo4j.tutorial.unmanaged_extensions.utilities.ExampleData;
import org.neo4j.tutorial.unmanaged_extensions.utilities.Migration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class ColleagueFinderExtensionTest
{
    private CommunityNeoServer server;

    @Before
    public void startServer() throws IOException
    {
        server = CommunityServerBuilder.server()
                .withThirdPartyJaxRsPackage(
                        "org.neo4j.tutorial.unmanaged_extensions", "/colleagues" )
                .build();
        server.start();

        DatabaseFixture.useExistingDatabase( server.getDatabase().getGraph() )
                .populateWith( ExampleData.cypher )
                .applyMigrations(Collections.<Migration>emptyList()  );
    }

    @After
    public void stopServer()
    {
        server.stop();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnColleaguesWithSimilarSkills() throws Exception
    {
        Client client = Client.create( new DefaultClientConfig() );

        WebResource resource = client
                .resource( "http://localhost:7474/colleagues/similar-skills/Ian" );
        ClientResponse response = resource
                .accept( MediaType.APPLICATION_JSON )
                .get( ClientResponse.class );

        List<Map<String, Object>> results = new ObjectMapper().readValue( response.getEntity( String.class ),
                List.class );

        assertEquals( 200, response.getStatus() );
        assertEquals( MediaType.APPLICATION_JSON, response.getHeaders().get( "Content-Type" ).get( 0 ) );
        assertEquals( "Lucy", results.get( 0 ).get( "name" ) );
        assertThat( (Iterable<String>) results.get( 0 ).get( "skills" ), hasItems( "Java", "Neo4j" ) );
    }
}
