package com.example.demo.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Product;
import com.example.demo.exceptions.LoginException;
import com.example.demo.exceptions.SupplyException;
import com.example.demo.exceptions.itemNotFoundException;

@Service
@Scope("prototype")
public class AdminFacade extends UserFacade {

	@Override
	public boolean login(String email, String password) throws LoginException {
		if (email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin")) {

			return true;

		} else
			throw new LoginException();
	}

	public List<Product> getAllItems() {
		return stock.findAll();
	}

	public Product getItemByNo(int itemNo) throws itemNotFoundException {
		return stock.findById(itemNo).orElseThrow(itemNotFoundException::new);
	}

	public void withdrawItems(int itemNo, int amountToWithdraw) throws SupplyException, itemNotFoundException {
		Product pr = stock.findById(itemNo).orElseThrow(itemNotFoundException::new);
		if (pr.getAmount() <= 0)
			throw new SupplyException();
		if (pr.getAmount() < amountToWithdraw)
			throw new SupplyException(pr.getAmount());
		pr.setAmount(pr.getAmount() - amountToWithdraw);
		stock.save(pr);
	}

	public void depositItems(int itemNo, int amountToDeposit) throws itemNotFoundException{
		Product pr = stock.findById(itemNo).orElseThrow(itemNotFoundException::new);
		pr.setAmount(pr.getAmount() + amountToDeposit);
		stock.save(pr);
	}

	public void addItem(Product item)  {
		stock.save(item);
	}

	public void deleteItem(int itemNo) {
		stock.deleteById(itemNo);
	}
	


}
