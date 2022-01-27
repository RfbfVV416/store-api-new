package org.store_api_new.service;

import org.store_api_new.model.StoreUser;
import javax.servlet.http.HttpSession;

public interface UserService {
    StoreUser register(StoreUser credentialsDto);
    StoreUser getUserBySessionEmail(HttpSession httpSession);
}
