package vk.com.notes_about_java.client.service;

import org.springframework.stereotype.Service;
import vk.com.notes_about_java.client.Client;

import java.util.Set;

@Service
public class ClientService {

    public Set<Client> getClientsSet() {

        Client client1 = new Client(1, 1, "Михалыч");
        Client client2 = new Client(2, 1, "Иваныч");

        return Set.of(client1, client2);
    }

}
