package com.uwu.tas.service.authService;

import com.uwu.tas.config.security.custom.CustomOauthException;
import com.uwu.tas.dto.auth.CommonUserDetails;
import com.uwu.tas.dto.auth.UserAuthDto;
import com.uwu.tas.entity.Admin;
import com.uwu.tas.entity.PublicUser;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.enums.UserRole;
import com.uwu.tas.enums.UserStatus;
import com.uwu.tas.repository.AdminRepository;
import com.uwu.tas.repository.PublicUserRepository;
import com.uwu.tas.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.uwu.tas.config.security.SecurityConstants.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final PublicUserRepository publicUserRepository;
    private final AdminRepository adminRepository;
    private final VendorRepository vendorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String clientId = user.getUsername();

        switch (clientId) {

            case PUBLIC_USER_CLIENT_ID:
                PublicUser publicUser = this.getPublicUser(username);
                return new UserAuthDto(publicUser.getId(), username, publicUser.getPassword(), getRole(UserRole.PUBLIC_USER),
                        getCommonUserDetailsFromPublicUser(publicUser));

            case ADMIN_CLIENT_ID:
                Admin admin = this.getAdmin(username);
                return new UserAuthDto(admin.getId(), username, admin.getPassword(), getRole(UserRole.ADMIN),
                        getCommonUserDetailsFromAdmin(admin));

            case VENDOR_CLIENT_ID:
                Vendor vendor = this.getVendor(username);
                return new UserAuthDto(vendor.getId(), username, vendor.getPassword(), getRole(UserRole.VENDOR),
                        getCommonUserDetailsFromVendor(vendor));
            default:
                throw new CustomOauthException("Invalid client id");

        }

    }

    private PublicUser getPublicUser(String email) {
        Optional<PublicUser> optionalPublicUser = publicUserRepository.findByEmail(email);
        if (optionalPublicUser.isPresent()) {
            PublicUser publicUser = optionalPublicUser.get();
            if (publicUser.getStatus() == UserStatus.INACTIVE) {
                throw new CustomOauthException("This user account is inactive. Please contract ROADSIGN support.");
            }
            if (publicUser.getStatus() == UserStatus.PENDING) {
                throw new CustomOauthException("This user account is under pending status. Please verify your account email.");
            }
            return publicUser;
        } else {
            throw new CustomOauthException("You have entered an invalid username or password");
        }
    }

    private Admin getAdmin(String username) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (admin.getStatus() == UserStatus.INACTIVE) {
                throw new CustomOauthException("This user account is inactive. Please contract ROADSIGN support.");
            }
            return admin;
        } else {
            throw new CustomOauthException("You have entered an invalid username or password");
        }
    }

    private Vendor getVendor(String email) {
        Optional<Vendor> optionalVendor = vendorRepository.findByEmail(email);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            if (vendor.getStatus() == UserStatus.INACTIVE) {
                throw new CustomOauthException("This user account is inactive. Please contract ROADSIGN support.");
            }
            if (vendor.getStatus() == UserStatus.PENDING) {
                throw new CustomOauthException("This user account is under pending status. Please verify your account email.");
            }
            return vendor;
        } else {
            throw new CustomOauthException("You have entered an invalid username or password");
        }
    }


//    private PublicUser getPublicUser(String username) {
//        PublicUser publicUser = null;
//        if (EmailValidator.getInstance().isValid(username)) {
//            publicUser = publicUserRepository.findByUserByEmail(username).orElseThrow(() -> new CustomServiceException("Invalid Email!"));
//        } else if (username.chars().allMatch(Character::isDigit) && username.length() <= 12) {
//            /*convert user mobile number to  MSISDN (Mobile Station Integrated Services Digital Network(sri lanka))*/
//            String msisdn = MobileNumberValidator.getMobileStandardFormat(username);
//            publicUser = publicUserRepository.findByUserByMobile(msisdn).orElseThrow(() -> new CustomServiceException("Invalid Mobile Number!"));
//        } else {
//            throw new CustomServiceException("enter valid email or mobile!");
//        }
//        if (publicUser.getStatus() != ActiveStatus.ACTIVE) {
//            throw new CustomOauthException(AppConstants.NotFoundConstants.NO_USER_FOUND);
//        }
//        if (!this.checkOtpAlreadyValid(publicUser)) throw new CustomServiceException(OTP_VERIFICATION_TIME_IS_OVER);
//
//        return publicUser;
//    }

    private CommonUserDetails getCommonUserDetailsFromAdmin(Admin admin) {
        CommonUserDetails userDetails = new CommonUserDetails();
        userDetails.setUsername(admin.getUsername());
        return userDetails;
    }

    private CommonUserDetails getCommonUserDetailsFromVendor(Vendor vendor) {
        return new CommonUserDetails(
                null,
                vendor.getFirstName(),
                vendor.getLastName(),
                vendor.getMobile(),
                vendor.getEmail(),
                vendor.getGender()
        );
    }

    private CommonUserDetails getCommonUserDetailsFromPublicUser(PublicUser publicUser) {
        return new CommonUserDetails(
                null,
                publicUser.getFirstName(),
                publicUser.getLastName(),
                publicUser.getMobile(),
                publicUser.getEmail(),
                publicUser.getGender()
        );
    }

    /**
     * @param userRole the user role of a searched user
     * @return the user role as authority
     */
    private List<SimpleGrantedAuthority> getRole(UserRole userRole) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole));
    }
}

