package org.example.UsersTest;

import appointment.Appointment;
import appointment.AppointmentRepository;
import appointment.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import users.User;
import users.UserBD;
import users.UserRepository;
import users.UserService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private User user;
    @BeforeEach
    void setUp() {
        // Configuración previa al test
        userService = new UserService(userRepository);
        user = new User("password", "user@email.com");

    }

    @Test
    void saveUser() {
        Mockito.lenient().when(userRepository.saveUserR(1,user)).thenReturn(
                new User("password", "user@email.com"));

        User userResultado = userService.saveUser(1,user);

        Assertions.assertEquals(user, userResultado);
    }

    @Test
    void getUser() {
        userService.saveUser(1,user);

        Mockito.when(userRepository.getUserR(1)).thenReturn(user);

        User userResultado = userService.getUser(1);
        Assertions.assertEquals(user,userResultado);
    }

    @Test
    void updateUser() {
        userService.updateUser(1, user);

        User userActualizado =
                new User("password","email@email.com");
        userService.updateUser(1, userActualizado);

        User userActualizadoObtenido = userService.getUser(1);
        Mockito.lenient().when(userRepository.updateUserR(1,userActualizado)).thenReturn(userActualizadoObtenido);
    }

    @Test
    void deteleUser() {
        userService.saveUser(1,user);

        Mockito.when(userRepository.deleteUserR(user)).thenReturn(user);
        boolean result = userService.deteleUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).deleteUserR(user);
        assertTrue(result);
    }

}