package com.example.demo.web;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Product;
import com.example.demo.exceptions.NoSessionFoundException;
import com.example.demo.exceptions.SessionTimeOutException;
import com.example.demo.services.AdminFacade;



@RestController
@RequestMapping("admin")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class AdminController {

	
	@Autowired
	private Map<String, Session> sessions;
	
	@GetMapping("getAllItems/{token}")
	public ResponseEntity<?> getAllItems(@PathVariable String token){
		try {
			AdminFacade admin = (AdminFacade) checkSession(token).getFacade();
			return ResponseEntity.ok(admin.getAllItems());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("getItemByNo/{itemNo}/{token}")
	public ResponseEntity<?> getItemByNo(@PathVariable String token, @PathVariable int itemNo){
		try {
			AdminFacade admin = (AdminFacade) checkSession(token).getFacade();
			return ResponseEntity.ok(admin.getItemByNo(itemNo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("withdrawItems/{itemNo}/{amount}/{token}")
	public ResponseEntity<?> withdrawItems(@PathVariable String token, @PathVariable int itemNo, @PathVariable int amount ){
		try {
			AdminFacade admin = (AdminFacade) checkSession(token).getFacade();
			admin.withdrawItems(itemNo, amount);
			return ResponseEntity.ok(admin.getItemByNo(itemNo).getTitle()+" Stock Updated !");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("depositItems/{itemNo}/{amount}/{token}")
	public ResponseEntity<?> depositItems(@PathVariable String token, @PathVariable int itemNo, @PathVariable int amount ){
		try {
			AdminFacade admin = (AdminFacade) checkSession(token).getFacade();
			admin.depositItems(itemNo, amount);
			return ResponseEntity.ok(admin.getItemByNo(itemNo).getTitle()+" Stock Updated !");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("addItem/{token}")
	public ResponseEntity<?> addItem(@PathVariable String token, @RequestBody Product item){
		try {
			System.out.println(item);
			AdminFacade admin = (AdminFacade) checkSession(token).getFacade();
			admin.addItem(item);
			return ResponseEntity.ok(item.getTitle()+" was added !");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("deleteItem/{itemNo}/{token}")
	public ResponseEntity<?> deleteItem(@PathVariable String token, @PathVariable int itemNo){
		try {
			AdminFacade admin = (AdminFacade) checkSession(token).getFacade();
			String title = admin.getItemByNo(itemNo).getTitle();
			admin.deleteItem(itemNo);;
			return ResponseEntity.ok(title+" was deleted.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	public Session checkSession(String token) throws NoSessionFoundException, SessionTimeOutException {
		Session session = sessions.get(token);
		if (session == null) {
			throw new NoSessionFoundException();
		}
		if (System.currentTimeMillis() - session.getLastAccessed() > 1000 * 60 * 30) {
			sessions.remove(token);
			throw new SessionTimeOutException();
		}
		session.setLastAccessed(System.currentTimeMillis());
		return session;
	}
	
	

}
