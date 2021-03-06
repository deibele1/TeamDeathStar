package dev.deathstarreactorcore.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import dev.deathstarreactorcore.annotations.AuthenticatedUser;
import dev.deathstarreactorcore.beans.BasicEventInfo;
import dev.deathstarreactorcore.rawTypes.RawEvent;
import dev.deathstarreactorcore.services.EventService;

@RestController
public class EventController {
    @Autowired
    EventService es;

    @AuthenticatedUser
    @RequestMapping(value = "/event/add", method = RequestMethod.POST, consumes = "application/json")
    public BasicEventInfo add(@RequestBody RawEvent evt, @RequestHeader(value = "username") String username) {
        return es.save(evt,username);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getFutureEvents() {
		return es.findByAdventGreaterThan(Date.valueOf(LocalDate.now()));
    }

    @RequestMapping(value = "/past", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getPastEvents() {
		
    	return es.passedEvents();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getAllEvents() {
		
    	return es.getAll();
    }
    
    @RequestMapping(value = "/30day", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getEventsWithin30Days() {

    	
    	LocalDate today = LocalDate.now();
    	LocalDate future = LocalDate.now();
    	future = future.plusDays(30);
    	
    	return es.findByAdventBetween(Date.valueOf(today), Date.valueOf(future));
    }
    
    @RequestMapping(value = "/60day", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getEventsWithin60Days() {

    	
    	LocalDate today = LocalDate.now();
    	LocalDate future = LocalDate.now();
    	future = future.plusDays(60);
    	
    	return es.findByAdventBetween(Date.valueOf(today), Date.valueOf(future));
    }
    
    @RequestMapping(value = "/90day", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getEventsWithin90Days() {

    	
    	LocalDate today = LocalDate.now();
    	LocalDate future = LocalDate.now();
    	future = future.plusDays(90);
    	
    	return es.findByAdventBetween(Date.valueOf(today), Date.valueOf(future));
    }
    
    @RequestMapping(value = "/1", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getFirstCategory() {
    	
    	return es.getAllbyCatagory(1);
    }
    
    @RequestMapping(value = "/2", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getSecondCategory() {
    	
    	return es.getAllbyCatagory(2);
    }
    
    @RequestMapping(value = "/3", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getThirdCategory() {
    	
    	return es.getAllbyCatagory(3);
    }
    
    @RequestMapping(value = "/4", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getFourthCategory() {
    	
    	return es.getAllbyCatagory(1);
    }
    
    @RequestMapping(value = "/5", method = RequestMethod.GET, produces = "application/json")
    public LinkedList<BasicEventInfo> getFifthCategory() {
    	
    	return es.getAllbyCatagory(5);
    }
}