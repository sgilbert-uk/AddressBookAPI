package com.sgilbertuk.addressBookAPI.Controller;

import com.sgilbertuk.addressBookAPI.Excpetions.UserNotFoundException;
import com.sgilbertuk.addressBookAPI.Model.User;
import com.sgilbertuk.addressBookAPI.Repository.AddressBookApiRepository;
import com.sgilbertuk.addressBookAPI.Service.AddressBookApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookApiControllerTest {

    @Mock
    private AddressBookApiRepository apiRepository;

    @Mock
    private AddressBookApiService apiService;

    @InjectMocks
    private final AddressBookApiController addressController = new AddressBookApiController();

    public User user = new User();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        user.setLastName("lovelace");
    }

    // Note: These tests need renaming

    @Test
    void given_validLastName_andLastNamePresent_whenGetEndpointCalled_Expect200() throws Exception { // needs a rename
        // Given/When
        when(apiService.getName(user.getLastName())).thenReturn(List.of(user));
        List<User> actual = addressController.getUser(user.getLastName());
        // Then
        verify(apiService,times(1)).getName(user.getLastName());
        assertThat(actual).isEqualTo(List.of(user));
    }

    @Test
    void given_validLastName_andLastNameNotPresent_whenGetEndpointCalled_Expect404() { // needs a rename
        // Given
        String wrongName = "smith";
        // When
        when(apiService.getName(wrongName)).thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class, () -> apiService.getName(wrongName),
                "No user with last name smith was found");
    }
}
