package pe.com.tracklink.starter.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.WriteModel;

import pe.com.tracklink.starter.models.Cliente;

import org.bson.BsonDocument;
import org.bson.BsonNull;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.ReturnDocument.AFTER;
import static java.util.Arrays.asList;

@Repository
public class MongoDBClienteRepository implements ClienteRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();
    @Autowired
    private MongoClient client;
    private MongoCollection<Cliente> clienteCollection;

    @PostConstruct
    void init() {
        clienteCollection = client.getDatabase("db_tracklink").getCollection("clientes", Cliente.class);
    }

    @Override
    public Cliente save(Cliente cliente) {
        cliente.setId(new ObjectId());
        clienteCollection.insertOne(cliente);
        return cliente;
    }



    @Override
    public List<Cliente> findAll() {
        return clienteCollection.find().into(new ArrayList<>());
    }



    @Override
    public Cliente findOne(String id) {
        return clienteCollection.find(eq("_id", new ObjectId(id))).first();
    }

  

    @Override
    public long delete(String id) {
        return clienteCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }





    @Override
    public Cliente update(Cliente cliente) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return clienteCollection.findOneAndReplace(eq("_id", cliente.getId()), cliente, options);
    }



  

    private List<ObjectId> mapToObjectIds(List<String> ids) {
        return ids.stream().map(ObjectId::new).collect(Collectors.toList());
    }
}