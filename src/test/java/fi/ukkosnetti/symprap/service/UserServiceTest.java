package fi.ukkosnetti.symprap.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.model.User;
import fi.ukkosnetti.symprap.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private LombokMapper mapper;
	
	@Mock
	private UserRepository repo;
	
	@InjectMocks
	private UserService service;
	
	@Test
	public void createsUser() {
		when(repo.save(any(User.class))).thenReturn(new User());
		when(mapper.convertValue(any(UserCreate.class), eq(User.class))).thenReturn(new User());
		when(mapper.convertValue(any(User.class), eq(UserGet.class))).thenReturn(Mockito.mock(UserGet.class));
		UserGet user = service.createUser(new UserCreate("testUser", "Tom", "Bombadil", new Date(0), 321424412l));
		assertNotNull(user);
	}
}
