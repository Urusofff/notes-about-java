package vk.com.notes_about_java.client;

import lombok.Getter;

import java.util.Set;

public record ClientGroup(Integer id, String name, Set<Client> clients) {

}
