package br.com.macielgoncalves.quarkussocial.model.repository;

import br.com.macielgoncalves.quarkussocial.model.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
