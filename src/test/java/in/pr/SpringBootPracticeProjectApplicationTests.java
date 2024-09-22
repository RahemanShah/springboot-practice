package in.pr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import in.pr.Entity.User;
import in.pr.Repository.UserRepository;
import in.pr.Service.IMPL.UserDtoImpl;
import in.pr.Service.RestController.Exveption.UnAuthorizedException;

@SpringBootTest
class SpringBootPracticeProjectApplicationTests {


    @InjectMocks
    private UserDtoImpl userDtoImpl;

    @Mock
    private UserRepository userRepo;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setAbout("About John");
    }

    @Test
    void testCreateUser() {
        when(userRepo.save(any(User.class))).thenReturn(user);
        
        User createdUser = userDtoImpl.createUser(user);
        
        assertEquals("John Doe", createdUser.getName());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testGetOneUser() {
        // Arrange
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        
        // Act
        User fetchedUser = userDtoImpl.getOneUser(1);
        
        // Assert
        assertEquals("John Doe", fetchedUser.getName());
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    void testGetOneUserNotFound() {
        // Arrange
        when(userRepo.findById(1)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(UnAuthorizedException.class, () -> {
            userDtoImpl.getOneUser(1);
        });
        verify(userRepo, times(1)).findById(1);
    }
    @Test
    void testDeleteUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        
        userDtoImpl.deleteUser(1);
        
        verify(userRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepo.findById(1)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(UnAuthorizedException.class, () -> {
            userDtoImpl.deleteUser(1);
        });
        
        assertEquals("user id not found: 1", exception.getMessage());
    }

    @Test
    void testGetAllUsers() {
        when(userRepo.findAll()).thenReturn(List.of(user));
        
        List<User> userList = userDtoImpl.GetAllUser();
        
        assertEquals(1, userList.size());
        assertEquals("John Doe", userList.get(0).getName());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenReturn(user);
        
        user.setName("Jane Doe");
        User updatedUser = userDtoImpl.updateUser(user);
        
        assertEquals("Jane Doe", updatedUser.getName());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepo.findById(1)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(UnAuthorizedException.class, () -> {
            userDtoImpl.updateUser(user);
        });
        
        assertEquals("user id not found: 1", exception.getMessage());
    }

    @Test
    void testIsUserExists() {
        when(userRepo.existsById(1)).thenReturn(true);
        
        assertTrue(userDtoImpl.isUserExists(1));
        verify(userRepo, times(1)).existsById(1);
    }

}
