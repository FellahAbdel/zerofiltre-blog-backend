package tech.zerofiltre.blog.domain.user.use_cases;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit.jupiter.*;
import tech.zerofiltre.blog.domain.user.*;
import tech.zerofiltre.blog.domain.user.model.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class VerifyTokenTest {

    public static final String TOKEN = "token";
    @MockBean
    VerificationTokenProvider verificationTokenProvider;


    VerifyToken verifyToken;

    User user = new User();
    VerificationToken verificationToken = new VerificationToken(user, TOKEN);


    @BeforeEach
    void setUp() {
        verifyToken = new VerifyToken(verificationTokenProvider);
    }

    @Test
    void onValidToken_setUserActive_thenSave() throws InvalidTokenException {
        //ARRANGE
        when(verificationTokenProvider.ofToken(TOKEN)).thenReturn(java.util.Optional.ofNullable(verificationToken));

        //ACT
        verifyToken.execute(TOKEN);

        //ASSERT
        verify(verificationTokenProvider, times(1)).ofToken(TOKEN);

    }

    @Test
    void onTokenExpired_ThrowInvalidTokenException_withProperMessage() {
        //ARRANGE
        verificationToken.setExpiryDate(LocalDateTime.now());
        when(verificationTokenProvider.ofToken(TOKEN)).thenReturn(java.util.Optional.ofNullable(verificationToken));

        //ACT & ASSERT
        assertThatExceptionOfType(InvalidTokenException.class).isThrownBy(() -> verifyToken.execute(TOKEN))
                .withMessage(ConfirmUserRegistration.TOKEN_EXPIRED);

    }

    @Test
    void onTokenNotFound_ThrowInvalidTokenException_withProperMessage() {
        //ARRANGE
        when(verificationTokenProvider.ofToken(TOKEN)).thenReturn(Optional.empty());

        //ACT & ASSERT
        assertThatExceptionOfType(InvalidTokenException.class).isThrownBy(() -> verifyToken.execute(TOKEN))
                .withMessage(ConfirmUserRegistration.INVALID_TOKEN);

    }
}