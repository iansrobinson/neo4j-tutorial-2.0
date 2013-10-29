package org.neo4j.tutorial.unmanaged_extensions.utilities;

import org.neo4j.graphdb.GraphDatabaseService;

public interface Migration
{
    void apply(GraphDatabaseService db);
}
