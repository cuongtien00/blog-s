package com.codegym.repository;

import com.codegym.model.AppRole;
import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends CrudRepository<AppUser,Long> {
    AppUser findByName(String name);
}
