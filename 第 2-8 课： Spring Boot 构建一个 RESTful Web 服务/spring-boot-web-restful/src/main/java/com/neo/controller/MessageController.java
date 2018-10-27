
package com.neo.controller;

import com.neo.model.Message;
import com.neo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {

	@Autowired
	private  MessageRepository messageRepository;

	@GetMapping(value = "messages")
	public List<Message> list() {
		List<Message> messages = this.messageRepository.findAll();
		return messages;
	}

	@PostMapping(value = "message")
	public Message create(Message message) {
		message = this.messageRepository.save(message);
		return message;
	}

	@PutMapping(value = "message")
	public Message modify(Message message) {
		Message messageResult=this.messageRepository.update(message);
		return messageResult;
	}

	@PatchMapping(value="/message/text")
	public Message patch(Message message) {
		Message messageResult=this.messageRepository.updateText(message);
		return messageResult;
	}

	@GetMapping(value = "message/{id}")
	public Message get(@PathVariable Long id) {
		Message message = this.messageRepository.findMessage(id);
		return message;
	}

	@DeleteMapping(value = "message/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteMessage(id);
	}



}
