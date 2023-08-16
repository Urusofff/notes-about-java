package vk.com.notes_about_java.reactor_mono_block_deadlock;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import vk.com.notes_about_java.client.Client;
import vk.com.notes_about_java.client.ClientGroup;
import vk.com.notes_about_java.client.ClientGroupsCount;
import vk.com.notes_about_java.client.service.ClientService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Log
public record ReactorMonoBlockDeadlock(ClientService clientService) {

    // https://habr.com/en/articles/741464/
    public void groupClientsInFlux() {

        Flux<Client> clientsFlux = Flux.fromIterable(clientService.getClientsSet());

        clientsFlux
                .groupBy(Client::groupId)
                .map(clientGroupedFlux -> clientGroupedFlux // replace to flatMap to avoid deadlock !
                        .reduce(new ClientGroup(1, "credit", new HashSet<>()),
                                (creditClientGroup, newClient) -> {
                                    if (creditClientGroup.id().equals(newClient.groupId())) {
                                        Set<Client> clients = creditClientGroup.clients();
                                        clients.add(newClient);
                                        return new ClientGroup(creditClientGroup.id(),
                                                creditClientGroup.name(),
                                                clients);
                                    }
                                    return creditClientGroup;
                                })
                )// <-- Flux<Mono<ClientGroup>> if run on map function
                .reduce(new ClientGroupsCount(1, 0),
                        (clientsCount, newClientGroup) -> {
                            Integer id = Objects.requireNonNull(newClientGroup).id(); // <-- DEADLOCK on block in map!!!
                            if (id.equals(clientsCount.groupId())) {
                                Integer count = clientsCount.countOfClients();
                                int i = count + Objects.requireNonNull(newClientGroup).clients().size();
                                return new ClientGroupsCount(id, i);
                            }
                            return clientsCount;
                        })
                .subscribe(System.out::println);

    }
}
