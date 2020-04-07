package pe.com.tracklink.starter.repositories;

import org.springframework.stereotype.Repository;
import pe.com.tracklink.starter.models.Cliente;
import java.util.List;

@Repository
public interface ClienteRepository {

    Cliente save(Cliente person);


    List<Cliente> findAll();


    Cliente findOne(String id);

  
    long delete(String id);


    Cliente update(Cliente person);


}