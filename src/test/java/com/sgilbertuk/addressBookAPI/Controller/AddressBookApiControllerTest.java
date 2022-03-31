package com.sgilbertuk.addressBookAPI.Controller;

import com.sgilbertuk.addressBookAPI.Excpetions.UserNotFoundException;
import com.sgilbertuk.addressBookAPI.Model.User;
import com.sgilbertuk.addressBookAPI.Repository.AddressBookApiRepository;
import com.sgilbertuk.addressBookAPI.Service.AddressBookApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AddressBookApiControllerTest {

    //TODO: Assert HTTP status

    @Mock
    private AddressBookApiRepository apiRepository;

    @Mock
    private AddressBookApiService apiService;

    @InjectMocks
    private final AddressBookApiController addressController = new AddressBookApiController();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_validLastName_andLastNamePresent_whenGetEndpointCalled_Expect200() throws Exception {
        // Given
        User user =  new User();
        user.setLastName("lovelace");
        // When
        when(apiRepository.findByLastName(user.getLastName())).thenReturn(List.of(user));
        when(apiService.getName(user.getLastName())).thenReturn(List.of(user));
        when(addressController.getUser(user.getLastName())).thenReturn(List.of(user));
        // Then
        assertThat(addressController.getUser(user.getLastName())).contains(user);
    }

    @Test
    void given_validLastName_andLastNameNotPresent_whenGetEndpointCalled_Expect404(){
        // Given
        User user =  new User();
        user.setLastName("lovelace");
        // When
        when(apiRepository.findByLastName("smith")).thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class, () -> apiRepository.findByLastName("smith"),
                "No user with last name smith was found");
    }
}
