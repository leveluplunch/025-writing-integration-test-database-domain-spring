package com.levelup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
			scripts = "classpath:beforeScripts.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, 
	scripts = "classpath:afterScripts.sql") })
public class ApplicationTests {

	@Autowired
	private PersonDAO personDAO;
	
	@Test
	public void should_update_password(){ 
		
		Person person = new Person();
		person.setPersonId(1);
		person.setPassword("wilma");
		
		personDAO.updatePassword(person);
		
		//validate update occurred
		Person updatedPerson = personDAO.getPerson(1);
		assertEquals("wilma", updatedPerson.getPassword());
		
		
	}
	
}
