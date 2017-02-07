package mvillalobos.presentations.spring.boot.kitchen.api.services;

import mvillalobos.presentations.spring.boot.kitchen.api.model.Message;

public interface MessageService {
	Message createOneMessage(String message);
}
