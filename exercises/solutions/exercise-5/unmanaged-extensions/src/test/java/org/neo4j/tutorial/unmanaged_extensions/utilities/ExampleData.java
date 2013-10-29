package org.neo4j.tutorial.unmanaged_extensions.utilities;

public class ExampleData
{
    public static String cypher = "CREATE (ian:person {name:'Ian'}),\n" +
            "       (bill:person {name:'Bill'}),\n" +
            "       (lucy:person {name:'Lucy'}),\n" +
            "       (acme:company {name:'Acme'}),\n" +
            "       (java:skill {name:'Java'}),\n" +
            "       (csharp:skill {name:'C#'}),\n" +
            "       (neo4j:skill {name:'Neo4j'}),\n" +
            "       (ruby:skill {name:'Ruby'}),\n" +
            "       (ian)-[:WORKS_FOR]->(acme),\n" +
            "       (bill)-[:WORKS_FOR]->(acme),\n" +
            "       (lucy)-[:WORKS_FOR]->(acme),\n" +
            "       (ian)-[:HAS_SKILL]->(java),\n" +
            "       (ian)-[:HAS_SKILL]->(csharp),\n" +
            "       (ian)-[:HAS_SKILL]->(neo4j),\n" +
            "       (bill)-[:HAS_SKILL]->(neo4j),\n" +
            "       (bill)-[:HAS_SKILL]->(ruby),\n" +
            "       (lucy)-[:HAS_SKILL]->(java),\n" +
            "       (lucy)-[:HAS_SKILL]->(neo4j)";
}
