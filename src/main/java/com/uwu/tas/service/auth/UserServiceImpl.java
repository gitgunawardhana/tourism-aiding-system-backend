package com.uwu.tas.service.auth;

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

import static com.uwu.tas.config.security.SecurityConstants.PUBLIC_USER_CLIENT_ID;
import static com.uwu.tas.config.security.SecurityConstants.VENDOR_CLIENT_ID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PublicUserRepository publicUserRepository;
    private final VendorRepository vendorRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("User login: " + username);

        // gets current authentication principal
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String clientId = user.getUsername();

        switch (clientId) {

            case PUBLIC_USER_CLIENT_ID:
                PublicUser publicUser = getPublicUser(username);
                return new UserAuthDto(publicUser.getId(), username, publicUser.getPassword(), getRole(UserRole.PUBLIC_USER),
                        publicUser.getStatus(), getCommonUserDetailsFromPublicUser(publicUser));

            case VENDOR_CLIENT_ID:
                Vendor vendor = getVendor(username);
                return new UserAuthDto(vendor.getId(), username, vendor.getPassword(), getRole(UserRole.VENDOR),
                        vendor.getStatus(), getCommonUserDetailsFromVendor(vendor));

            default:
                Admin admin = getAdmin(username);
                return new UserAuthDto(admin.getId(), username, admin.getPassword(), getRole(UserRole.ADMIN),
                        admin.getStatus(), getCommonUserDetailsFromAdmin(admin));
        }

    }

    private List<SimpleGrantedAuthority> getRole(UserRole userRole) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole));
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
            return vendor;
        } else {
            throw new CustomOauthException("You have entered an invalid username or password");
        }
    }

    private PublicUser getPublicUser(String email) {
        Optional<PublicUser> optionalPublicUser = publicUserRepository.findByEmail(email);
        if (optionalPublicUser.isPresent()) {
            PublicUser publicUser = optionalPublicUser.get();
            if (publicUser.getStatus() == UserStatus.INACTIVE) {
                throw new CustomOauthException("This user account is inactive. Please contract ROADSIGN support.");
            }
            return publicUser;
        } else {
            throw new CustomOauthException("You have entered an invalid username or password");
        }
    }

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
}
