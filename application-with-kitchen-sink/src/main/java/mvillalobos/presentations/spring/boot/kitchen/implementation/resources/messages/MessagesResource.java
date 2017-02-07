package mvillalobos.presentations.spring.boot.kitchen.implementation.resources.messages;

import mvillalobos.presentations.spring.boot.kitchen.api.model.Message;
import mvillalobos.presentations.spring.boot.kitchen.api.resources.messages.io.CreateOneMessageRequest;
import mvillalobos.presentations.spring.boot.kitchen.api.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path("messages")
public class MessagesResource {

	private final MessageService messageService;

	@Autowired
	public MessagesResource(MessageService messageService) {
		this.messageService = messageService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOneMessage(CreateOneMessageRequest createOneMessageRequest) {
		final Message oneMessage = messageService.createOneMessage(createOneMessageRequest.getMessage());
		return Response.ok(oneMessage).build();
	}
}
