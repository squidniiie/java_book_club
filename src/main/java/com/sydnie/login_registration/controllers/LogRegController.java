package com.sydnie.login_registration.controllers;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sydnie.login_registration.models.Book;
import com.sydnie.login_registration.models.Login;
import com.sydnie.login_registration.models.User;
import com.sydnie.login_registration.services.BookService;
import com.sydnie.login_registration.services.UserService;

@Controller
public class LogRegController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new Login());
		return "/user/index.jsp";
	}
	
//------------------------------New User:--------------------------------
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
		userService.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new Login());
			return "/user/index.jsp";
		}
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/user/dashboard";
	}
	
// Login User:
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") Login newLogin, BindingResult result, Model model,
			HttpSession session) {
		User user = userService.login(newLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "/user/index.jsp";
		}
		session.setAttribute("user_id", user.getId());
		return "redirect:/user/dashboard";
	}
	
//	Successful login:
	@GetMapping("/user/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userID = (Long) session.getAttribute("user_id");
		List<Book> books = bookService.allBooks();
		if (userID == null) {
			return "redirect:/";
		} else {			
			User thisUser = userService.findOne(userID);
			model.addAttribute("name", thisUser.getUserName());
			model.addAttribute("books", books);
			model.addAttribute("borrowBooks", userService.findOne(userID).getBorrowBooks());
			return "/user/dashboard.jsp";
		}
	}
//	Logout:
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}


// --------------------------------BOOKS-------------------------------------------
//	Create book method:
@GetMapping("/new")
public String newBook(@ModelAttribute("newBook") Book book, HttpSession session, Model model) {
	Long user = (Long) session.getAttribute("user_id");
	User thisUser = userService.findOne(user);
	model.addAttribute("allBooks", bookService.allBooks());
	model.addAttribute("user", thisUser);
	return "/books/new_book.jsp";
}

@PostMapping("/new")
public String create(@Valid @ModelAttribute("newBook") Book book, BindingResult result, Model model) {
	if (result.hasErrors()) {
		model.addAttribute("allUsers", userService.allUsers());
		return "/books/new_book.jsp";
	} else {
		bookService.createBook(book);
		return "redirect:/user/dashboard";
	}
}

//Retrieve one book:
@RequestMapping("/books/{id}")
public String retrieveOne(@PathVariable("id") Long id, Model model) {
// retrieve the logged in user, the id stored in session, and grab the user id the pass it over.
	Book oneBook = bookService.findBook(id);
	model.addAttribute("oneBook", oneBook);
	return "books/show.jsp";
}
//Retrieve all books page:
@RequestMapping("/books")
public String getAll(Model model) {
	List<Book> bookList = bookService.allBooks();
	model.addAttribute("books", bookList);
	return "/books/show.jsp";
}

////Update book:
@RequestMapping("/books/{id}/edit")
public String edit(@PathVariable("id") Long id, HttpSession session, Model model) {
	Long userId = (Long) session.getAttribute("user_id");
	if (id == null) {
		System.out.println(userId);
		return "redirect:/books";
		
//		comparing user id to session to the book's user id
	} else {
		Book oneBook = bookService.findBook(id);
		Long creatorId = oneBook.getUser().getId();
		model.addAttribute("book", oneBook);
		System.out.println(creatorId);
		return "/books/edit.jsp";
	}
	
}

@RequestMapping(value="/books/{id}/edit", method=RequestMethod.PUT)
public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
	if (result.hasErrors()) {
		return "/books/edit.jsp";
		
	} else {
		bookService.update(book);
		return "redirect:/user/dashboard";
	}

}
//Delete book:
@RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
public String delete(@PathVariable("id") Long id) {
	bookService.deleteBook(id);
	return "redirect:/user/dashboard";
}

// Borrowed book:

// find one borrowed book:
@GetMapping("/books/borrow/{id}")
public String borrow(@PathVariable("id") Long bookId, HttpSession session) {
	Long userId = (Long) session.getAttribute("user_id");
	Book bookBorrowed = bookService.findBook(bookId);
	if(userId == null) {
		return"redirect:/user/dashboard";
	} else {
		bookBorrowed.setBorrowUser(userService.findOne(userId));
		bookService.update(bookBorrowed);
		return "redirect:/user/dashboard";
	}
}
// Return book:

@GetMapping("/books/return/{id}")
public String returnBook(@PathVariable("id") Long id, HttpSession session) {
	Long userId = (Long) session.getAttribute("user_id");
	Book bookReturned = bookService.findBook(id);
	if(userId == null) {
		return "redirect:user/index";
//	}
//	if(userId != bookReturned.getUser().getId()) {
//		return "redirect:/books";
	} else {
		bookReturned.setBorrowUser(null);
		bookService.update(bookReturned);
		return "redirect:/user/dashboard";
	}
	
}

}

	


