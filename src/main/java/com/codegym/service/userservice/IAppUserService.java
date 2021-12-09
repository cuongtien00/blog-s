package com.codegym.service.userservice;

import com.codegym.model.AppUser;

public interface IAppUserService {
    AppUser getUserByUsername(String name);
}
