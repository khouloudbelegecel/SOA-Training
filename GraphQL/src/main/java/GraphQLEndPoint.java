
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.SchemaParser;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.ModuleBusiness;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {

    public GraphQLEndPoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        UniteEnseignementBusiness ueb = new UniteEnseignementBusiness();
        ModuleBusiness mb = new ModuleBusiness(ueb);
        return SchemaParser.newParser()
                .file("schema.graphql")
                .resolvers(new Query(mb, ueb), new MutationResolver(mb, ueb))
                .build()
                .makeExecutableSchema();
    }

}
