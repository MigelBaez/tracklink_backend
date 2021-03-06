package pe.com.tracklink.starter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.tracklink.starter.models.Cliente;
import pe.com.tracklink.starter.repositories.ClienteRepository;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api")
public class ClienteController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente postcliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("clientes")
    public List<Cliente> getclientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<Cliente> getcliente(@PathVariable String id) {
        Cliente cliente = clienteRepository.findOne(id);
        if (cliente == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(cliente);
    }



  
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("cliente/{id}")
    public Long deletecliente(@PathVariable String id) {
        return clienteRepository.delete(id);
    }



 
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("cliente")
    public Cliente putcliente(@RequestBody Cliente cliente) {
        return clienteRepository.update(cliente);
    }

    
    @PatchMapping("cliente")
    public Cliente patchcliente(@RequestBody Cliente cliente) {
        return clienteRepository.update(cliente);
    }




    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}