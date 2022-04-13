package com.validation.dto.documents;

import com.validation.model.documents.Passport;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassportDto {

    Long id;
    String serial;
    String number;

    LocalDate issueDate;
    String departmentCode;

    String gender;
    LocalDate birthDate;
    String birthPlace;

    String firstName;
    String lastName;
    String middleName;

    public static Passport toPassport(PassportDto dto) {
        return Passport.builder()
                .id(dto.getId())
                .serial(dto.getSerial())
                .number(dto.getNumber())
                .issueDate(dto.getIssueDate())
                .departmentCode(dto.getDepartmentCode())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .birthPlace(dto.getBirthPlace())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .build();
    }

    public static PassportDto fromPassport(Passport passport) {
        return PassportDto.builder()
                .id(passport.getId())
                .serial(passport.getSerial())
                .number(passport.getNumber())
                .issueDate(passport.getIssueDate())
                .departmentCode(passport.getDepartmentCode())
                .gender(passport.getGender())
                .birthDate(passport.getBirthDate())
                .birthPlace(passport.getBirthPlace())
                .firstName(passport.getFirstName())
                .lastName(passport.getLastName())
                .middleName(passport.getMiddleName())
                .build();
    }
}
