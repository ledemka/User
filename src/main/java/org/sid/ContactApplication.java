package org.sid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sid.dao.ContactRepository;
import org.sid.entitie.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactApplication  implements CommandLineRunner{
	

	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args){
		
		SpringApplication.run(ContactApplication.class, args);
	}
	
	public void run(String ... arg0) throws Exception{
		//tester si mon DAO marche
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactRepository.save(new Contact((long)1, "KAMDEM","Roger",df.parse("12/03/2015"),"ledemka@yahoo.fr",(long)783189929,"tatatat"));
		contactRepository.save(new Contact((long)2, "HASSAN","Diego",df.parse("15/04/2020"),"Iv.tankeu@yahoo.fr",(long)783159824,"babababa"));
		contactRepository.findAll().forEach(c->{
			System.out.println(c.getNom());
		});
	}

}
