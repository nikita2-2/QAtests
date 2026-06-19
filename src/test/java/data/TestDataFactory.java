package data;

import data.UserRequestData;

public class TestDataFactory {
    public static UserRequestData createValidBirthUserData(String name) {
        return UserRequestData.builder()
                .mode("birth")
                .personalFirstName(name)
                .personalLastName("Иванов")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("79991112233")
                .personalNumberOfPassport("AB123456")

                .citizenLastName("Иванов")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("Муж")

                .dateOfMarriage("2026-10-10")
                .newLastName("Иванова")
                .anotherPersonLastName("Сидорова")
                .anotherPersonFirstName("Мария")
                .anotherPersonMiddleName("Алексеевна")
                .birth_of_anotoherPerson("15051997")
                .anotherPersonPassport("CD789012")

                .birth_place("Москва, ул Длинная 4")
                .birth_mother("Anna")
                .birth_father("Egor")
                .build();
    }

    public static UserRequestData createDeathData(){
        return UserRequestData.builder()
                .mode("death")

                .personalFirstName("Иванвцовв")
                .personalLastName("Иванов")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("79999856985")
                .personalNumberOfPassport("ММ466666")

                .citizenLastName("Петрова")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("М")

                .death_placeOfDeath("Москва улица Ленина")
                .death_dateOfDeath("2025-10-10")

                .build();
    }

    public static UserRequestData createMarriageData(){
        return UserRequestData.builder()
                .mode("wedding")

                .personalLastName("Иванов")
                .personalFirstName("Иванвцовв")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("79999856985")
                .personalNumberOfPassport("ММ466666")

                .citizenLastName("Петрова")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("М")

                .dateOfMarriage("2026-10-10")
                .newLastName("")
                .anotherPersonLastName("Сидорова")
                .anotherPersonFirstName("Мария")
                .anotherPersonMiddleName("Алексеевна")
                .birth_of_anotoherPerson("1999-01-01")
                .anotherPersonPassport("CD789012")

                .build();
    }

    public static AdminRequestData createAdminData(){
        return AdminRequestData.builder()
                .dateofbirth("1985-01-01")
                .personalFirstName("smdfsk")
                .personalLastName("smdfsk")
                .personalMiddleName("smdfsk")
                .personalNumberOfPassport("ФИ123456")
                .personalPhoneNumber("7999123445")
                .build();
    }
    public static AdminRequestData createValidAdminData(String adminName) {
        return AdminRequestData.builder()
                .dateofbirth("2020-10-10")
                .personalFirstName(adminName) // Передаем имя динамически
                .personalLastName("Петров")
                .personalMiddleName("Петрович")
                .personalNumberOfPassport("KH123456")
                .personalPhoneNumber("79998887766")
                .build();
    }
}
