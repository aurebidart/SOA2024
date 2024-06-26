package ar.edu.iua.iw3.backend.auth;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.business.Exceptions.NotFoundException;

public interface IUserBusiness {
	
	public List<User> list() throws  BusinessException;

	
	public User load(String usernameOrEmail) throws NotFoundException, BusinessException;

	public void changePassword(String usernameOrEmail, String oldPassword, String newPassword, PasswordEncoder pEncoder)
			throws BadPasswordException, NotFoundException, BusinessException;

	public void disable(String usernameOrEmail) throws NotFoundException, BusinessException;

	public void enable(String usernameOrEmail) throws NotFoundException, BusinessException;

}
