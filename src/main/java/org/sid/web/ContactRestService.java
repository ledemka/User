package org.sid.web;

import java.util.List;

import org.sid.dao.ContactRepository;
import org.sid.entitie.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class ContactRestService {

	@Autowired
	private ContactRepository contactRepository;
	//par defaut il renvoie un format json
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public List<Contact> getContacts(){
		return  contactRepository.findAll();
	}
	//jawrs pathparent   spring pathVariable
	@RequestMapping(value="/contact/{id}", method=RequestMethod.GET)
	public Contact detailContact(@PathVariable Long id) {
		return contactRepository.findById(id).orElse(null);
		
	}
	
	@RequestMapping(value="/contact/{id}", method=RequestMethod.POST)
	public Contact saveContact(@RequestBody Contact c) {
		return contactRepository.save(c);
		
	}
	
	@RequestMapping(value="/contact/{id}", method=RequestMethod.DELETE)
	public void deleteContact(@RequestBody Long id) {
		 contactRepository.deleteById(id);
		
	}
	
	@RequestMapping(value="/contact/{id}", method=RequestMethod.PUT)
	public Contact editContact(@PathVariable Long id,@RequestBody Contact c) {
		c.setId(id);
		return contactRepository.save(c);
		
	}	@RequestMapping(value="/chercher", method=RequestMethod.GET)
	public Page<Contact> chercherContacts(
			@RequestParam (name="motCle",defaultValue="")String motCle,
			@RequestParam (name="numPage",defaultValue="0")int numPage,
			@RequestParam (name="taillePage",defaultValue="5")int taillePage){
		//quelque soit ce qui vient avant et apres le mot clé en parametre il prendra le mot clé
		return  contactRepository.chercher("%"+motCle+"%",PageRequest.of(numPage,taillePage));
	}
	
}
