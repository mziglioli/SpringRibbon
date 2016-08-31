package com.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.model.User;
import com.server.service.UserService;
import com.server.util.Catalago;

@RestController
@RequestMapping(value = Catalago.URL_USER)
public class UserController extends RestControllerDefault<User, UserService> {

}