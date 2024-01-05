package com.jeunice.iBuild.service;

import com.jeunice.iBuild.Utils.BasicResponse;
import com.jeunice.iBuild.dto.AppUserDTO;
import com.jeunice.iBuild.dto.ResetPasswordDTO;
import com.jeunice.iBuild.model.AppUser;
import com.jeunice.iBuild.model.PasswordToken;
import com.jeunice.iBuild.model.Verification;
import com.jeunice.iBuild.repository.AppUserRepository;
import com.jeunice.iBuild.repository.PasswordCodeRepository;
import com.jeunice.iBuild.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor

public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private PasswordCodeRepository passwordCodeRepository;

    @Autowired
    private SendMail sendMail;

//    @Autowired
//    private final PasswordEncoder passwordEncoder;

    public BasicResponse getAll() {
        List<AppUser> users = appUserRepository.findAll();

        return BasicResponse.success(users);
    }

    public BasicResponse signUp(AppUserDTO appUserDTO) {

        Optional<AppUser> user = appUserRepository.findAppUserByEmail(appUserDTO.getEmail());
        if(user.isPresent()){
            return BasicResponse.ofSuccess("User exists");
        }
        AppUser appUser = new AppUser(
                appUserDTO.getEmail(),
                appUserDTO.getFirstName(),
                appUserDTO.getLastName(),
                appUserDTO.getLevels(),appUserDTO.getPhoneNumber() );
//        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        appUserRepository.save(appUser);
        return confirm(appUser);

    }

    private BasicResponse confirm(AppUser appUser){

        Random random = new Random();
        int sCode = random.nextInt(10000);
        String code = String.format("%04d",  sCode);

        Verification verification = new Verification(code,
                LocalDateTime.now().plusMinutes(10),
                appUser
                );

        verificationRepository.save(verification);
        sendMail.send(appUser.getEmail(), buildEmail(appUser.getFirstName(), "http://localhost:8080/confirm/?code="+code));
        return BasicResponse.success(code);
    }

    public BasicResponse confirmCode(String code) {

        Optional<Verification> optional =  verificationRepository.findByCode(code);
        if (optional.isEmpty()) {

            return  BasicResponse.ofSuccess("Code does not exist!");
        }

        if (optional.get().getExpiredAt().isBefore(LocalDateTime.now())) {
            return BasicResponse.ofSuccess("Code has expired");
        }

        Verification user = optional.get();
        user.setConfirmedAt(LocalDateTime.now());
        user.getAppUser().setEnabled(true);
        verificationRepository.save(user);
        return BasicResponse.ofSuccess("Account Enabled!");

    }

    public BasicResponse resendCode(String email) {

        Optional<AppUser> user = appUserRepository.findAppUserByEmail(email);
        if(user.isEmpty()){
            return BasicResponse.failure("User does not exist");
        }

        return  confirm(user.get());
    }

    public BasicResponse sendPassCode(String email){
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(email);
        if (optionalAppUser.isEmpty()){
            return BasicResponse.failure("User does not exist");
        }

        Random random = new Random();
        int sCode = random.nextInt(10000);
        String code = String.format("%04d",  sCode);

        PasswordToken passwordToken = new PasswordToken(
                LocalDateTime.now().plusMinutes(10),code,optionalAppUser.get()
        );

        passwordCodeRepository.save(passwordToken);
        sendMail.send(email, "Your reset token is " + code);
        return BasicResponse.ofSuccess("Reset code sent");
    }

    public BasicResponse saveNewPassword(ResetPasswordDTO resetPasswordDTO){

        Optional<PasswordToken> userWithCode = passwordCodeRepository.checkCode(resetPasswordDTO.getCode());
        if(userWithCode.isEmpty()){
            return BasicResponse.failure("code does not exist");
        }

         if(userWithCode.get().getExpiresAt().isBefore(LocalDateTime.now())){
             return BasicResponse.failure("code has expired!");
         }

         AppUser updatedUser = userWithCode.get().getAppUser();
                 updatedUser.setPassword(resetPasswordDTO.getPassword());

                  appUserRepository.save(updatedUser);
                  return BasicResponse.ofSuccess("Password updated!");

//         PasswordToken token = userWithCode.get();
//                 token.getAppUser().setPassword(resetPasswordDTO.getPassword());

    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0B0C0C\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0B0C0C\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #B1B4B6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
