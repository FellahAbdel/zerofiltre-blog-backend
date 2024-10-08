package tech.zerofiltre.blog.domain.course.features.enrollment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.zerofiltre.blog.domain.course.CertificateProvider;
import tech.zerofiltre.blog.domain.course.CourseProvider;
import tech.zerofiltre.blog.domain.course.EnrollmentProvider;
import tech.zerofiltre.blog.domain.course.model.Certificate;
import tech.zerofiltre.blog.domain.error.ZerofiltreException;
import tech.zerofiltre.blog.domain.user.model.User;
import tech.zerofiltre.blog.util.ZerofiltreUtils;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GenerateCertificateTest {

    private User user;

    @Mock
    private EnrollmentProvider enrollmentProvider;

    @Mock
    private CourseProvider courseProvider;

    @Mock
    CertificateProvider certificateProvider;


    private GenerateCertificate generateCertificate;

    @BeforeEach
    void init() {
        generateCertificate = new GenerateCertificate(enrollmentProvider, certificateProvider);

        user = new User();
        user.setId(1L);
        user.setFullName("FirstName LastName");
        user.setLanguage("fr");

    }

    @Test
    void getCertificate_whenEnrollment_Is_Completed() throws IOException, ZerofiltreException {
        //given
        String courseTitle = "title course 3";
        String fileName = ZerofiltreUtils.sanitizeString(user.getFullName()) + "-" + ZerofiltreUtils.sanitizeString(courseTitle) + ".pdf";
        byte[] content = {1, 2};

        when(enrollmentProvider.isCompleted(anyLong(), anyLong())).thenReturn(true);
        doNothing().when(enrollmentProvider).setCertificatePath(any(), anyLong(), anyLong());
        when(certificateProvider.get(any(), anyLong())).thenReturn(new Certificate(fileName, content));

        //when
        Certificate response = generateCertificate.get(user, 2L);

        //then
        verify(enrollmentProvider, times(1)).isCompleted(anyLong(), anyLong());
        assertThat(response.getName()).isEqualTo(fileName);
        assertThat(response.getContent()).isEqualTo(content);
    }

    @Test
    void throwZerofiltreException_whenCompletedEnrollmentIsFalse() {
        //given
        when(enrollmentProvider.isCompleted(anyLong(), anyLong())).thenReturn(false);
        when(courseProvider.getTitle(anyLong())).thenReturn("title course 3");

        //then
        Assertions.assertThatExceptionOfType(ZerofiltreException.class)
                .isThrownBy(() -> generateCertificate.get(user, 2L));
    }


}