package org.halo.thallo.api.docs.swagger;

import com.google.common.collect.Maps;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Import(FeignClientsConfiguration.class)
public class SwaggerResourcesController {
    private Map<String, SwaggerResourceClient> clients = Maps.newConcurrentMap();

    private Decoder decoder;
    private Encoder encoder;
    private Client client;
    private Contract contract;

    // See: https://cloud.spring.io/spring-cloud-static/Greenwich.SR3/single/spring-cloud.html#_creating_feign_clients_manually
    @Autowired
    public SwaggerResourcesController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.client = client;
        this.contract = contract;
    }


    @GetMapping("/swagger-resources/{instance}")
    public String resources(@PathVariable("instance") String instance) {
        if (!clients.containsKey(instance)) {
            SwaggerResourceClient swaggerResourceClient  = Feign.builder().client(client)
                    .encoder(encoder)
                    .decoder(decoder)
                    .contract(contract)
                    .target(SwaggerResourceClient.class, "http://" + instance);
            clients.put(instance, swaggerResourceClient);
        }

        return clients.get(instance).getApiDocs();
    }
}
