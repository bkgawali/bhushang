package src.main.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import src.main.java.model.Author;
import src.main.java.model.AuthorRepository;

@RestController
public class AuthorController {

	@Autowired
	AuthorRepository authorRepo;
//test change
	
	@RequestMapping(value = "/authors", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE, })
	public List<Author> getauthors() {
		return (List<Author>) authorRepo.findAll();

	}

	@RequestMapping(value = "/authors", method = RequestMethod.POST)
	public ResponseEntity<Author> createauthor(@RequestBody Author authorToBeCreated) {

		Author newlyCreatedauthor = authorRepo.save(authorToBeCreated);
		ResponseEntity<Author> response = new ResponseEntity<Author>(newlyCreatedauthor, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/authors/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Author> updateauthor(@PathVariable(name = "id") Long id, @RequestBody Author authorToBeUpdated) {

		if (authorRepo.existsById(id)) {
			authorToBeUpdated.setId(id);
			Author updatedauthor = authorRepo.save(authorToBeUpdated);
			ResponseEntity<Author> response = new ResponseEntity<Author>(updatedauthor, HttpStatus.ACCEPTED);
			return response;
		}
		else {
			ResponseEntity<Author> response = new ResponseEntity<Author>(authorToBeUpdated, HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteauthor(@PathVariable(name = "id") Long id) {

		if (authorRepo.existsById(id)) {	
			authorRepo.deleteById(id);
			ResponseEntity<String> response = new ResponseEntity<String>("author Deleted", HttpStatus.OK);
			return response;
		}
		else {
			ResponseEntity<String> response = new ResponseEntity<String>("author not found", HttpStatus.NOT_FOUND);
			return response;
		}
	}

	
	


}
